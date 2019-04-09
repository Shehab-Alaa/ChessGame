package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.DiagonalMovement;
import movements.ForwardMovement;

public class Pawn extends ChessPiece implements ForwardMovement , DiagonalMovement{

	public Pawn(Position currentPosition, String color) {
		super(currentPosition, color);
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		
		return null;
	}

	@Override
	public boolean canMove(Position possiblePosition) {
		
		return false;
	}

	@Override
	public void backToCurrentPosition() {
		
		
	}

	@Override
	public void makeMove() {
		
		
	}

	@Override
	public void capture() {
		
		
	}

	@Override
	public void captured() {
	
		
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		
		return null;
	}

	@Override
	public ArrayList<Position> getValidForwardMoves() {
		
		return null;
	}

}
