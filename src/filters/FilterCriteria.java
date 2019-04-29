package filters;

import java.util.ArrayList;

import extra.Position;
import pieces.ChessPiece;

public interface FilterCriteria {
    void filterPositions(ChessPiece chessPiece);
}
