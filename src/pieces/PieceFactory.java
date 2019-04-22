package pieces;

import java.util.HashMap;

public class PieceFactory {

	private static final HashMap<Integer , ChessPiece> pieces = new HashMap<Integer,ChessPiece>();
	
	public static ChessPiece getPawnPiece()
	{
		Pawn pawn = (Pawn) pieces.get(1);
		
		if (pawn == null)
		{
			pawn = new Pawn(null , null);
			pieces.put(1, pawn);
		}
		return pawn;
	}
	
	public static ChessPiece getKnightPiece()
	{
		Knight knight = (Knight) pieces.get(2);
		
		if (knight == null)
		{
			knight = new Knight(null , null);
			pieces.put(2, knight);
		}
		return knight;
	}
	
	public static ChessPiece getBishopPiece()
	{
		Bishop bishop = (Bishop) pieces.get(3);
		
		if (bishop == null)
		{
			bishop = new Bishop(null , null);
			pieces.put(3, bishop);
		}
		return bishop;
	}
	
	public static ChessPiece getRookPiece()
	{
		Rook rook = (Rook) pieces.get(4);
		
		if (rook == null)
		{
			rook = new Rook(null,null);
			pieces.put(4, null);
		}
		return rook;
	}
	
	public static ChessPiece getQueenPiece()
	{
		Queen queen = (Queen) pieces.get(5);
		
		if (queen == null)
		{
			queen = new Queen(null,null);
			pieces.put(5, queen);
		}
		return queen;
	}
	
	public static ChessPiece getKingPiece()
	{
		King king = (King) pieces.get(6);
		
		if (king == null)
		{
			king = new King(null,null);
			pieces.put(6, king);
		}
		return king;
	}
	
}
