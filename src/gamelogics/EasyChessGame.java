package gamelogics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import extra.Position;
import filters.KingFilterCriteria;
import game.ChessBoard;
import pieces.ChessPiece;
import pieces.King;
import players.Player;
import undo.ChessBoardCareTaker;
import undo.ChessBoardOriginator;
import undo.ChessBoardState;

public class EasyChessGame extends ChessGameLogic{

	private ChessBoardOriginator chessBoardOriginator;
	private ChessBoardCareTaker chessBoardCareTaker;
	
	public EasyChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		
		chessBoardOriginator = new ChessBoardOriginator();
		chessBoardCareTaker = new ChessBoardCareTaker();
	}


	public static void colorValidPositions(ArrayList<Position> positionsHolder)
	{
		for (Position position : positionsHolder)
		{
			if (chessBoard.hasPieceInPositon(position))
			{
				  squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.yellow , 3));
			}
			else
			{
			  squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.green , 3));
			}
		}
		
	}
	
	private void pressedButton(Position buttonPosition)
	{		
		if (!squares[buttonPosition.getRow()][buttonPosition.getColumn()].getBorder().equals(new JButton().getBorder()))
	      {
	    	 // colored
			  if (playTurn == 0)
				  saveChessBoardState();
			  
	    	  if(chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  ChessPiece enemy = chessBoard.getPiece(buttonPosition);
	    		  squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
	    		  if(enemy instanceof King)
    			  {
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
    			  }
	    		  chessBoard.pieceCaptured(enemy);
	    	  }	
	    		  ImageIcon iconHolder = (ImageIcon) squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].getIcon();
	    		  squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
	    	      squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(iconHolder);
	    	      removeColoredBorder();
	    	      currentPiece.setCurrentPosition(buttonPosition);
	    	      if(new KingFilterCriteria().Checkmate(new KingFilterCriteria().getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
	    			  //here check mate\
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
	    	      }
	    	      currentPiece = null;    	      
	    	      playTurn++;
	    	      saveChessBoardState();
	    	      //chessBoard.rotateChessBoard();
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
	    	  else removeColoredBorder();
	    	  
	      }
	  
	}
	
	public static void removeColoredBorder()
	{
		for (int i =0;i<8;i++)
			for (int j=0;j<8;j++)
			  squares[i][j].setBorder(UIManager.getBorder("Button.border"));
	
	}
	
	public static boolean hasCurrentPiece()
	{
		return(currentPiece != null);
	}

	public void saveChessBoardState()
	{
		chessBoardOriginator.setState(new ChessBoardState(chessBoard.getChessPieces() , chessBoard.getCapturedPieces() , chessBoard.getSquares() , playTurn));
		chessBoardCareTaker.addChessBoardState(chessBoardOriginator.saveStateToMemento());
		chessBoardCareTaker.deleteRedoChessBoardStates();
	}
	
	public void undo()
	{
	   try {
		   chessBoardOriginator.getStateFromMemento(chessBoardCareTaker.getUndoState());
			ChessBoardState chessBoardStateHolder = chessBoardOriginator.getState();
			
			if (!chessBoardStateHolder.equals(null))
			{
				chessBoard.setChessPieces(chessBoardStateHolder.getChessPieces());
				chessBoard.setCapturedPieces(chessBoardStateHolder.getCapturedPieces());
				chessBoard.setSquares(chessBoardStateHolder.getSquares());
				playTurn = chessBoardStateHolder.getPlayTurn();
			}
	   }catch(NullPointerException ex)
	   {
			JOptionPane.showMessageDialog(null, "Nothing to Undo");
	   }
		
	}

	public void redo()
	{
		try {
			chessBoardOriginator.getStateFromMemento(chessBoardCareTaker.getRedoState());
			ChessBoardState chessBoardStateHolder = chessBoardOriginator.getState();
			
			if (chessBoardStateHolder != null)
			{
				chessBoard.setChessPieces(chessBoardStateHolder.getChessPieces());
				chessBoard.setCapturedPieces(chessBoardStateHolder.getCapturedPieces());
				chessBoard.setSquares(chessBoardStateHolder.getSquares());
				playTurn = chessBoardStateHolder.getPlayTurn();
			}
		}catch(NullPointerException ex)
		{
			JOptionPane.showMessageDialog(null, "Nothing to Redo");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));;
					if (currentPiece != null)
					{
						holder = currentPiece;
					}
	              
					if (i==0 && j == 0)
						  undo();
					else if (i==0 && j == 1)
						  redo();
					else if(squares[i][j].getIcon()==null || EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||EasyChessGame.playTurn % 2 == 1 && holder.getPieceColor().equals("Black"))
						           pressedButton(new Position(i,j));
				}
			}
		}
	}
	
	
	
	
	
}
