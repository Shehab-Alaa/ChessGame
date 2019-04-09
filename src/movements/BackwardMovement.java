package movements;

import java.util.ArrayList;

import extra.Position;

public interface BackwardMovement extends Movement{
	ArrayList<Position> getValidBackwardMoves();
}
