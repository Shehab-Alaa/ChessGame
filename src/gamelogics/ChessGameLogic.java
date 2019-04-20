package gamelogics;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import players.Player;

abstract public class ChessGameLogic implements ActionListener {
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
	   for(int i=0; i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				squares[i][j].addActionListener(this);
			}
		}
   }
   

   
}
 