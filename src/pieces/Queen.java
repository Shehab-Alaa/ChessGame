package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.DiagonalMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;

public class Queen extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement , DiagonalMovement {

	public Queen(Position currentPosition, String color) {
		super(currentPosition, color);
	}
	

	@Override
	public ArrayList<Position> getValidMoves() {
		validMoves = new ArrayList<>();
		validMoves = getValidForwardMoves();
		validMoves.addAll(getValidBackwardMoves());
		validMoves.addAll(getValidLeftMoves());
		validMoves.addAll(getValidRightMoves());
		validMoves.addAll(getValidDiagonalMoves());
		return validMoves;
	}


	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=1; i<=8; i++)
		{
			//northwest
			if (checkPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i)))
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i));
			
			//northeast
			if (checkPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i)))
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i));
			
			//southwest
			if (checkPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i)))
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i));
			
			//southeast
			if (checkPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i)))
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i));
		}
		
		return validMoves;
	
	}


	@Override
	public ArrayList<Position> getValidLeftMoves() {
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=0; i<currentPosition.getColumn(); i++)
			validMoves.add(new Position(currentPosition.getRow(),i));
		return validMoves;
	}


	@Override
	public ArrayList<Position> getValidRightMoves() {
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i= currentPosition.getColumn()+1; i<=7; i++)
			validMoves.add(new Position(currentPosition.getRow(),i));
		return validMoves;
	}


	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=currentPosition.getRow()+1; i<=7; i++)
			validMoves.add(new Position(i, currentPosition.getColumn()));
		return validMoves;
	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=0; i<currentPosition.getRow(); i++)
			validMoves.add(new Position(i, currentPosition.getColumn()));
		return validMoves;
	}

	private Boolean checkPosition(Position p) {
		
		return (p.getRow() >= 0 && p.getRow()<8 && p.getColumn() >= 0 && p.getColumn() <8);
	}
}
