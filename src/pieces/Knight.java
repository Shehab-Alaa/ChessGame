package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.LMovement;

public class Knight extends ChessPiece implements LMovement {

	public Knight(Position currentPosition, String color) {
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
	public ArrayList<Position> getValidLMoves() {
		
		return null;
	}
}
