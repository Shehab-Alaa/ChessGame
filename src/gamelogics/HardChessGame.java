package gamelogics;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import pieces.ChessPiece;
import players.Player;

public class HardChessGame extends ChessGameLogic{
	private boolean checkFirst;
	private ArrayList<Position> validMoves;
	private ImageIcon currentImageIcon;
	private int seconds ;
	private Thread timer;
	
	public HardChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		checkFirst=true;
		validMoves=new ArrayList<Position>();
        currentImageIcon=null;
        seconds = 30;
        startCountDown();
    }
	
	private void gameLogic(Position buttonPosition,ChessPiece piece)
	{
		if(checkFirst)
		{
			if(piece!=null)
			{
				validMoves = chessBoard.getValidPositions(piece);
				currentImageIcon = (ImageIcon) squares[buttonPosition.getRow()][buttonPosition.getColumn()].getIcon();
				currentPiece=piece;
				checkFirst=false;
			}
		}
		else
		{		
			checkFirst = true;
			if (piece != null && piece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				gameLogic(buttonPosition, piece);
			}			
			
			else 
			{
				for (int i=0;i<validMoves.size();i++)
				{
					if(validMoves.get(i).getRow() == buttonPosition.getRow() && validMoves.get(i).getColumn() == buttonPosition.getColumn())
					{
						if(piece != null)
						{
							chessBoard.pieceCaptured(piece);
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
						}
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(currentImageIcon);
							currentImageIcon=null;
							squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
							currentPiece.setCurrentPosition(buttonPosition);
							if(kingFilterCriteria.Checkmate(kingFilterCriteria.getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
								//here check mate\
				    	    	  JOptionPane.showMessageDialog(null, "Dead");
				    	      }
							currentPiece=null;
							seconds = 30;
							playTurn++;
						}
				}
			}
		}
		
	}
	
	private void startCountDown()
	{
		 timer = new Thread()
			{
				public void run() {
				for(;;)
				{
					try {
						sleep(1000);
						seconds--;
						System.out.println("Seconds: " + seconds);
						if (seconds ==0)
						{
							seconds = 30;
							playTurn++;
							System.out.println("Player is Turned");
						}
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				}
			};
			timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));
					if(holder != null && playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||holder != null && playTurn % 2 == 1 && holder.getPieceColor().equals("Black")
							||checkFirst == false )
						 gameLogic(new Position(i,j),holder);
				}
			}
		}
	}
}