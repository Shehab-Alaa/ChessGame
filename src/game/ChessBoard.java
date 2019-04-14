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
	private static ChessPiece currentPiece;
	
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
		currentPiece = chessPiece;
		
		if (chessPiece instanceof Pawn)
			filterPawnMovements(validPositions);
		
		else if (chessPiece instanceof Bishop)
			filterBishopMovements(validPositions);
		
		else if (chessPiece instanceof Rook)
			filterRookMovements(validPositions);
		
		else if (chessPiece instanceof Knight)
			filterKnightMovements(validPositions);
		
		else if (chessPiece instanceof Queen)
			filterQueenMovements(validPositions);
		
		else if (chessPiece instanceof King)
			filterKingMovements(validPositions);
		
		return validPositions;
	}
	
	private static void filterKingMovements(ArrayList<Position> validPositions) {
		
	}

	private static void filterQueenMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				filterPositionsBlocked(validPositions.indexOf(chessPiece.getCurrentPosition()), validPositions);
				validPositions.remove(chessPiece.getCurrentPosition());
			}
			else
				filterPositionsBlocked(validPositions.indexOf(chessPiece.getCurrentPosition()), validPositions);
		}
	}
	
	private static void filterPositionsBlocked(int indextOfBlockingPiece, ArrayList<Position> validPosition) {
		Position blockingPiece = validPosition.get(indextOfBlockingPiece);
		
		//forward
		if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() == 0)
		{
				for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
					if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() == 0)
						validPosition.remove(i);
		}
		
		//backward
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() == 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() == 0)
					validPosition.remove(i);
		}
		
		//right
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() == 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() == 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
		
		//left
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() == 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() == 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//northwest
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//northeast
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
		
		//southwest
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//southeast
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
	}

	private static void filterKnightMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
				validPositions.remove(chessPiece.getCurrentPosition());
		}
	}
	
	private static void filterRookMovements(ArrayList<Position> validPositions) {
		
	}

	private static void filterBishopMovements(ArrayList<Position> validPositions) {
		
	}

	private static void filterPawnMovements(ArrayList<Position> validPositions) {
		
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
