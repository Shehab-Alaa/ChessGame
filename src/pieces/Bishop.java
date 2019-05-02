package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.BishopMovements;
import movements.DiagonalMovement;

public class Bishop extends ChessPiece implements DiagonalMovement{

	private BishopMovements bishopMovements;
	
	public Bishop(Position currentPosition, String color) {
		super(currentPosition, color);
		bishopMovements = new BishopMovements();
		pieceValue = 3;
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		validMoves = new ArrayList<>();
		super.validMoves.addAll(getValidDiagonalMoves());
		return super.validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		bishopMovements.setCurrentPosition(currentPosition);
		return bishopMovements.getValidDiagonalMoves();
	}

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Bishop(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}


}

