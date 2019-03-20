package agent;

import core.Board;
import core.Player;

import java.util.ArrayList;

import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.SoftMaxTransferFunction;

public class MLP_2 implements Agent {
	int[] LAYERS = new int[]{ 9, 128, 32, 1 };
	
	double error;
	MultiLayerPerceptron net;
	double samples;
	
	int id = -1;
	
	public MLP_2() {
		net = new MultiLayerPerceptron(LAYERS, 0.01, new SigmoidalTransferFunction(), null);
		error = 0.0 ;
		samples = 1000000000 ;
	}

	public double[] predict(double[] X, Board board) {		
		double[] pred =  net.forwardPropagation(X);
		
		
		int predId = -1;
		
		if (pred[0] <= 0.11) {
			predId = 0;
		} else if (pred[0] <= 0.22) {
			predId = 1;
		} else if (pred[0] <= 0.33) {
			predId = 2;
		} else if (pred[0] <= 0.44) {
			predId = 3;
		} else if (pred[0] <= 0.55) {
			predId = 4;
		} else if (pred[0] <= 0.66) {
			predId = 5;
		} else if (pred[0] <= 0.77) {
			predId = 6;
		} else if (pred[0] <= 0.88) {
			predId = 7;
		} else if (pred[0] <= 1.0) {
			predId = 8;
		}
		
		
		
		System.out.println(predId);
		return pred;
	}
	

	@Override
	public void play(Board board) {
		double[] X = board.normalize();
		
		MinMax mm = new MinMax(true);
		double[] Y = mm.predict(board);
		System.out.println("Y :" + Y[0]);
		boolean turn = true;
		int cpt = 0;
		int predId = -1;
		
		while(turn) {
			cpt +=1;
			
			double[] pred = predict(X, board);
			error += net.backPropagate(X, Y);
			
			ArrayList<Integer> vp = board.getValidPosition();
			for (Integer i: vp) {
				if (pred[0] == i) {
					turn = false;
					System.out.println("Predict valid move " + pred[0] + " at iter " + cpt);
					predId = (int) pred[0];
					break;
				}
			}
		}

		board.Move(predId, true);
			
	}
	
	public static void train(int steps) {
        Player p2 = new Player(false);
        MLP agent = new MLP();
		
		for (int i=0; i<steps; i++) {
			Board board = new Board();
			
			if (i%2 == 0) {
				while(1 == 1) {
					p2.play(board);
					if (board.isEnd()) break;
					agent.play(board);
					if (board.isEnd()) break;
				}
				
			} else {
				while(1 == 1) {
					agent.play(board);
					if (board.isEnd()) break;
					p2.play(board);
					if (board.isEnd()) break;
				}
				
			}
	        //board.display();
	        //board.display();
	        //System.out.println(agent.error);
	        if ( i % 100 == 0 ) System.out.println("Error at step "+i+" is "+ (agent.error/(double)i));
		}
	}
	
	
	
}
