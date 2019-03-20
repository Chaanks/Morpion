package agent;

import java.util.Scanner;

import core.Board;

public class HumanConsol implements Agent{

	Boolean token;
	
	public HumanConsol(Boolean isCircle) {
		token = isCircle;
	}
	
	@Override
	public void play(Board board) {
		Scanner scanner = new Scanner(System.in);
		int line, col;
			System.out.println("Enter line: ");
			line = scanner.nextInt();
			System.out.println("Enter column: ");
			col = scanner.nextInt();
		
		board.Move(line, col, token);
			
	}
		
	

}
