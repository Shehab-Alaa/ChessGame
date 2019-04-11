package game;

import java.util.ArrayList;

import extra.Position;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard {

	private static ChessBoard chessBoard;
	private static ArrayList<ChessPiece> chessPieces;
	private static ArrayList<ChessPiece> capturedPieces;
	
	public static ChessBoard getChessBoard()
	{
		if (chessBoard == null)
			chessBoard = new ChessBoard();
		return chessBoard;
	}
	
	private ChessBoard()
	{
		chessPieces = new ArrayList<>();
		capturedPieces = new ArrayList<>();
		
		chessPieces.add(new Rook(new Position(7,0) , "White"));
		chessPieces.add(new Knight(new Position(7,1) , "White"));
		chessPieces.add(new Bishop(new Position(7,2) , "White"));
		chessPieces.add(new Queen(new Position(7,3) , "White"));
		chessPieces.add(new King(new Position(7,4) , "White"));
		chessPieces.add(new Bishop(new Position(7,5) , "White"));
		chessPieces.add(new Knight(new Position(7,6) , "White"));
		chessPieces.add(new Rook(new Position(7,7) , "White"));

		for(int i=0;i<8;i++)
		{
			chessPieces.add(new Pawn(new Position(6,i) , "White"));
		}
		
		chessPieces.add(new Rook(new Position(0,0) , "Black"));
		chessPieces.add(new Knight(new Position(0,1) , "Black"));
		chessPieces.add(new Bishop(new Position(0,2) , "Black"));
		chessPieces.add(new Queen(new Position(0,3) , "Black"));
		chessPieces.add(new King(new Position(0,4) , "Black"));
		chessPieces.add(new Bishop(new Position(0,5) , "Black"));
		chessPieces.add(new Knight(new Position(0,6) , "Black"));
		chessPieces.add(new Rook(new Position(0,7) , "Black"));

		for(int i=0;i<8;i++)
		{
			chessPieces.add(new Pawn(new Position(1,i) , "Black"));
		}
	}
	
	public static ChessPiece getPiece(Position position)
	{
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getCurrentPosition().equals(position))
				return chessPiece;
		}
		return null;
	}
	
	public static ArrayList<Position> getValidPositions(ChessPiece chessPiece)
	{
		ArrayList<Position> validPositions = chessPiece.getValidMoves();
		
		if (chessPiece instanceof Pawn)
			filterPawnMovements(validPositions , chessPiece.getPieceColor());
		
		else if (chessPiece instanceof Bishop)
			filterBishopMovements(validPositions , chessPiece.getPieceColor());
		
		else if (chessPiece instanceof Rook)
			filterRookMovements(validPositions , chessPiece.getPieceColor());
		
		else if (chessPiece instanceof Knight)
			filterKnightMovements(validPositions , chessPiece.getPieceColor());
		
		else if (chessPiece instanceof Queen)
			filterQueenMovements(validPositions , chessPiece.getPieceColor());
		
		else if (chessPiece instanceof King)
			filterKingMovements(validPositions , chessPiece.getPieceColor());
		
		return validPositions;
	}
	
	private static void filterKingMovements(ArrayList<Position> validPositions , String pieceColor) {
		
	}

	private static void filterQueenMovements(ArrayList<Position> validPositions , String pieceColor) {
		
	}

	private static void filterKnightMovements(ArrayList<Position> validPositions , String pieceColor) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(pieceColor))
				validPositions.remove(chessPiece.getCurrentPosition());
		}
	}

	private static void filterRookMovements(ArrayList<Position> validPositions , String pieceColor) {
		
	}

	private static void filterBishopMovements(ArrayList<Position> validPositions , String pieceColor) {
		
	}

	private static void filterPawnMovements(ArrayList<Position> validPositions , String pieceColor) {
		
	}

	public static boolean hasPieceInPositon(Position position)
	{
       if(getPiece(position).equals(null))
    	   return false;
       return true;
	}
	
	public static void pieceCaptured(ChessPiece chessPiece)
	{
		chessPieces.remove(chessPiece);
		chessPiece.captured();
		capturedPieces.add(chessPiece);
	}
	
	public static void resetBoard()
	{
		chessBoard = new ChessBoard();
	}
}
