package game;

import java.awt.Font;

import extra.Position;
import gamelogics.EasyChessGame;
import gamelogics.EasyVsComputerGame;
import gamelogics.HardChessGame;
import pieces.ChessPiece;
import players.Player;

public class ChessGame {
  public static void main(String[]args) throws InterruptedException
  {
	  SplashScreen splashScreen = new SplashScreen();
	  splashScreen.run();
  }
}
