package pieces;


import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;
import movements.RookMovements;

public class Rook extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement{

	private RookMovements rookMovements;
	
	public Rook(Position currentPosition, String color) {
		super(currentPosition, color);
		rookMovements = new RookMovements();
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
		 rookMovements.setCurrentPosition(currentPosition);
		return rookMovements.getValidLeftMoves();
	}

	@Override
	public ArrayList<Position> getValidRightMoves() {
		 rookMovements.setCurrentPosition(currentPosition);
		return rookMovements.getValidRightMoves();
	}

	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		rookMovements.setCurrentPosition(currentPosition);
       return rookMovements.getValidBackwardMoves();
	}

	@Override
	public ArrayList<Position> getValidForwardMoves() {
		rookMovements.setCurrentPosition(currentPosition);
	   return rookMovements.getValidForwardMoves();
	}

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Rook(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}

}