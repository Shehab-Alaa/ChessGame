package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public class KnightFilterCriteria implements FilterCriteria{
	
	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {
		  
		   chessBoard.setValidPositions(chessPieceHolder.getValidMoves());
		     
			for  (ChessPiece chessPiece : chessBoard.getChessPieces())
			{
				if (chessPieceHolder.getPieceColor().equals(chessPiece.getPieceColor()) &&
						!chessPieceHolder.getCurrentPosition().equals(chessPiece.getCurrentPosition()))
				{
					for (int i =0;i<chessBoard.getValidPositonsArray().size();i++)
					{
						if (chessBoard.getValidPositonsArray().get(i).getRow() == chessPiece.getCurrentPosition().getRow() &&
								chessBoard.getValidPositonsArray().get(i).getColumn() == chessPiece.getCurrentPosition().getColumn())
						{
							chessBoard.getValidPositonsArray().remove(i);
	                         break;
						}
					}
				}
			}
	}

}
