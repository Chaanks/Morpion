package application;

import agent.Agent;
import agent.MLP;
import core.Board;
import core.Player;

public class Model {
	
	private static Board board;
	private static Boolean onePlayer;
	
	MLP ai;
	String model;
	Player p1;
	Player p2;
	
	boolean isCircleTurn;
	String currentModel;
	public Model(Boolean onePlayer, String difficulty) {
		board = new Board();
		p1 = new Player(false);
		Model.onePlayer = onePlayer;
		
		if (onePlayer) {
			ai = new MLP();
			
			if (difficulty != null) {
				
				if (difficulty.equals("easy")) model = "first";
				else if (difficulty.equals("medium")) model = "third";
				else if (difficulty.equals("hard")) model = "evil";
				else if (difficulty.equals("")) model = "evil2";
				else model = difficulty;
				
			}
			
			ai.load(model);
			
		} else {
			p2 = new Player(false);
		}
		
		isCircleTurn = true;
		if ((int) (Math.random()+0.5) == 1) {
			isCircleTurn = false;
		}
		
		if (isCircleTurn && isAiGame()) {
			int id = computeMove();
			Controller.buttons.get(id).setStyle(getPlayerStyle(true));
			System.out.println("ia play");
			board.display();
		}
	}
	
	

	public boolean computeMove(int x) {
		if (isAiGame()) {
			if (!isEnd()) {
				if (board.Move(x, false)) {
					Controller.buttons.get(x).setStyle(getPlayerStyle(false));
					System.out.println("player play");
					board.display();
					if (!isEnd()) {
						int id = computeMove();
						Controller.buttons.get(id).setStyle(getPlayerStyle(true));
						System.out.println("ia play");
						board.display();
					}
					
					return true;
				} 				
			}
		} else {
			if (!isEnd()) {
				if (board.Move(x, isCircleTurn)) {
					Controller.buttons.get(x).setStyle(getPlayerStyle(isCircleTurn));
					System.out.println("player play");
					board.display();
					isCircleTurn = !isCircleTurn;
					return true;
				}				
			}
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
	
	public int[] getWinPos() {
		return board.getWinnerPos();
	}


	public int computeMove() {
		return ai.play(board);
		
	}
	
	public Boolean isOnePlayer() {
		return Model.onePlayer;
	}
	
	public static Boolean isDraw() {
		return Model.board.isDraw();
	}
}
