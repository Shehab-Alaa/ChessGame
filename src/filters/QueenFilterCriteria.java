package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public class QueenFilterCriteria implements FilterCriteria{
	
	private BishopFilterCriteria bishopFilterCriteria = new BishopFilterCriteria();
	private RookFilterCriteria rookFilterCriteria = new RookFilterCriteria();
	
	@Override
	public void  filterPositions(ChessPiece chessPieceHolder) {
		bishopFilterCriteria.filterPositions(chessPieceHolder);
		rookFilterCriteria.filterPositions(chessPieceHolder,chessBoard.getValidPositonsArray());	
	}

}
