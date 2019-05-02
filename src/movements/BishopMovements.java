package movements;

import java.util.ArrayList;

import extra.Position;

public class BishopMovements implements DiagonalMovement{

	private Position currentPosition;
	
	public void setCurrentPosition(Position currentPosition)
	{
		this.currentPosition = currentPosition;
	}
	
	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		
		ArrayList<Position> validMoves = new ArrayList<Position>();
		for (int i=1; i<7; i++)
		{
			if (checkPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i)))//left down
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() -i));
			
			if (checkPosition(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i)))//right down
				validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn() +i));

			if (checkPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i)))//left up
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() -i));	
		
			if (checkPosition(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i)))//right up
				validMoves.add(new Position(currentPosition.getRow()-i, currentPosition.getColumn() +i));			
		}
		return validMoves;
	}

     private Boolean checkPosition(Position p) {
		
		return (p.getRow() >= 0&& p.getColumn() >= 0  && p.getRow()<8 && p.getColumn() <8);
	}
}
