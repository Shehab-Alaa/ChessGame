package movements;

import java.util.ArrayList;

import extra.Position;

public class RookMovements implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement{

	private Position currentPosition;
	
	public void setCurrentPosition(Position currentPosition)
	{
		this.currentPosition = currentPosition;
	}
	
	@Override
	public ArrayList<Position> getValidLeftMoves() {
		ArrayList<Position>moveLeft = new ArrayList<Position>();
		for(int i=currentPosition.getColumn()-1;i>=0;i--)
		{
			Position a = new Position(currentPosition.getRow(),i);
			moveLeft.add(a);
		}
		
		return moveLeft;
	}

	@Override
	public ArrayList<Position> getValidRightMoves() {
		ArrayList<Position>moveRight = new ArrayList<Position>();
		for(int i=currentPosition.getColumn()+1;i<8;i++)
		{
			Position a = new Position(currentPosition.getRow(),i);
			moveRight.add(a);
		}
		return moveRight;
	}

	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		 ArrayList<Position>moveBackward = new ArrayList<Position>();	
    	 for(int i=currentPosition.getRow()+1;i<8;i++)
	   	{
			Position a = new Position(i,currentPosition.getColumn());
			moveBackward.add(a);
		}
		return moveBackward ;
	}

	@Override
	public ArrayList<Position> getValidForwardMoves() {
		ArrayList<Position>moveForward = new ArrayList<Position>();
		for(int i=currentPosition.getRow()-1;i>=0;i--)
		{
			Position a = new Position(i,currentPosition.getColumn());
			moveForward.add(a);
		}
		return moveForward ;
	}

}
