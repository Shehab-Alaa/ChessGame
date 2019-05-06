package pieces;

import java.util.ArrayList;

import extra.Position;
import movements.DiagonalMovement;
import movements.ForwardMovement;

public class Pawn extends ChessPiece implements ForwardMovement , DiagonalMovement{

	public Pawn(Position currentPosition, String color) {
		super(currentPosition, color);
		pieceValue = 1;
	}

	@Override
	public ArrayList<Position> getValidMoves() {
		validMoves = new ArrayList<>();
		validMoves.addAll(getValidForwardMoves());
		validMoves.addAll(getValidDiagonalMoves());
		return validMoves;
	}

	@Override
	public ArrayList<Position> getValidDiagonalMoves() {
		ArrayList<Position> pawndiagonal =new ArrayList<Position>();
		
		if(color.equals("White"))
		{
			if(currentPosition.getRow()<7&&currentPosition.getColumn()>0)
			{
				Position p = new Position(currentPosition.getRow()-1,currentPosition.getColumn()-1);
				 pawndiagonal.add(p);
			}
			if(currentPosition.getRow()<7&&currentPosition.getColumn()<7)
			{
				Position p1 = new Position(currentPosition.getRow()-1,currentPosition.getColumn()+1);
				 pawndiagonal.add(p1);
			}
		}
		else
		{
			if(currentPosition.getRow()>0&&currentPosition.getColumn()>0)
			{
				Position p2 = new Position(currentPosition.getRow()+1,currentPosition.getColumn()-1);
				 pawndiagonal.add(p2);
			}
			if(currentPosition.getRow()>0&&currentPosition.getColumn()<7)
			{
				Position p3 = new Position(currentPosition.getRow()+1,currentPosition.getColumn()+1);
				 pawndiagonal.add(p3);
			}
		}
		return pawndiagonal;

	}


	@Override
	public ArrayList<Position> getValidForwardMoves() {
		ArrayList<Position> pawnforward =new ArrayList<Position>();
		if(color.equals("White"))
		{
			if(currentPosition.getRow()<7&&currentPosition.getRow()>0)
				{
					Position p=new Position(currentPosition.getRow()-1,currentPosition.getColumn());
					pawnforward.add(p);
				}
			if(currentPosition.getRow()==6)/////////////2 steps
			    {
					Position p1=new Position(currentPosition.getRow()-2,currentPosition.getColumn());
					pawnforward.add(p1);
			    }
		}
		else 
		{
			if(currentPosition.getRow()>0&&currentPosition.getRow()<7)
			{
				Position p2=new Position(currentPosition.getRow()+1,currentPosition.getColumn());
				pawnforward.add(p2);
			}
			if(currentPosition.getRow()==1)
				{
					Position p3=new Position(currentPosition.getRow()+2,currentPosition.getColumn());
					pawnforward.add(p3);
				}
		}
		return pawnforward ;
	}

	@Override
	public ChessPiece cloneChessPiece() {
	    return new Pawn(new Position(currentPosition.getRow() , currentPosition.getColumn()), color);
	}





}
