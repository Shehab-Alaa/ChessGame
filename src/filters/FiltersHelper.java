package filters;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class FiltersHelper {
   private BishopFilterCriteria bishopFilterCriteria;
   private KingFilterCriteria kingFilterCriteria;
   private KnightFilterCriteria knightFilterCriteria;
   private PawnFilterCriteria pawnFilterCriteria;
   private QueenFilterCriteria queenFilterCriteria;
   private RookFilterCriteria rookFilterCriteria;
   
   public FiltersHelper()
   {
	  bishopFilterCriteria = new BishopFilterCriteria(); 
	  kingFilterCriteria = new KingFilterCriteria();
	  knightFilterCriteria = new KnightFilterCriteria();
	  pawnFilterCriteria = new PawnFilterCriteria();
	  queenFilterCriteria = new QueenFilterCriteria();
	  rookFilterCriteria = new RookFilterCriteria();
   }
   
   public void filterPositions(ChessPiece chessPiece)
   {
	    if (chessPiece instanceof Pawn) 
      	  pawnFilterCriteria.filterPositions(chessPiece);
		else if (chessPiece instanceof Bishop)
      	  bishopFilterCriteria.filterPositions(chessPiece);
		else if (chessPiece instanceof Rook)
      	  rookFilterCriteria.filterPositions(chessPiece);
		else if (chessPiece instanceof Knight)
      	  knightFilterCriteria.filterPositions(chessPiece);
		else if (chessPiece instanceof Queen)
      	  queenFilterCriteria.filterPositions(chessPiece);
		else if (chessPiece instanceof King)
      	  kingFilterCriteria.filterPositions(chessPiece);
   }

}
