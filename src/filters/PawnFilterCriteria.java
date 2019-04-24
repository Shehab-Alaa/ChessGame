package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;

public class PawnFilterCriteria implements FilterCriteria{

	@Override
	public ArrayList<Position> filterPositions(ChessPiece chessPieceHolder) {
	     ChessBoard.validPositions = chessPieceHolder.getValidMoves(); 
		for (int i =0;i<ChessBoard.validPositions.size();i++)
		{
	        if(ChessBoard.hasPieceInPositon(ChessBoard.validPositions.get(i)))
	        {
	        	if (ChessBoard.getPiece(ChessBoard.validPositions.get(i)).getPieceColor().equals(chessPieceHolder.getPieceColor()))
	        		ChessBoard.validPositions.remove(i);
	        }
		}
		
		ArrayList<Position> deletedPositions = new ArrayList<>();
		Position positionHolder;
		
		if (chessPieceHolder.getPieceColor().equals("White"))
		{
			if (chessPieceHolder.getCurrentPosition().getRow() == 6)
			{
				positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 2 ,chessPieceHolder.getCurrentPosition().getColumn());
				if (ChessBoard.hasPieceInPositon(positionHolder))
				  deletedPositions.add(positionHolder);	
			}
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 1,chessPieceHolder.getCurrentPosition().getColumn());
			if (ChessBoard .hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 1,chessPieceHolder.getCurrentPosition().getColumn() + 1);
			if (!ChessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 1,chessPieceHolder.getCurrentPosition().getColumn() - 1);
			if (!ChessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
		}
		else if (chessPieceHolder.getPieceColor().equals("Black"))
		{
			if (chessPieceHolder.getCurrentPosition().getRow() == 1)
			{
				positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 2,chessPieceHolder.getCurrentPosition().getColumn());
				if (ChessBoard.hasPieceInPositon(positionHolder))
					deletedPositions.add(positionHolder);
			}
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 1,chessPieceHolder.getCurrentPosition().getColumn());
			if (ChessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 1,chessPieceHolder.getCurrentPosition().getColumn() + 1);
			if (!ChessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 1,chessPieceHolder.getCurrentPosition().getColumn() - 1);
			if (!ChessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
		}
		
		for (Position position : deletedPositions)
		{
			for (int i =0;i<ChessBoard.validPositions.size();i++)
			{
				if (ChessBoard.validPositions.get(i).getRow() == position.getRow() && ChessBoard.validPositions.get(i).getColumn() == position.getColumn())
				{
					ChessBoard.validPositions.remove(i);
					break;
				}
			}
		}
		return ChessBoard.validPositions;
	}

}
