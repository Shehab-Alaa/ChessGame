package gamelogics;
import java.util.Random;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import game.Promotion;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
import pieces.Rook;
import players.Player;
public class EasyVsComputerGame extends EasyChessGame{

	private ChessPiece bestPieceMove;
	private Position bestPositionMove;
	private int bestValueMove;
	
	public EasyVsComputerGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}
	
	@Override
	protected void gameLogic(Position buttonPosition)
	{		
		if (!squares[buttonPosition.getRow()][buttonPosition.getColumn()].getBorder().equals(new JButton().getBorder()))
	      {
			 
			 if (playTurn == 0)
				  saveChessBoardState();
			 
	    	 // colored
	    	 
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
	    	      if(currentPiece instanceof King)
	    	    	  kingFilterCriteria.Castling(currentPiece, buttonPosition.getColumn());
		    	  
	    	      currentPiece.setFirstMove(true);
				currentPiece.setCurrentPosition(buttonPosition);
				//System.out.println(currentPiece.getPieceColor());
				
				  if(kingFilterCriteria.Checkmate(kingFilterCriteria.getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
					//here check mate\
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
	    	       }
	    	     /* if(checkPromotion())
	    	      {
	    	    	  Promotion promotion = new Promotion(this);
	    	    	  boardFrame.setEnabled(false);
	    	      }
	    	      else
	    	      {
	    	        computerTurn(buttonPosition.getColumn());
	    	        playTurn++;
	    	        saveChessBoardState();
	    	        displayCapturedPieces();
	    	      }*/
	    	        playTurn++;
	    	        saveChessBoardState();
	    	        displayCapturedPieces();
	    	        computerTurn(buttonPosition.getColumn());
	    	       
	      }
	      else
	      {
	    	 // uncolored
	    	  if (chessBoard.hasPieceInPositon(buttonPosition) && hasTurn(buttonPosition) == true)
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
	
	private void computerTurn(int column)
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
	    if(bestPieceMove instanceof King) {
	    	 new KingFilterCriteria().Castling(bestPieceMove, column);
	    	 bestPieceMove.setFirstMove(false);
	    }
	    bestPieceMove.setCurrentPosition(bestPositionMove);
	    /*if (checkPromotion())
	    {
	       	Random random = new Random();
	       	int promotedPieceType = random.nextInt(4) + 1;
	       	currentPiece = bestPieceMove;
	       	promote(promotedPieceType);
	    }
	    else 
	    {  
		   playTurn++;
		   saveChessBoardState();
	       displayCapturedPieces();
	    }*/
	    playTurn++;
		saveChessBoardState();
	    displayCapturedPieces();
	}
	
	private void generateBestMove()
	{	    
		bestPieceMove = null;
		bestPositionMove = null;
		bestValueMove = 0;
		
	    int valueHolder = 0;
	    ChessPiece chessPieceHolder = null;
	    
		for (int i =0;i<chessBoard.getChessPieces().size();i++)
		{
			ChessPiece chessPiece = chessBoard.getChessPieces().get(i);
			if (chessPiece.getPieceColor()=="Black")
			{
				for (Position position : chessBoard.getValidPositions(chessPiece))
				{		
						if (chessBoard.hasPieceInPositon(position))
						{
							ChessPiece enemyPiece = chessBoard.getPiece(position);
							if(enemyPiece.getPieceColor()=="White") {
								if (bestValueMove < enemyPiece.getPieceValue())
								{
									if(Protected(chessPiece,enemyPiece)&&enemyPiece.getPieceValue()>=chessPiece.getPieceValue())
									{
										valueHolder = enemyPiece.getPieceValue();
										chessPieceHolder = enemyPiece;
									}
									else if(!Protected(chessPiece,enemyPiece))
									{
										bestPieceMove = chessPiece;
										bestPositionMove = enemyPiece.getCurrentPosition();
										bestValueMove = enemyPiece.getPieceValue();
									}
								}
							}
						}
					
				}
			
				if(bestPositionMove == null) {
					if(chessPieceHolder != null){
						bestPieceMove = chessPieceHolder;
						bestPositionMove = chessPieceHolder.getCurrentPosition();
					}
			    	else {
			    		generateRandomMove();
	        		}
				}
				
			}
		}
		
	}
	

	@Override
	void afterPromotion() {
		if (playTurn % 2 == 0)
		{
		   int column = currentPiece.getCurrentPosition().getColumn();	
		   currentPiece = null;
		   promotedPiece = null;
		   saveChessBoardState();
	       displayCapturedPieces();	
		   boardFrame.setEnabled(true);
		   computerTurn(column);
	       playTurn++;
		}
		else
		{
		  currentPiece = null;
		  promotedPiece = null;
	      saveChessBoardState();
	      displayCapturedPieces();
	      playTurn++;
		}
	}
    
	private void generateRandomMove()
	{
		Random random = new Random();
		int chessPieceIndex;
		while(true)
		{
			chessPieceIndex = random.nextInt(chessBoard.getChessPieces().size());
			if (chessBoard.getChessPieces().get(chessPieceIndex).getPieceColor().equals("Black")
					&& !(chessBoard.getChessPieces().get(chessPieceIndex) instanceof King)
					&& chessBoard.getValidPositions(chessBoard.getChessPieces().get(chessPieceIndex)).size() != 0)
			{
				bestPieceMove = chessBoard.getChessPieces().get(chessPieceIndex);
				int positionIndex = random.nextInt(chessBoard.getValidPositions(chessBoard.getChessPieces().get(chessPieceIndex)).size());
				bestPositionMove = chessBoard.getValidPositions(chessBoard.getChessPieces().get(chessPieceIndex)).get(positionIndex);
				break;
			}
		}
		
	}
	
	private void Swap(ChessPiece chessPiece,ChessPiece enemychessPiece) {
		Position temp= new Position(chessPiece.getCurrentPosition().getRow(),chessPiece.getCurrentPosition().getColumn());
		chessPiece.setCurrentPosition(enemychessPiece.getCurrentPosition());
		enemychessPiece.setCurrentPosition(temp);
	}
	
	private boolean Protected(ChessPiece chessPiece,ChessPiece enemychessPiece) {
		Swap(chessPiece,enemychessPiece);
		for(int i =0;i<chessBoard.getChessPieces().size();i++) {
			 ChessPiece whitePiece = chessBoard.getChessPieces().get(i);
			if(whitePiece.getPieceColor()=="White") {
				for (Position position : chessBoard.getValidPositions(whitePiece)){
					if(chessPiece.getCurrentPosition().getRow()==position.getRow()&&chessPiece.getCurrentPosition().getColumn()==position.getColumn()) {
						Swap(chessPiece,enemychessPiece);
						return true;
					}
				}
			}
		}			
		Swap(chessPiece, enemychessPiece);
		return false;
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
				
					if(squares[i][j].getIcon()==null || playTurn % 2 == 0 && holder.getPieceColor().equals("White"))
						           gameLogic(new Position(i,j));
				}
			}
		}
	}

}