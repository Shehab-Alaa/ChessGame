package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public interface FilterCriteria {
	final ChessBoard chessBoard = ChessBoard.getChessBoardInstance();
    void filterPositions(ChessPiece chessPiece);
}
