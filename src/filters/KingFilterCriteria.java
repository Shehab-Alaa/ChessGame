package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;

public class KingFilterCriteria implements FilterCriteria{

	private static int counterFrind=0,counterEnemy=0;
		
	@Override
	public ArrayList<Position> filterPositions(ChessPiece chessPieceHolder) {
		counterFrind=0;
		counterEnemy=0;
		ChessBoard.validPositions = chessPieceHolder.getValidMoves(); 
		//ArrayList<Position> validPosition,kingvalidPosition;
		for (int i=0;i< ChessBoard.chessPieces.size();i++){
			if (ChessBoard.chessPieces.get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())){				
				for(int j=0;j<ChessBoard.validPositions.size();) 
					if(ChessBoard.validPositions.get(j).getColumn()==ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn()&&ChessBoard.validPositions.get(j).getRow()==ChessBoard.chessPieces.get(i).getCurrentPosition().getRow()){
						counterFrind++;
						ChessBoard.validPositions.remove(j);
				       }
					else j++;
				}
			else if(!ChessBoard.chessPieces.get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())) {
				filterEnemyPositions(ChessBoard.chessPieces.get(i), chessPieceHolder, ChessBoard.validPositions);
			}
		}
		return ChessBoard.validPositions;
	}
	
	public static ChessPiece getOppositeKingPiece(String color)
	{
		for (ChessPiece chessPiece : ChessBoard.chessPieces)
		{
			if (chessPiece instanceof King &&chessPiece.getPieceColor()!=color)
				return chessPiece;
		}
		return null;
	}
			
    public static ChessPiece getKingPiece(String color)
	{
		for (ChessPiece chessPiece : ChessBoard.chessPieces)
		{
			if (chessPiece instanceof King &&chessPiece.getPieceColor()==color)
				return chessPiece;
		}
		return null;
	}
	    
    private static boolean filterationHelper(Position position,ArrayList<Position> bigvalidpositions,int indexofbigvalidpositions) {
		ArrayList<Position> enemyValidPositions,currentPieceValidPositions;
		Position current = ChessBoard.currentPiece.getCurrentPosition();
		for(int i=0;i<ChessBoard.chessPieces.size();i++) {
			
			if(ChessBoard.chessPieces.get(i).getPieceColor()!=ChessBoard.currentPiece.getPieceColor()&&(!(ChessBoard.chessPieces.get(i) instanceof King)&&!(ChessBoard.chessPieces.get(i) instanceof Knight))) {
				ChessBoard.currentPiece.setCurrentPosition(position); 					
				ChessPiece king = getKingPiece(ChessBoard.currentPiece.getPieceColor());
				currentPieceValidPositions=new ArrayList<Position>(ChessBoard.validPositions);
					enemyValidPositions = ChessBoard.filter(ChessBoard.chessPieces.get(i));
					ChessBoard.validPositions=currentPieceValidPositions;
					for(int j=0;j<enemyValidPositions.size();j++) {
						if(enemyValidPositions.get(j).getRow()==king.getCurrentPosition().getRow()&&enemyValidPositions.get(j).getColumn()==king.getCurrentPosition().getColumn()){
							bigvalidpositions.remove(indexofbigvalidpositions);
							ChessBoard.currentPiece.setCurrentPosition(current);
							return true;
							}								
				}
				
			}
		}
		ChessBoard.currentPiece.setCurrentPosition(current);
		return false;
	}
	
    public static ArrayList<Position> checkKingProtection(ArrayList<Position> validPositions) {
	  for(int i=0;i<validPositions.size();) {
		  if(!filterationHelper(validPositions.get(i),validPositions,i)) 
			  i++;		  
	  }
	  return validPositions;
	  
	  
 }
  
    public static boolean Checkmate(ChessPiece kingChessPiece){
 		ArrayList<Position>currentPiecevalidPosition=new ArrayList<Position>(ChessBoard.validPositions);
 		ChessBoard.validPositions = ChessBoard.filter(kingChessPiece); 
 			
 					int length= kingChessPiece.getValidMoves().size();
 					
 		if(length!=counterFrind	 && length == counterFrind + counterEnemy) {
 			ChessBoard.validPositions=currentPiecevalidPosition;
 			return true;
 		}
 		
 		ChessBoard.validPositions=currentPiecevalidPosition;
 		return false ;
 		}

    private static void filterEnemyPositions(ChessPiece enemy,ChessPiece King,ArrayList<Position> validPositionsOfKing) {
		ArrayList<Position> validPositionsHolder;
		if(enemy instanceof King) {
			for(int j=0;j<validPositionsOfKing.size();) {
				double length=Math.floor((Math.sqrt((Math.pow((validPositionsOfKing.get(j).getRow()-enemy.getCurrentPosition().getRow()), 2))
				+(Math.pow((validPositionsOfKing.get(j).getColumn()-enemy.getCurrentPosition().getColumn()), 2)))));
				if(length==1.0)						       
					{counterEnemy++;
					ChessBoard.validPositions.remove(j);
					}
			    else j++;
			}
		}
	else {

		validPositionsHolder=ChessBoard.filter(enemy);

		for(int j=0;j< validPositionsHolder.size();j++) {				
			for(int ii=0;ii<validPositionsOfKing.size();) { 

				if(validPositionsOfKing.get(ii).getColumn()==validPositionsHolder.get(j).getColumn()&&validPositionsOfKing.get(ii).getRow()==validPositionsHolder.get(j).getRow())				
					{if(!(enemy instanceof Knight))counterEnemy++;
					validPositionsOfKing.remove(ii);																	
					}
				else ii++;
		 }	
			
		}
	   }

		System.out.println(ChessBoard.validPositions.size());
		ChessBoard.validPositions = validPositionsOfKing;
	}
	






}
