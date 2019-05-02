package game;

import gamelogics.EasyChessGame;
import gamelogics.EasyVsComputerGame;
import gamelogics.HardChessGame;
import players.Player;

public class ChessGame {
  public static void main(String[]args)
  {
	// new HardChessGame(new Player("Shehab" , "White") , new Player("Alaa" , "Black"));
     new EasyChessGame(new Player("Shehab" , "White") , new Player("Alaa" , "Black"));
	//new EasyVsComputerGame(new Player("Shehab" , "White") , new Player("Alaa" , "Black"));
  }
}
