package movements;

import java.util.ArrayList;

import extra.Position;

public interface ForwardMovement extends Movement{
    ArrayList<Position> getValidForwardMoves();
}
