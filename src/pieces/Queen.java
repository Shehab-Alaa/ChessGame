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
	public ArrayList<Position> getValidLeftMoves() {
	
		return null;
	}


	@Override
	public ArrayList<Position> getValidRightMoves() {
		
		return null;
	}


	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		
		return null;
	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
		
		return null;
	}

}
