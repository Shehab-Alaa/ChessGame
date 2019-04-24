package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;

public class QueenFilterCriteria implements FilterCriteria{

	@Override
	public ArrayList<Position> filterPositions(ChessPiece chessPieceHolder) {
		ChessBoard.validPositions.addAll(new BishopFilterCriteria().filterPositions(chessPieceHolder));
		ChessBoard.validPositions.addAll(new RookFilterCriteria().filterPositions(chessPieceHolder));
		return ChessBoard.validPositions;
	}

}
