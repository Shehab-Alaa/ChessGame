package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;

public class QueenFilterCriteria implements FilterCriteria{

	private ChessBoard chessBoard = ChessBoard.getChessBoardInstance();
	
	@Override
	public void  filterPositions(ChessPiece chessPieceHolder) {
		new BishopFilterCriteria().filterPositions(chessPieceHolder);
		new RookFilterCriteria().filterPositions(chessPieceHolder,chessBoard.getValidPositonsArray());	
	}

}
