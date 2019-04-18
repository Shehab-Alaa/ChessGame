package game;

import gamelogics.EasyChessGame;
import players.Player;

public class GameLogic {
  public static void main(String[]args)
  {
	 new EasyChessGame(new Player("Shehab") , new Player("Alaa"));
  }
}
