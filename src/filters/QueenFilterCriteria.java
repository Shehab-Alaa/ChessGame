package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public class QueenFilterCriteria implements FilterCriteria{
	
	@Override
	public void  filterPositions(ChessPiece chessPieceHolder) {
		new BishopFilterCriteria().filterPositions(chessPieceHolder);
		new RookFilterCriteria().filterPositions(chessPieceHolder,chessBoard.getValidPositonsArray());	
	}

}
