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
	    return getValidLMoves();
	}

	@Override
	public boolean canMove(Position possiblePosition) {
		this.possiblePosition = possiblePosition;
		return validMoves.contains(possiblePosition);
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
	    statue = false;
	}


	@Override
	public ArrayList<Position> getValidLMoves() {

		   int xMoves[] = { 2, 1, -1, -2, -2, -1, 1, 2 }; 
	       int yMoves[] = { 1, 2, 2, 1, -1, -2, -2, -1 }; 
	 
	       for (int i = 0; i < 8; i++) { 
	
	           int x = currentPosition.getRow() + xMoves[i]; 
	           int y = currentPosition.getColumn() + yMoves[i]; 
	
	           if (x >= 0 && y >= 0 && x <= 7  && y <= 7)
	        	   validMoves.add(new Position(x,y));           
	       } 
	       
		return validMoves;
	}
}
