package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.BishopMovements;
import movements.DiagonalMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;
import movements.RookMovements;

public class Queen extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement , DiagonalMovement {

	private BishopMovements bishopMovements;
	private RookMovements rookMovements;
	
	public Queen(Position currentPosition, String color) {
		super(currentPosition, color);
		bishopMovements = new BishopMovements();
		rookMovements = new RookMovements();
		pieceValue = 7;
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
		bishopMovements.setCurrentPosition(currentPosition);
	   return bishopMovements.getValidDiagonalMoves();
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
	    return new Queen(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}
}
