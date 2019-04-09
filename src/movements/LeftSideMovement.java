package movements;

import java.util.ArrayList;

import extra.Position;

public interface LeftSideMovement extends Movement{
	ArrayList<Position> getValidLeftMoves();
}
