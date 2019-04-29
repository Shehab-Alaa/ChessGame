package gamelogics;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import extra.Position;
import filters.KingFilterCriteria;
import game.ChessBoard;
import pieces.ChessPiece;
import players.Player;

public class HardChessGame extends ChessGameLogic{

	private static boolean checkfirst;
	private ArrayList<Position>validmoves;
	private ImageIcon currentImageicon;
	private static int seconds ;
	private Thread timer;
	public HardChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		checkfirst=true;
		validmoves=new ArrayList<Position>();
        currentImageicon=null;
        seconds = 30;
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

	private void pressedButton(Position buttonPosition,ChessPiece piece)
	{
		if(checkfirst)
		{
			if(piece!=null)
			{
				validmoves = chessBoard.getValidPositions(piece);
				currentImageicon = (ImageIcon) squares[buttonPosition.getRow()][buttonPosition.getColumn()].getIcon();
				currentPiece=piece;
				checkfirst=false;
			}
		}
		else
		{		
			checkfirst = true;
			if (piece != null && piece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				pressedButton(buttonPosition, piece);
			}			
			
			else 
			{
				for (int i=0;i<validmoves.size();i++)
				{
					if(validmoves.get(i).getRow() == buttonPosition.getRow() && validmoves.get(i).getColumn() == buttonPosition.getColumn())
					{
						if(piece != null)
						{
							chessBoard.pieceCaptured(piece);
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
						}
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(currentImageicon);
							currentImageicon=null;
							squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
							currentPiece.setCurrentPosition(buttonPosition);
							if(new KingFilterCriteria().Checkmate(new KingFilterCriteria().getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
				    			  
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));
					if(holder != null && EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||holder != null && EasyChessGame.playTurn % 2 == 1 && holder.getPieceColor().equals("Black")
							||checkfirst == false )
						 pressedButton(new Position(i,j),holder);
				}
			}
		}
	}
}