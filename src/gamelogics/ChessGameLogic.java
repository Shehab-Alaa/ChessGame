package gamelogics;

import javax.swing.JButton;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;
import players.Player;

abstract public class ChessGameLogic {
   protected Player playerOne;
   protected Player playerTwo;
   protected ChessBoard chessBoard;
   protected int playTurn; // 0 for White , 1 for Black
   protected ChessPiece currentPiece;
   
   public ChessGameLogic(Player playerOne,Player playerTwo)
   {
	   this.playerOne = playerOne;
	   this.playerTwo = playerTwo;
	   chessBoard = ChessBoard.getChessBoard();
	   playTurn = 0;
   }
   
   public void startGame()
   {
	   
   }
   
   abstract public void gameLogic(Position position ,JButton[][] squares);
   
   public int getPlayTurn()
   {
	   return playTurn;
   }
   
   public ChessPiece getCurrentPiece()
   {
	   return currentPiece;
   }
   
   public void endGame()
   {
	   
   }
}
