package game;

import gamelogics.EasyChessGame;
import gamelogics.EasyVsComputerGame;
import gamelogics.HardChessGame;
import players.Player;

public class ChessGame {
  public static void main(String[]args)
  {
	// new HardChessGame(new Player("Shehab") , new Player("Alaa"));
     new EasyChessGame(new Player("Shehab") , new Player("Alaa"));
	// new EasyVsComputerGame(new Player("Shehab") , new Player("Alaa"));
  }
}
