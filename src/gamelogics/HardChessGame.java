package gamelogics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
import players.Player;

public class HardChessGame extends ChessGameLogic{
	protected boolean checkFirst;
	protected ArrayList<Position> validMoves;
	protected ImageIcon currentImageIcon;
	protected int seconds ;
	protected Thread timer;
	protected JLabel timerLabel;

	
	public HardChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		checkFirst=true;
		validMoves=new ArrayList<Position>();
        currentImageIcon=null;
        
    	timerLabel = new JLabel("30");
		timerLabel.setForeground(Color.RED);
		timerLabel.setFont(new Font("Algerian", Font.BOLD, 32));
		timerLabel.setBounds(35, 311, 50, 46);
		panel.add(timerLabel);
		
		boardFrame.setVisible(true);  
    
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
				OverTakeRemoved(buttonPosition);
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
						
						OverTakeSaved(buttonPosition);
						squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(currentImageIcon);
						squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
						//currentImageIcon=null;							
							
							if(currentPiece instanceof King)
				    	    	 new KingFilterCriteria().Castling(currentPiece, buttonPosition.getColumn());
					    	  
				    	    currentPiece.setFirstMove(true);
							currentPiece.setCurrentPosition(buttonPosition);
							//System.out.println(currentPiece.getPieceColor());
							
							  if(kingFilterCriteria.Checkmate(kingFilterCriteria.getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
								//here check mate\
				    	    	  JOptionPane.showMessageDialog(null, "Dead");
				    	       }
							
							currentPiece=null;
							seconds = 30;
							playTurn++;
						}
				}
				if(currentPiece instanceof Pawn &&(buttonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==-1
						||buttonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==1))
				{
					takeOver(buttonPosition);
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
						timerLabel.setText(seconds + "");
						if (seconds ==0)
						{
							seconds = 30;
							playTurn++;
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

	@Override
	void afterPromotion() {
		// TODO Auto-generated method stub
		
	}
}