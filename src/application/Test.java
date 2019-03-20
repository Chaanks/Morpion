package application;

import agent.HumanConsol;
import agent.MLP;
import agent.MLP_2;
import agent.MinMax;
import core.Board;
import core.Player;

public class Test {
    public static void main(String[] args) {
        Board board = new Board();
        Player p1 = new Player(true);
        Player p2 = new Player(false);
        MLP agent = new MLP();
        MinMax mm = new MinMax(false);
        MinMax mm2 = new MinMax(true);
        HumanConsol h2 = new HumanConsol(false);
        HumanConsol h = new HumanConsol(true);
        
        //char[] conf = {'X', 'O', 'X',
        //			   'O', 'O', 'X',
        //			   ' ', ' ', ' '};
        //board.loadConfig(conf);
        

        MLP.train(10000);
        
        //agent.play(board);
        
        /*
        agent.load("evil");
        
        
        while(1 == 1) {
        	agent.play(board);
        	//mm2.predict_2(board);
        	//mm2.play(board);
        	if (board.isEnd()) break;
        	board.display();
        	h2.play(board);
        	if (board.isEnd()) break;
        }
        
        
        */
        //agent.play(board);
        
        //System.out.println(test.isWinner(false));
    }
}
