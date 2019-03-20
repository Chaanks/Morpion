package core;
import java.util.ArrayList;
import java.util.Arrays;


public class Board {
    
    int SIZE = 3;
    final int[][] WIN = {
     {0, 1, 2},
     {3, 4, 5},
     {6, 7, 8},
     {0, 4, 8},
     {2, 4, 6},
     {0, 3, 6},
     {1, 4, 7},
     {2, 5, 8},
    };

    private char[] board;


    public Board() {
        board = new char[] {' ', ' ', ' ',
                            ' ', ' ', ' ',
                            ' ', ' ', ' '};

    }
    
    public Board(Board b) {
    	board =  Arrays.copyOf(b.board, b.board.length);
    }

    public Boolean Move(int x, int y, Boolean isCircle) {
        if (board[x * SIZE + y] == ' ') {
            if (isCircle) {
                board[x * SIZE + y] = 'O';
            } else {
                board[x * SIZE + y] = 'X';
            }
            return true;
        }
        return false;
    }
    
    
    public Boolean Move(int x, Boolean isCircle) {
        if (board[x] == ' ') {
            if (isCircle) {
                board[x] = 'O';
            } else {
                board[x] = 'X';
            }
            return true;
        }
        return false;
    }

    public void display() {
        for (int i=1; i<=board.length; i++) {
            System.out.print(board[i-1]);
            System.out.print('|');
            if (i % SIZE == 0) {
                System.out.println("");
            }
        }
    }
    
    public Boolean isWinner(Boolean isCircle) {
    	char token = 'X';
    	if (isCircle) token = 'O';
    	
    	for (int[] cond : WIN) {
    		if (board[cond[0]] == token && board[cond[1]] == token && board[cond[2]] == token) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public Boolean isDraw() {
    	for (char c : board) {
    		if (c == ' ') return false;
    	}
    	
    	if (isWinner(true) || isWinner(false)) {
    		return false;
    	}
    	
    	return true;
    }
    
    public Boolean isEnd() {
    	if (getValidPosition().size() == 0) {
    		return true;
    	} else if (isWinner(true) || isWinner(false)) {
    		return true;
    	}
    	
    	return false;
    }
    
    public ArrayList<Integer> getValidPosition() {    	
    	ArrayList<Integer> positions = new ArrayList<Integer>();
    	for (int i=0; i<board.length; i++) {
    		if (board[i] == ' ') {
    			positions.add(i);
    		}
    	}
    	
    	return positions;
    }
    
    public double[] normalize() {
    	double[] normalized = new double[9];
    	
    	for (int i=0; i<board.length; i++) {
    			if (board[i] == ' ') normalized[i] = 0.0;
    			else if (board[i] == 'O') normalized[i] = 1.0;
    			else if (board[i] == 'X') normalized[i] = -1.0;
    	}
    	
    	return normalized;
    }
    
    public void loadConfig(char[] conf) {
    	board = conf;
    }
    
    public char[] getBoard() {return board;}

}