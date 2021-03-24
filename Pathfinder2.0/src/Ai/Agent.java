package Ai;

import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import Game.Player;
import Game.Player.NextState;
import Game.World;

public class Agent {
	World world;

	Player player;

	int x, y, current_episode;

	double[][][] q_table;


	double learning_rate = 0.99;
	double discount_rate = 0.99;

	double exploration_rate = 1;
	double max_exploration_rate = 1;
	double min_exploration_rate = 0.1;
	double exploration_decay_rate = 0.01;

	double reward;
	int different_steps;
	boolean done;
	public Agent(World world) {
		this.world = world;

		q_table = new double[world.b][world.l][5];
		for (double[][] D : q_table)
			for (double[] d : D)
				Arrays.fill(d, 0);

		current_episode = 0;
	}

	public Agent(Agent best_agent, Player player) {
		q_table = best_agent.q_table;
		this.player = player;

		current_episode = best_agent.current_episode;

		exploration_rate = best_agent.exploration_rate;
	}
	
	public void startofEpisode() {
		current_episode++;
		
		x = player.x;
		y = player.y;
		
		reward = 0;
		different_steps = 0;
		done = false;
	}

	public void runStep() {
			int action;
			double exploration_threshold = Math.random();

			if (exploration_threshold > exploration_rate) {
				action = optimalAction(q_table[x][y]);
			} else {
				action = randomAction();
			}

			NextState nextState = player.Move(action);

			if(q_table[x][y][action] == 0)
				different_steps++;
			q_table[x][y][action] = q_table[x][y][action] * (1 - learning_rate) + learning_rate
					* (nextState.reward + discount_rate * optimalValue(q_table[nextState.x][nextState.y]));

			x = nextState.x;
			y = nextState.y;
			reward += nextState.reward;
			done = nextState.done;
	}
	
	public double endofEpisode() {
		exploration_rate = min_exploration_rate + (max_exploration_rate - min_exploration_rate)
				* exponentialDecay(exploration_decay_rate, current_episode);

		return reward;
	}

	public int optimalAction(double[] actions) {

		int optimal_action = 0;
		double temp = actions[0];

		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i] && player.isPossible(i)) {
				optimal_action = i;
				temp = actions[i];
			}
		}

		return optimal_action;
	}

	public double optimalValue(double[] actions) {
		double temp = actions[0];
		for (int i = 0; i < actions.length; i++) {
			if (temp < actions[i] && player.isPossible(i)) {
				temp = actions[i];
			}
		}
		return temp;
	}

	public int randomAction() {
		int random_action = (int) (Math.random() * 5);
		if (player.isPossible(random_action)) {
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

		for (int x = 0; x < world.b; x++)
			for (int y = 0; y < world.b; y++) {
				double average = 0;
				for (int i = 1; i <= 4; i++)
					average += q_table[x][y][i];

				average = average / 4;
				g.drawString(numberFormat.format(average), x * 100 + 30, y * 100 + 55);
			}
	}
}
