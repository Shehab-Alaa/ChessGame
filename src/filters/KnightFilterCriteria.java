package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;

public class KnightFilterCriteria implements FilterCriteria{

	
	@Override
	public ArrayList<Position> filterPositions(ChessPiece chessPieceHolder) {
		     ChessBoard.validPositions = chessPieceHolder.getValidMoves(); 
			for  (ChessPiece chessPiece : ChessBoard.chessPieces)
			{
				if (chessPieceHolder.getPieceColor().equals(chessPiece.getPieceColor()) &&
						!chessPieceHolder.getCurrentPosition().equals(chessPiece.getCurrentPosition()))
				{
					for (int i =0;i<ChessBoard.validPositions.size();i++)
					{
						if (ChessBoard.validPositions.get(i).getRow() == chessPiece.getCurrentPosition().getRow() &&
								ChessBoard.validPositions.get(i).getColumn() == chessPiece.getCurrentPosition().getColumn())
						{
							ChessBoard.validPositions.remove(i);
	                         break;
						}
					}
				}
			}

			return ChessBoard.validPositions;
	}

}
