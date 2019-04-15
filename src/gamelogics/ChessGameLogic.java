package gamelogics;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;
import players.Player;

abstract public class ChessGameLogic {
   protected Player playerOne;
   protected Player playerTwo;
   protected static ChessBoard chessBoard;
   public static int playTurn; // even for White , odd for Black
   protected static ChessPiece currentPiece;
   protected static JButton squares[][];
   	
   
   public ChessGameLogic(Player playerOne,Player playerTwo)
   {
	   this.playerOne = playerOne;
	   this.playerTwo = playerTwo;
	   chessBoard = ChessBoard.getChessBoard();
	   currentPiece = null;
	   playTurn = 0;
	   squares = chessBoard.getSquares();
   }
   

}
