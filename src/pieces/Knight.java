package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.LMovement;

public class Knight extends ChessPiece implements LMovement {

	public Knight(Position currentPosition, String color) {
		super(currentPosition, color);
		pieceValue = 3;
	}

	@Override
	public ArrayList<Position> getValidMoves() {
	    return getValidLMoves();
	}

	@Override
	public ArrayList<Position> getValidLMoves() {

		validMoves = new ArrayList<>();
		
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

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Knight(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}
}
