package gamelogics;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import extra.Position;
import pieces.ChessPiece;
import pieces.Pawn;
import players.Player;

public class EasyVsComputerGame extends EasyChessGame{

	private ChessPiece bestPieceMove;
	private Position bestPositionMove;
	private int bestValueMove;
	
	public EasyVsComputerGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}
	
	private void pressedButton(Position buttonPosition)
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
	    	      computerTurn();
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
	
	private void computerTurn()
	{
		generateBestMove();
		if (chessBoard.hasPieceInPositon(bestPositionMove))
		{
			ChessPiece enemy = chessBoard.getPiece(bestPositionMove);
  		    squares[bestPositionMove.getRow()][bestPositionMove.getColumn()].setIcon(null);
  		    chessBoard.pieceCaptured(enemy);
		}
		ImageIcon iconHolder = (ImageIcon) squares[bestPieceMove.getCurrentPosition().getRow()][bestPieceMove.getCurrentPosition().getColumn()].getIcon();
	    squares[bestPieceMove.getCurrentPosition().getRow()][bestPieceMove.getCurrentPosition().getColumn()].setIcon(null);
	    squares[bestPositionMove.getRow()][bestPositionMove.getColumn()].setIcon(iconHolder);
	    bestPieceMove.setCurrentPosition(bestPositionMove);
		playTurn++;
	}
	
	private void generateBestMove()
	{
		bestPieceMove = null;
		bestPositionMove = null;
		bestValueMove = 0;
	
		for (ChessPiece chessPiece : chessBoard.chessPieces)
		{
			if (chessPiece.getPieceColor().equals("Black"))
			{
				for (Position position : chessBoard.getValidPositions(chessPiece))
				{
					if ((bestPieceMove == null) && (chessPiece instanceof Pawn == true))
					{
						bestPieceMove = chessPiece;
						bestPositionMove = position;
					}
					else 
					{
						if (chessBoard.hasPieceInPositon(position))
						{
							ChessPiece enemyPiece = chessBoard.getPiece(position);
							if (bestValueMove < enemyPiece.getPieceValue())
							{
								bestPieceMove = chessPiece;
								bestPositionMove = enemyPiece.getCurrentPosition();
								bestValueMove = enemyPiece.getPieceValue();
							}
						}
					}
				}
			}
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
				
					if(squares[i][j].getIcon()==null || EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White"))
						           pressedButton(new Position(i,j));
				}
			}
		}
	}

}
