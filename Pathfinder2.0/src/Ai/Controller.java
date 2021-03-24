package Ai;

import java.util.Arrays;

import Game.World;
import Game.Player.NextState;

public class Controller {
	World world;

	Agent[] agents;

	Agent best_agent;

	int num_episodes, current_episode, max_steps_per_episode;

	public Controller(World world) {
		this.world = world;

		best_agent = new Agent(world);

		num_episodes = 1000;
		max_steps_per_episode = 5000;

		agents = new Agent[world.player.length];
		resetAgents();
	}

	public void resetAgents() {
		for (int i = 0; i < agents.length; i++)
			agents[i] = new Agent(best_agent, world.player[i]);

	}

	public void startLearning() {
		for (int i = 0; i < num_episodes; i++) {
			double[] rewards = new double[agents.length];
			int[] steps = new int[agents.length];
			for (int j = 0; j < agents.length; j++) {
				agents[j].startofEpisode();
			}
			for (int step = 0; step < max_steps_per_episode; step++) {
				boolean d = false;
				for (int j = 0; j < agents.length; j++)
					if (!agents[j].done) {
						agents[j].runStep();
					}else {
						d = true;
					}
				
				if(d)
					break;
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j < agents.length; j++) {
				rewards[j] = agents[j].endofEpisode();
				steps[j] = agents[j].different_steps;
				
			}

			best_agent = agents[optimalAgent(rewards)];
			
			
				
			System.out.println(i + ":  " +best_agent.reward);
			world.reset();
			resetAgents();

		}

	}

	public int optimalAgent(double[] rewards) {

		int optimal_agent = 0;
		double temp = rewards[0];

		for (int i = 0; i < rewards.length; i++) {
			if (temp < rewards[i]) {
				optimal_agent = i;
				temp = rewards[i];
			}
		}

		return optimal_agent;
	}
	
	public int optimalstepsAgent(int[] steps) {

		int optimal_steps = 0;
		double temp = steps[0];

		for (int i = 0; i < steps.length; i++) {
			if (temp < steps[i]) {
				optimal_steps = i;
				temp = steps[i];
			}
		}

		return optimal_steps;
	}
}
