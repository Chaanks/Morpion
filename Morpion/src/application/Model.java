package application;

import agent.Agent;
import agent.MLP;
import core.Board;
import core.Player;

public class Model {
	
	private static Board board;
	
	MLP ai;
	Player p1;
	Player p2;
	
	boolean isCircleTurn;
	
	public Model(Boolean onePlayer) {
		board = new Board();
		p1 = new Player(false);
		isCircleTurn = true;
		if (onePlayer) {
			ai = new MLP();
			ai.load("evil");
			
		} else {
			p2 = new Player(false);
		}
	}
	
	

	public boolean computeMove(int x) {
		if (isCircleTurn && isAiGame()) {
			int id = computeMove();
			Controller.buttons.get(id).setStyle(getPlayerStyle(true));
		}
		
		if (board.Move(x, false)) {
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

	public String getPlayerStyle(Boolean isCircle) {
		if (isCircle) {
			return "-fx-background-image: url('/res/circle.png'); -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-border-color: black;";
		} else {
			return "-fx-background-image: url('/res/cross.png'); -fx-background-position: center; -fx-background-repeat: no-repeat; -fx-border-color: black;";
		}
	}


	public int computeMove() {
		return ai.play(board);
		
	}
}
