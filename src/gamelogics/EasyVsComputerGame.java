package gamelogics;
import java.util.Random;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
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
	    	      if(kingFilterCriteria.Checkmate(kingFilterCriteria.getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
	    			  //here check mate\
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
	    	      }
	    	      removeColoredBorder();
	    	      
	    	      currentPiece.setCurrentPosition(buttonPosition);
	    	      computerTurn(buttonPosition.getColumn());
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
	    	  else removeColoredBorder();
	    	  
	      }
	  
	}
	
	private void computerTurn(int column)
	{
		int c=0;
		
		generateBestMove(c);
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
	    
		playTurn++;
	}
	
	private void generateBestMove(int c)
	{	    
		bestPieceMove = null;
		bestPositionMove = null;
		bestValueMove = 0;
	    int  length=0,valueHolder=0;
	    ChessPiece holder=null;
		while (length<chessBoard.getChessPieces().size())
		{
			length++;
			Random r=new Random();
	        int randomNumber=r.nextInt(chessBoard.getChessPieces().size());
			if (chessBoard.getChessPieces().get(randomNumber).getPieceColor()=="Black")
			{
				Position Position=null;
				for (Position position : chessBoard.getValidPositions(chessBoard.getChessPieces().get(randomNumber)))
				{
					Position=position;
									
							
						if (chessBoard.hasPieceInPositon(position))
						{
							ChessPiece enemyPiece = chessBoard.getPiece(position);
							if(enemyPiece.getPieceColor()=="White") {
								if (bestValueMove < enemyPiece.getPieceValue())
								{
									if(Protected(chessBoard.getChessPieces().get(randomNumber),enemyPiece)&&enemyPiece.getPieceValue()>=chessBoard.getChessPieces().get(randomNumber).getPieceValue())
									{
										valueHolder=enemyPiece.getPieceValue();
										holder=enemyPiece;
									}
									else if(!Protected(chessBoard.getChessPieces().get(randomNumber),enemyPiece))
									{
										bestPieceMove = chessBoard.getChessPieces().get(randomNumber);
										bestPositionMove = enemyPiece.getCurrentPosition();
										bestValueMove = enemyPiece.getPieceValue();
									}
								}
							}
						}
					
				}
				if(bestPositionMove==null) {
					 if(holder!=null){
						bestPieceMove = holder;
						bestPositionMove = holder.getCurrentPosition();
					}
					 else {
							bestPieceMove = chessBoard.getChessPieces().get(randomNumber);
							bestPositionMove = Position;
					}
				}
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
		for( ChessPiece  whitePiece:chessBoard.getChessPieces()) {
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
