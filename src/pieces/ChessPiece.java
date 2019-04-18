package pieces;

import java.util.ArrayList;

import extra.Position;

public abstract class ChessPiece {

	protected Position currentPosition;
	protected Position possiblePosition;
	protected final String color;
	protected boolean statue;
	protected ArrayList<Position> validMoves;
	
	public ChessPiece(Position currentPosition, String color)
	{
		this.currentPosition = currentPosition;
		this.color = color;
		statue = true;
		validMoves = new ArrayList<>();
	}
	
	public abstract ArrayList<Position> getValidMoves();
    
	public void setCurrentPosition(Position currentPosition)
	{
		this.currentPosition = currentPosition;
	}
	
	public void makeMove()
	{
		
	}
	
	public void capture()
	{
		
	}
	
	public void captured()
	{
		statue = false;
	}
	
	public Position getCurrentPosition()
	{
		return currentPosition;
	}

	public String getPieceColor()
	{
		return color;
	}
	
}

