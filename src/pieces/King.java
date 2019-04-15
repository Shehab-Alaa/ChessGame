package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.BackwardMovement;
import movements.DiagonalMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;

public class King extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement , DiagonalMovement{


	public King(Position currentPosition, String color) {
		super(currentPosition, color);
	}
	@Override
	public ArrayList<Position> getValidMoves() {

		validMoves = getValidBackwardMoves();
		validMoves.addAll(getValidForwardMoves());
		validMoves.addAll(getValidLeftMoves());
		validMoves.addAll(getValidRightMoves());
		validMoves.addAll(getValidDiagonalMoves());

		return validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> movesHolder =new ArrayList<>();

		fillList(1, 1);
		fillList(-1, -1);
		fillList(-1, 1);
		fillList(1, -1);		

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidLeftMoves() {
		ArrayList<Position> movesHolder =new ArrayList<>();
		
		fillList(0, -1);
		
		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidRightMoves() {		
		ArrayList<Position> movesHolder =new ArrayList<>();
		
		fillList(0, 1);

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidBackwardMoves() {
		ArrayList<Position> movesHolder =new ArrayList<>();
		
		fillList(1, 0);

		return movesHolder;
	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
		ArrayList<Position> movesHolder =new ArrayList<>();
		
		fillList(-1, 0);
	
		return movesHolder;
	}
	
	private boolean check(int i,int j) {return i>=0&&i<8&&j>=0&&j<8;}
	
	private void  fillList(int i,int j) {
		if(check(currentPosition.getRow()+i,currentPosition.getColumn()+j))
			validMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn()+j));
	}
	
}
