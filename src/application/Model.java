package application;

import agent.Agent;
import agent.MLP;
import core.Board;
import core.Player;

public class Model {

	private static int MOVE_ID;

	
	Board board;
	
	Agent ai;
	Player p1;
	Player p2;
	
	boolean isCircleTurn;
	
	public Model() {
		board = new Board();
		ai = new MLP();
		p1 = new Player(false);
		isCircleTurn = false;
	}
	
	
	public static int getMOVE_ID() {
		return MOVE_ID;
	}

	public static void setMOVE_ID(int id) {
		MOVE_ID = id;
	}


	public boolean computeMove(int x) {
		
		if (board.Move(x, isCircleTurn)) {
			if (ai == null) {
				isCircleTurn = !isCircleTurn;
			}
			
			board.display();
			return true;
		}
		return false;
	}
	
	public boolean isAiGame() {
		return ai != null;
	}
	
	public boolean isEnd() {
		return board.isEnd();
	}

	public String getPlayerStyle() {
		if (isCircleTurn == true) {
			return "-fx-background-image: url('/res/cross.png'); -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-border-color: black;";
		} else {
			return "-fx-background-image: url('/res/circle.png'); -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-border-color: black;";
		}
	}


	public int computeMove() {
		return ai.play(board);
		
	}
}
