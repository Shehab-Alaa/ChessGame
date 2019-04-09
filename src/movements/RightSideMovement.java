package movements;

import java.util.ArrayList;

import extra.Position;

public interface RightSideMovement extends Movement{
	ArrayList<Position> getValidRightMoves();
}
