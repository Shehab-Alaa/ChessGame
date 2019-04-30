package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.DiagonalMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;

public class King extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement , DiagonalMovement{

	private ArrayList<Position> movesHolder;

	public King(Position currentPosition, String color) {
		super(currentPosition, color);
		pieceValue = 9;
	}
	
	@Override
	public ArrayList<Position> getValidMoves() {
		validMoves = new ArrayList<Position>(getValidBackwardMoves());
		validMoves.addAll(getValidForwardMoves());
		validMoves.addAll(getValidLeftMoves());
		validMoves.addAll(getValidRightMoves());
		validMoves.addAll(getValidDiagonalMoves());

		return validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		movesHolder =new ArrayList<>();

		fillList(1, 1);
		fillList(1, -1);		
		fillList(-1, -1);
		fillList(-1, 1);

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidLeftMoves() {
		movesHolder =new ArrayList<>();
		
		fillList(0, -1);
		
		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidRightMoves() {		
		movesHolder =new ArrayList<>();
		
		fillList(0, 1);

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		movesHolder =new ArrayList<>();
		
		fillList(1, 0);

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
		movesHolder =new ArrayList<>();
		
		fillList(-1, 0);
	
		return movesHolder;
	}
	
	private boolean check(int i,int j) {return i>=0&&i<8&&j>=0&&j<8;}
	
	private void  fillList(int i,int j) {
		if(check(currentPosition.getRow()+i,currentPosition.getColumn()+j))
			movesHolder.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn()+j));
	}

	@Override
	public ChessPiece cloneChessPiece() {
	    return new King(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}
	
}
