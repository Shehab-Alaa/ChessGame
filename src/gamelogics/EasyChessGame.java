package gamelogics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import extra.Position;
import pieces.ChessPiece;
import players.Player;

public class EasyChessGame extends ChessGameLogic{

	public EasyChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}


	public static void colorValidPositions(ArrayList<Position> positionsHolder)
	{
		for (Position position : positionsHolder)
		{
			squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.green , 3));
		}
		
	}
	
	public static void pressedButton(Position buttonPosition)
	{		
		if (!squares[buttonPosition.getRow()][buttonPosition.getColumn()].getBorder().equals(new JButton().getBorder()))
	      {
	    	 // colored
	    	  if(chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  ChessPiece enemy = chessBoard.getPiece(buttonPosition);
	    		  squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
	    		  chessBoard.pieceCaptured(enemy);
	    	  }	
	    		  ImageIcon iconHolder = (ImageIcon) squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].getIcon();
	    		  squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
	    	      squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(iconHolder);
	    	      removeColoredBorder();
	    	      currentPiece.setCurrentPosition(buttonPosition);
	    	      currentPiece = null;    	      
	    	      playTurn++;
	      }
	      else
	      {
	    	 // uncolored
	    	  if (chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  if(hasCurrentPiece())
	    		  {
	    			  removeColoredBorder();
	    		  }
	    		  currentPiece = chessBoard.getPiece(buttonPosition);
	    		  colorValidPositions(chessBoard.getValidPositions(currentPiece));
	    	  }
	 
	      }
	  
	}
	
	public static void removeColoredBorder()
	{
		for (Position position : chessBoard.getValidPositions(currentPiece))
		{
			squares[position.getRow()][position.getColumn()].setBorder(UIManager.getBorder("Button.border"));
		     // new button and get it's border;
		}
	}
	
	public static boolean hasCurrentPiece()
	{
		return(currentPiece != null);
	}
	
	
	
	
	
}
