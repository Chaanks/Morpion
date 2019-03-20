package core;
import java.util.ArrayList;
import java.util.Random;


public class Player {
	Boolean token;
	
	public Player(Boolean isCircle) {
		token = isCircle;
	}
	
	public void play(Board board) {
		
		ArrayList<Integer> validPositions = board.getValidPosition();
		Random rand = new Random();
	    int id = validPositions.get(rand.nextInt(validPositions.size()));
	    
	    board.Move(id, token);
	}
}
