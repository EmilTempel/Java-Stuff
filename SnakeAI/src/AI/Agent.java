package AI;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashMap;

import Game.Snake;
import Game.Snake.NextState;
import Game.World;
import Main.Start;

public class Agent {
	World world;
	Snake player;
	HashMap<String, Double> q_table;

	int num_episodes = 100000;
	int max_steps_per_episode = 10000;
	int sleeptime = 0;

	double learning_rate = 0.9;
	double discount_rate = 0.5;

	double exploration_rate = 1;
	double max_exploration_rate = 1;
	double min_exploration_rate = 0;
	double exploration_decay_rate = 0.001;

	public Agent(World world) {
		this.world = world;
		player = world.player;
		q_table = new HashMap<String, Double>();

	}

	public void startLearning() {

		for (int episode = 1; episode <= num_episodes; episode++) {
			if (episode == 20000)
				sleeptime = 20;
			player = new Snake(world, 15, 15, 4);
			world.respawnFruit();

			double current_reward = 0;

			for (int step = 0; step < max_steps_per_episode; step++) {
				int[][] state = player.getState();

				int action = 0;
				double[] actions = new double[5];
				for (int i = 0; i < 5; i++)
					actions[i] = QValue(state, i);
				double exploration_threshold = Math.random();

				if (exploration_threshold > exploration_rate) {
					action = optimalAction(actions);
				} else {
					action = (int) (Math.random() * 5);
				}

				NextState nextState = player.Move(action);

				double[] nextState_actions = new double[5];
				for (int i = 0; i < 5; i++)
					nextState_actions[i] = QValue(nextState.state, i);

				double q_value = QValue(state, action) * (1 - learning_rate)
						+ learning_rate * (nextState.reward + discount_rate * optimalValue(nextState_actions));

				setQValue(state, action, q_value);


				current_reward = current_reward + nextState.reward;

				if (nextState.done)
					break;

				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			System.out.println(episode + ":  " + current_reward);

			exploration_rate = min_exploration_rate
					+ (max_exploration_rate - min_exploration_rate) * exponentialDecay(exploration_decay_rate, episode);

		}
	}

	public double QValue(int[][] state, int action) {
		
		String code = String.valueOf(action);

			for (int x = 0; x < 11; x++)
				for (int y = 0; y < 11; y++)
					code = code + state[x][y];

		if (q_table.get(code) == null) {
			q_table.put(code, 0.0);
		}

		return q_table.get(code);
	}

	public void setQValue(int[][] state, int action, double value) {
		String code = String.valueOf(action);

			for (int x = 0; x < 11; x++)
				for (int y = 0; y < 11; y++)
					code = code + state[x][y];

		q_table.put(code, value);
	}

	public int optimalAction(double[] actions) {

		int optimal_action = 0;
		double temp = actions[0];

		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i]) {
				optimal_action = i;
				temp = actions[i];
			}
		}

		return optimal_action;
	}

	public double optimalValue(double[] actions) {

		double temp = actions[0];

		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i]) {
				temp = actions[i];
			}
		}

		return temp;
	}

	public double exponentialDecay(double decay_rate, int episodes) {
		double result = 1;

		for (int i = 0; i < episodes; i++) {
			result = result * decay_rate;
		}
		return result;
	}

	public void paint(Graphics g) {
		player.paint(g);
	}
}
