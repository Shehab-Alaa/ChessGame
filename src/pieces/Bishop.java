package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.DiagonalMovement;

public class Bishop extends ChessPiece implements DiagonalMovement{

	public Bishop(Position currentPosition, String color) {
		super(currentPosition, color);
		pieceValue = 3;
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		validMoves = new ArrayList<>();
		super.validMoves.addAll(getValidDiagonalMoves());
		return super.validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {

		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=1; i<7; i++)
		{
			if (CheckPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i)))//left down
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i));
			
			if (CheckPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i)))//right down
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i));

			if (CheckPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i)))//left up
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i));	
		
			if (CheckPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i)))//right up
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i));			
		}
		
		return validMoves;
	
	}
	
	private Boolean CheckPosition(Position p) {
		
		return (p.getRow() >= 0&& p.getColumn() >= 0  && p.getRow()<8 && p.getColumn() <8);
	}

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Bishop(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}


}

