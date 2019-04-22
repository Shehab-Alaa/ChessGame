package gamelogics;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;
import players.Player;

public class HardChessGame extends ChessGameLogic{

	private static boolean checkfirst;
	private ArrayList<Position>validmoves;
	private ImageIcon currentImageicon;
	
	public HardChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		checkfirst=true;
		validmoves=new ArrayList<Position>();
        currentImageicon=null;
    }

	private void pressedButton(Position buttonPosition,ChessPiece piece)
	{
		if(checkfirst==true)
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
						if(chessBoard.hasPieceInPositon(buttonPosition))
						{
							chessBoard.pieceCaptured(piece);
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
						}
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(currentImageicon);
							currentImageicon=null;
							squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
							currentPiece.setCurrentPosition(buttonPosition);
							currentPiece=null;
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
					if(squares[i][j].getIcon()==null || EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||EasyChessGame.playTurn % 2 == 1 && holder.getPieceColor().equals("Black")
							||checkfirst == false )
						 pressedButton(new Position(i,j),holder);
				}
			}
		}
	}
}