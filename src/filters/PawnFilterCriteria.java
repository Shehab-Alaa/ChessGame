package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public class PawnFilterCriteria implements FilterCriteria{
	
	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {

	    chessBoard.setValidPositions(chessPieceHolder.getValidMoves());
	    
		for (int i =0;i<chessBoard.getValidPositonsArray().size();i++)
		{
	        if(chessBoard.hasPieceInPositon(chessBoard.getValidPositonsArray().get(i)))
	        {
	        	if (chessBoard.getPiece(chessBoard.getValidPositonsArray().get(i)).getPieceColor().equals(chessPieceHolder.getPieceColor()))
	        		chessBoard.getValidPositonsArray().remove(i);
	        }
		}
		
		ArrayList<Position> deletedPositions = new ArrayList<>();
		Position positionHolder;
		
		if (chessPieceHolder.getPieceColor().equals("White"))
		{
			if (chessPieceHolder.getCurrentPosition().getRow() == 6)
			{
				positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 2 ,chessPieceHolder.getCurrentPosition().getColumn());
				if (chessBoard.hasPieceInPositon(positionHolder) || chessBoard.hasPieceInPositon(new Position(chessPieceHolder.getCurrentPosition().getRow() - 1,chessPieceHolder.getCurrentPosition().getColumn())))
				  deletedPositions.add(positionHolder);	
			}
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() - 1,chessPieceHolder.getCurrentPosition().getColumn());
			if (chessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
			
			int index = -1;
			for (int i = 0 ;i < 2;i++)
			{
				positionHolder = new Position((chessPieceHolder.getCurrentPosition().getRow() - 1),(chessPieceHolder.getCurrentPosition().getColumn() + index));
				if (!chessBoard.hasPieceInPositon(positionHolder))
					deletedPositions.add(positionHolder);
				index = 1;
			}
			
		}
		else if (chessPieceHolder.getPieceColor().equals("Black"))
		{
			if (chessPieceHolder.getCurrentPosition().getRow() == 1)
			{
				positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 2,chessPieceHolder.getCurrentPosition().getColumn());
				if (chessBoard.hasPieceInPositon(positionHolder) || chessBoard.hasPieceInPositon(new Position(chessPieceHolder.getCurrentPosition().getRow() + 1,chessPieceHolder.getCurrentPosition().getColumn())))
					deletedPositions.add(positionHolder);
			}
			
			positionHolder = new Position(chessPieceHolder.getCurrentPosition().getRow() + 1,chessPieceHolder.getCurrentPosition().getColumn());
			if (chessBoard.hasPieceInPositon(positionHolder))
				deletedPositions.add(positionHolder);
				
			int index = -1;
			for (int i = 0 ;i < 2;i++)
			{
				positionHolder = new Position((chessPieceHolder.getCurrentPosition().getRow() + 1),(chessPieceHolder.getCurrentPosition().getColumn() + index));
				if (!chessBoard.hasPieceInPositon(positionHolder))
					deletedPositions.add(positionHolder);
				index = 1;
			}
		}
		
		for (Position position : deletedPositions)
		{
			for (int i =0;i<chessBoard.getValidPositonsArray().size();i++)
			{
				if (chessBoard.getValidPositonsArray().get(i).getRow() == position.getRow() && chessBoard.getValidPositonsArray().get(i).getColumn() == position.getColumn())
				{
					chessBoard.getValidPositonsArray().remove(i);
					break;
				}
			}
		}
	}

}
