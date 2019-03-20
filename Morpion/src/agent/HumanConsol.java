package agent;

import java.util.Scanner;

import core.Board;

public class HumanConsol implements Agent{

	Boolean token;
	
	public HumanConsol(Boolean isCircle) {
		token = isCircle;
	}
	
	@Override
	public int play(Board board) {
		Scanner scanner = new Scanner(System.in);
		int line, col;
			System.out.println("Enter line: ");
			line = scanner.nextInt();
			System.out.println("Enter column: ");
			col = scanner.nextInt();
		
		board.Move(line, col, token);
		return line * 3 + col;
			
	}
		
	

}
