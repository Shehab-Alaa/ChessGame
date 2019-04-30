package pieces;


import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;

public class Rook extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement{

	public Rook(Position currentPosition, String color) {
		super(currentPosition, color);
		pieceValue = 5;
	}
	
	@Override
	public ArrayList<Position> getValidMoves() {
	  validMoves = new ArrayList<>();
	  validMoves.addAll(getValidRightMoves());
	  validMoves.addAll(getValidBackwardMoves());
	  validMoves.addAll(getValidLeftMoves());
	  validMoves.addAll(getValidForwardMoves());
	
		return validMoves;
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

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Rook(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}

}