package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.DiagonalMovement;

public class Bishop extends ChessPiece implements DiagonalMovement{

	public Bishop(Position currentPosition, String color) {
		super(currentPosition, color);
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		super.validMoves.addAll(getValidDiagonalMoves());
		return super.validMoves;
	}

	
	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> logicalMoves =new ArrayList<Position>();
		for(int i=0;i<8;i++)
		{
			Position p = new Position(super.currentPosition.getRow()+i,super.currentPosition.getColumn()+i);//right up
			logicalMoves.add(p);
			Position p1 = new Position(super.currentPosition.getRow()+i,super.currentPosition.getColumn()-i);//left up
			logicalMoves.add(p1);
			Position p2 = new Position(super.currentPosition.getRow()-i,super.currentPosition.getColumn()-i);//left down
			logicalMoves.add(p2);
			Position p3 = new Position(super.currentPosition.getRow()-i,super.currentPosition.getColumn()+i);//right down
			logicalMoves.add(p3);
		}
		return logicalMoves;
	}

}

