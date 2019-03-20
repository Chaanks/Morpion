package agent;

import java.util.ArrayList;
import java.util.Arrays;

import core.Board;

public class MinMax implements Agent {

	Boolean isCircle;
	
	public MinMax(Boolean token) {
		isCircle = token;
	}
	
	@Override
	public int play(Board board) {
		int bestScore = Integer.MIN_VALUE;
		int move = -1;
		
		ArrayList<Integer> vp = board.getValidPosition();
		for (Integer i: vp) {
			Board tmp = new Board(board);
			tmp.Move(i, isCircle);
			//tmp.display();
			int value = minmax(tmp, 1, false);
			if (value > bestScore) {
				bestScore = value;
				move = i;
			}	
			//System.out.println("value: " + bestScore);
			
		}
		
		System.out.println("player best move: " + bestScore + " - " + move);
		//System.out.println("player move: " + move);
		board.Move(move, isCircle);
		return move;

		
	}
	
	public double[] predict(Board board) {
		int bestScore = Integer.MIN_VALUE;
		int move = -1;
		ArrayList<Integer> vm = new ArrayList<Integer>();
		
		ArrayList<Integer> vp = board.getValidPosition();
		for (Integer i: vp) {
			Board tmp = new Board(board);
			tmp.Move(i, isCircle);
			int value = minmax(tmp, 1, false);
			if (value > bestScore) {
				bestScore = value;
				move = i;
				vm.clear();
				vm.add(i);
			} else if (value == bestScore) {
				vm.add(i);
			}
		}
		
		double[] pred = new double[9];
		for (int i=0; i<pred.length; i++) {
			if (i == move) {
				pred[i] = 1.0;
			} else {
				pred[i] = 0.0;
			}
			//System.out.print(pred[i] + " ");
		}	
		//System.out.println("Prediciton : " + move);
		
		return pred;
	}
	
	public double[] predict_2(Board board) {
		int bestScore = Integer.MIN_VALUE;
		int move = -1;
		ArrayList<Integer> vm = new ArrayList<Integer>();
		
		ArrayList<Integer> vp = board.getValidPosition();
		for (Integer i: vp) {
			Board tmp = new Board(board);
			tmp.Move(i, isCircle);
			int value = minmax(tmp, 1, false);
			if (value > bestScore) {
				bestScore = value;
				move = i;
				vm.clear();
				vm.add(i);
			} else if (value == bestScore) {
				vm.add(i);
			}
		}
		
		
		double[] moves = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		for (int i=0; i<vm.size(); i++) {
			moves[vm.get(i)] = 1.0;
		}
		
		//System.out.println(Arrays.toString(moves));
		
		return moves;
	}
	
	
	public int minmax(Board board,int depth,  Boolean token) {
		
		
		if (board.isWinner(!isCircle)) {
			return -10;
		} else if (board.isWinner(isCircle)){
			return 10;
		} else if (board.getValidPosition().size() == 0) {
			return 0;
		}
		
		ArrayList<Integer> vp = board.getValidPosition();
		
		if (token) {
			int bestScore = Integer.MIN_VALUE;
			int bestValue = -1;
			for (Integer i: vp) {
				Board tmp = new Board(board);
				tmp.Move(i, isCircle);
				//tmp.display();
				int value = minmax(tmp, depth-1, !token);
				//System.out.println("Max value: " + value);
				if (value > bestScore) {
					bestScore = value;
					bestValue = i;
				} else if (value == bestScore) {
					if ((int) (Math.random()+0.5) == 1) {
						bestScore = value;
						bestValue = i;
					}
				}
				
				
			}
			
			return bestScore;
				
		} else {
			int bestScore = Integer.MAX_VALUE;
			int bestValue = -1;
			for (Integer i: vp) {
				Board tmp = new Board(board);
				tmp.Move(i, !isCircle);
				//tmp.display();
				int value = minmax(tmp, depth-1, !token);
				//System.out.println("Min value: " + value);
				if (value < bestScore) {
					bestScore = value;
					bestValue = i;
				} else if (value == bestScore) {
					if ((int) (Math.random()+0.5) == 1) {
						bestScore = value;
						bestValue = i;
					}
				}
			}
			
			return bestScore;
		}

			
	}
	
}
