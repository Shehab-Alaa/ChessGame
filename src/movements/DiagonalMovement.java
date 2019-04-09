package movements;

import java.util.ArrayList;

import extra.Position;

public interface DiagonalMovement extends Movement{
	ArrayList<Position> getValidDiagonalMoves();
}
