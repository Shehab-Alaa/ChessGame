package pieces;

import java.util.ArrayList;

import extra.Position;
import game.Board;
import movements.BackwardMovement;
import movements.DiagonalMovement;
import movements.ForwardMovement;
import movements.LeftSideMovement;
import movements.RightSideMovement;

public class King extends ChessPiece implements ForwardMovement , BackwardMovement , RightSideMovement , LeftSideMovement , DiagonalMovement{

	private ArrayList<Position> ValidMoves;

	public King(Position currentPosition, String color) {
		super(currentPosition, color);
	}
	@Override
	public ArrayList<Position> getValidMoves() {
		ValidMoves=getValidBackwardMoves();
		ValidMoves.addAll(getValidForwardMoves());
		ValidMoves.addAll(getValidLeftMoves());
		ValidMoves.addAll(getValidRightMoves());
		ValidMoves.addAll(getValidDiagonalMoves());

		return ValidMoves;
	}

	@Override
	public boolean canMove(Position possiblePosition) {
		
		return false;
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
		
		
	}

	private boolean checkmate()
	{
		
		return false;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ValidMoves=new ArrayList<Position>();

		fillList(1, 1);
		fillList(-1, -1);
		fillList(-1, 1);
		fillList(1, -1);		

		return ValidMoves;
	}


	@Override
	public ArrayList<Position> getValidLeftMoves() {
		ValidMoves=new ArrayList<Position>();
		
		fillList(0, -1);
		
		return ValidMoves;
	}


	@Override
	public ArrayList<Position> getValidRightMoves() {		
        ValidMoves=new ArrayList<Position>();
		
		fillList(0, 1);

		return ValidMoves;
	}


	@Override
	public ArrayList<Position> getValidBackwardMoves() {
        ValidMoves=new ArrayList<Position>();
		
		fillList(1, 0);

		return ValidMoves;
	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
        ValidMoves=new ArrayList<Position>();
		
		fillList(-1, 0);
	
		return ValidMoves;
	}
	private boolean check(int i,int j) {return i>=0&&i<8&&j>=0&&j<8;}
	
	private void  fillList(int i,int j) {
		if(check(currentPosition.getRow()+i,currentPosition.getColumn()+j))
			ValidMoves.add(new Position(currentPosition.getRow()+i, currentPosition.getColumn()+j));
	}
	
}
