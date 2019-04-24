package filters;

import java.util.ArrayList;

import extra.Position;
import pieces.ChessPiece;

public interface FilterCriteria {
    ArrayList<Position> filterPositions(ChessPiece chessPiece);
}
