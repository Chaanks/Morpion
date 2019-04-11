package agent;

import core.Board;
import core.Player;

import java.util.ArrayList;

import ai.MultiLayerPerceptron;
import ai.SigmoidalTransferFunction;
import ai.SoftMaxTransferFunction;

public class MLP implements Agent {
	int[] LAYERS = new int[]{ 9, 32, 32,  9 };
	
	public double error;
	public float errorCpt;
	public MultiLayerPerceptron net;
	double samples;
	
	int id = -1;
	
	public MLP() {
		net = new MultiLayerPerceptron(LAYERS, 0.001, new SigmoidalTransferFunction(), null);
		error = 0.0 ;
		errorCpt = 0;
		samples = 1000000000 ;
	}

	public MLP(double lr, int[] layers) {
		net = new MultiLayerPerceptron(layers, lr, new SigmoidalTransferFunction(), null);
		error = 0.0 ;
		errorCpt = 0;
		samples = 1000000000 ;
	}
	
	public double[] predict(double[] X, Board board) {		
		double[] pred =  net.forwardPropagation(X);
		
		int predID = -1;
		double maxValue = Double.MIN_VALUE;
		
		/*
		ArrayList<Integer> vp = board.getValidPosition();
		for (Integer i: vp) {
			if (pred[i] > maxValue) {
				maxValue = pred[i];
				predID = i;
			}
		}
		*/
		
		for (int i=0; i<pred.length; i++) {
			if (pred[i] > maxValue) {
				maxValue = pred[i];
				predID = i;
			} else if (pred[i] == maxValue) {
				if ((int) (Math.random()+0.5) == 1) {
					maxValue = pred[i];
					predID = i;
				}
			}
		}
		
			
		for (int i=0; i<pred.length; i++) {
			if (i == predID) {
				pred[i] = 1.0;
			} else {
				pred[i] = 0.0;
			}
			//System.out.print(pred[i] + " ");
		}	
		//System.out.println("Prediciton : " + predID);
		id = predID;
		return pred;
	}
	

	@Override
	public int play(Board board) {
		double[] X = board.normalize();
		
		
		MinMax mm = new MinMax(true);
		double[] Y = mm.predict_2(board);
		ArrayList<Integer> vp = board.getValidPosition();
		
		int predId = -1;
		
		boolean turn = true;
		int cpt = 0;
		while(turn) {
			cpt +=1;
			
			double[] pred = predict(X, board);
			
			boolean correct = false;
			if (Y[id] == 1.0) {
				//System.out.println("correct pred");
				correct = true;
			}
			
			if (correct) {
				error += net.backPropagate(X, pred);
				
			} else {
				Y = mm.predict(board);
				error += net.backPropagate(X, Y);
			}
			
			errorCpt += 1;
			for (Integer i: vp) {
				if (id == i) {
						turn = false;
						//System.out.println("Predict valid move " + i + " at iter " + cpt);
						break;
				}
					
				
			}
			
		}
		
		/*
		int move = -1;
		for (int i=0; i<Y.length; i++) {
			if (Y[i] == 1.0) {
				move = i;
				break;
			}
		}
		*/
		
		board.Move(id, true);
		return id;
			
	}
	
	public void load(String path) {
		net = MultiLayerPerceptron.load(path);
	}
	
	public static void train(String name, int steps, double lr, int hlc, int hls) {
		System.out.println("start");
        int[] layers = new int[hlc + 2];
        layers[0] = 9;
        for (int i=1; i<hlc-1; i++) {
        	layers[i] = hls;
        }
        layers[hlc] = 9;
        
        System.out.println(layers);
        
        Player p2 = new Player(false);
        MLP agent = new MLP(lr, layers);
        
        
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
	        if ( i % 100 == 0 ) System.out.println("Error at step "+i+" is "+ (agent.error/agent.errorCpt));
		}
		
		System.out.println("end training");
		agent.net.save(name);
	}
	
	
	
}
