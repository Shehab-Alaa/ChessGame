package pieces;

import java.util.ArrayList;

import extra.Position;

public abstract class ChessPiece {

	private Position currentPosition;
	private Position possiblePosition;
	private final String color;
	private boolean statue;
	private ArrayList<Position> validMoves;
	
	public ChessPiece(Position currentPosition, String color)
	{
		this.currentPosition = currentPosition;
		this.color = color;
		statue = true;
		validMoves = new ArrayList<>();
	}
	
	public abstract ArrayList<Position> getValidMoves();
	public abstract boolean canMove(Position possiblePosition);
	public abstract void backToCurrentPosition();
	public abstract void makeMove();
	public abstract void capture();
	public abstract void captured();
	
	
}
