package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.DiagonalMovement;
import movements.ForwardMovement;

public class Pawn extends ChessPiece implements ForwardMovement , DiagonalMovement{

	public Pawn(Position currentPosition, String color) {
		super(currentPosition, color);
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		
		validMoves.addAll(getValidForwardMoves());
		validMoves.addAll(getValidDiagonalMoves());
		return validMoves;
	}


	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> logicalMoves =new ArrayList<Position>();
		Position p = new Position(currentPosition.getRow()+1,currentPosition.getColumn()-1);//left for white
		logicalMoves.add(p);
		Position p1 = new Position(currentPosition.getRow()+1,currentPosition.getColumn()+1);//right for white
		logicalMoves.add(p1);
		Position p2 = new Position(currentPosition.getRow()-1,currentPosition.getColumn()-1);//right for black
		logicalMoves.add(p2);
		Position p3 = new Position(currentPosition.getRow()-1,currentPosition.getColumn()+1);// left for black
		logicalMoves.add(p3);
		return logicalMoves;
	}

	@Override
	public ArrayList<Position> getValidForwardMoves() {
		ArrayList<Position> logicalMoves =new ArrayList<Position>();
		if(color.equals("White"))
			{
				Position p=new Position(currentPosition.getRow()+1,currentPosition.getColumn());
				logicalMoves.add(p);
				Position p1=new Position(currentPosition.getRow()+2,currentPosition.getColumn());
				logicalMoves.add(p1);
			}
		else 
		{
			Position p2=new Position(currentPosition.getRow()-1,currentPosition.getColumn());
			logicalMoves.add(p2);
			Position p3=new Position(currentPosition.getRow()-2,currentPosition.getColumn());
			logicalMoves.add(p3);
		}
		return logicalMoves;
	}

}
