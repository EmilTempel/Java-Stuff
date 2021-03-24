package Ai;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import Game.Player.NextState;
import Game.World;

public class Agent {
	World world;

	int x, y;

	double[][][] q_table;

	int num_episodes, max_steps_per_episode;

	double learning_rate, discount_rate;

	double exploration_rate, max_exploration_rate, min_exploration_rate, exploration_decay_rate;

	ArrayList<Double> rewards;

	public Agent(World world) {
		this.world = world;

		q_table = new double[world.b][world.l][5];
		for (double[][] D : q_table)
			for (double[] d : D)
				Arrays.fill(d, 0);

		num_episodes = 100;
		max_steps_per_episode = 400;

		learning_rate = 0.99;
		discount_rate = 0.99;

		exploration_rate = 1;
		max_exploration_rate = 1;
		min_exploration_rate = 0;
		exploration_decay_rate = 0.001;

		rewards = new ArrayList<Double>();
	}

	public void startLearning() {
		int episodes = 0;
		for (int i = 0; i < num_episodes; i++) {
			episodes++;

			world.reset();
			x = world.player.x;
			y = world.player.y;

			boolean Done = false;

			double current_reward = 0;

			for (int j = 0; j < max_steps_per_episode; j++) {
				
				
				
				int action;
				double exploration_threshold = Math.random();

				if (exploration_threshold > exploration_rate) {
					action = optimalAction(q_table[x][y]);
				} else {
					action = randomAction();
				}

				NextState nextState = world.player.Move(action);

				q_table[x][y][action] = q_table[x][y][action] * (1 - learning_rate) + learning_rate
						* (nextState.reward + discount_rate * optimalValue(q_table[nextState.x][nextState.y]));

				x = nextState.x;
				y = nextState.y;
				current_reward += nextState.reward;

				if (nextState.done)
					break;
				
				
				
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(episodes + ":  " +current_reward);

			exploration_rate = min_exploration_rate + (max_exploration_rate - min_exploration_rate)
					* exponentialDecay(exploration_decay_rate, episodes);
			
			rewards.add(current_reward);
		}
		
		
	}

	public int optimalAction(double[] actions) {

		int optimal_action = 0;
		double temp = actions[0];

		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i] && world.player.isPossible(i)) {
				optimal_action = i;
				temp = actions[i];
			}
		}

		return optimal_action;
	}

	public double optimalValue(double[] actions) {
		double temp = actions[0];
		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i] && world.player.isPossible(i)) {
				temp = actions[i];
			}
		}
		return temp;
	}

	public int randomAction() {
		int random_action = (int) (Math.random() * 5);
		if (world.player.isPossible(random_action)) {
			return random_action;
		} else {
			return randomAction();
		}
	}

	public double exponentialDecay(double decay_rate, int episodes) {
		double result = 1;

		for (int i = 0; i < episodes; i++) {
			result = result * decay_rate;
		}
		return result;
	}
	
	public void paint(Graphics g) {
		DecimalFormat numberFormat = new DecimalFormat("#0.0000");
		
		for(int x = 0; x < world.b; x++)
			for(int y = 0; y < world.b;y++) {
				double average = 0;
				for(int i = 1; i <= 4; i++)
					average += q_table[x][y][i];
				
				average = average/4;
				g.drawString(numberFormat.format(average),x*100 +30,y*100+55);
			}
	}
}
