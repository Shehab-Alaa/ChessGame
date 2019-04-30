package pieces;

import java.util.ArrayList;

import extra.Position;

public abstract class ChessPiece implements ChessPieceCloneable{
	protected Position currentPosition;
	protected Position possiblePosition;
	protected String color;
	protected boolean statue;
	protected ArrayList<Position> validMoves;
	protected int pieceValue;
	protected boolean firstMove;
	
	public ChessPiece(Position currentPosition, String color)
	{
		this.currentPosition = currentPosition;
		this.color = color;
		statue = true;
		validMoves = new ArrayList<>();
		firstMove = false;
	}
	
	public abstract ArrayList<Position> getValidMoves();
    
	public void setCurrentPosition(Position currentPosition)
	{
		this.currentPosition = currentPosition;
	}
	
	public void setPieceColor(String color)
	{
		this.color = color;
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

	public int getPieceValue()
	{
		return pieceValue;
	}
	
	public String getPieceColor()
	{
		return color;
	}

	public boolean isFirstMove() {
		return firstMove;
	}

	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
	
}

