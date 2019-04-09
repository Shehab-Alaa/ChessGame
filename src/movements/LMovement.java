package movements;

import java.util.ArrayList;

import extra.Position;

public interface LMovement extends Movement{
	ArrayList<Position> getValidLMoves();
}
