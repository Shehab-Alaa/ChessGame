package gamelogics;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import extra.Position;
import pieces.ChessPiece;
import players.Player;

public class EasyChessGame extends ChessGameLogic{

	public EasyChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}

	@Override
	public void gameLogic(Position position, JButton[][] squares) {
		currentPiece = chessBoard.getPiece(position);
		colorValidPositions(currentPiece , squares);
		
	}

	public void colorValidPositions(ChessPiece chessPiece , JButton[][] squares)
	{
		ArrayList<Position> positionsHolder = chessBoard.getValidPositions(chessPiece);
		
		for (Position position : positionsHolder)
		{
			squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.green , 3));
		}
	}


	
	// cannot move if the king will be captured;
	
}
