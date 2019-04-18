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
		validMoves = new ArrayList<>();
		super.validMoves.addAll(getValidDiagonalMoves());
		return super.validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> logicalMoves =new ArrayList<Position>();
			for(int i=1;i<7;i++)
			{
					if(currentPosition.getRow()<7&&currentPosition.getColumn()<7)
					{
						Position p = new Position(currentPosition.getRow()+i,currentPosition.getColumn()+i);
						logicalMoves.add(p);
					}
					if(currentPosition.getRow()<7&&currentPosition.getColumn()>0)
					{
						Position p1 = new Position(currentPosition.getRow()+i,currentPosition.getColumn()-i);
						logicalMoves.add(p1);
					}
					if(currentPosition.getRow()>0&&currentPosition.getColumn()>0)
					{
						Position p2 = new Position(currentPosition.getRow()-i,currentPosition.getColumn()-i);
						logicalMoves.add(p2);
					}
					if(currentPosition.getRow()>0&&currentPosition.getColumn()<7)
					{
						Position p3 = new Position(currentPosition.getRow()-i,currentPosition.getColumn()+i);
						logicalMoves.add(p3);
					}
			}
		
		return logicalMoves;
	}

}

