package filters;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Rook;

public class KingFilterCriteria implements FilterCriteria{

	private int friendCounter=0,enemyCounter=0;
	
	public ChessPiece getOppositeKingPiece(String color)
	{
		for (ChessPiece chessPiece : chessBoard.getChessPieces())
		{
			if (chessPiece instanceof King &&chessPiece.getPieceColor()!=color)
				return chessPiece;
		}
		return null;
	}
			
    public ChessPiece getKingPiece(String color)
	{
		for (ChessPiece chessPiece : chessBoard.getChessPieces())
		{
			if (chessPiece instanceof King &&chessPiece.getPieceColor()==color)
				return chessPiece;
		}
		return null;
	}
	    
    private boolean filterationHelper(Position position,ArrayList<Position> bigvalidpositions,int indexofbigvalidpositions,ChessPiece ChessPiece) {
		ArrayList<Position> enemyValidPositions,currentPieceValidPositions;
		Position current = ChessPiece.getCurrentPosition();
		for(int i=0;i<chessBoard.getChessPieces().size();i++) {
			
			if(chessBoard.getChessPieces().get(i).getPieceColor()!=ChessPiece.getPieceColor()&&(!(chessBoard.getChessPieces().get(i) instanceof King)&&!(chessBoard.getChessPieces().get(i) instanceof Knight))) {
				ChessPiece.setCurrentPosition(position); 					
				ChessPiece king = getKingPiece(ChessPiece.getPieceColor());
				currentPieceValidPositions=new ArrayList<Position>(chessBoard.getValidPositonsArray());
					enemyValidPositions = chessBoard.filter(chessBoard.getChessPieces().get(i));
					
					chessBoard.setValidPositions(new ArrayList<Position>(currentPieceValidPositions));
					
					for(int j=0;j<enemyValidPositions.size();j++) {
						if(enemyValidPositions.get(j).getRow()==king.getCurrentPosition().getRow()&&enemyValidPositions.get(j).getColumn()==king.getCurrentPosition().getColumn()){
							bigvalidpositions.remove(indexofbigvalidpositions);
							ChessPiece.setCurrentPosition(current);
							return true;
							}								
				}
				
			}
		}
		ChessPiece.setCurrentPosition(current);
		return false;
	}
	
    public ArrayList<Position> checkKingProtection(ArrayList<Position> validPositions,ChessPiece ChessPiece) {
	  for(int i=0;i<validPositions.size();) {
		  if(!filterationHelper(validPositions.get(i),validPositions,i, ChessPiece)) 
			  i++;		  
	  }
	    return validPositions; 
      }
  
    public boolean Checkmate(ChessPiece kingChessPiece,ChessPiece ChessPiece){
 		ArrayList<Position>currentPiecevalidPosition=new ArrayList<Position>(chessBoard.getValidPositonsArray());
 		ArrayList<Position>ChessPiecevalidPosition=new ArrayList<Position>(chessBoard.filter(ChessPiece));
 		
 		chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));

 		for(int i=0;i<chessBoard.getChessPieces().size();i++) {
 			if(chessBoard.getChessPieces().get(i).getPieceColor().equals(kingChessPiece.getPieceColor())) {
 				ArrayList<Position>beforprotection= new ArrayList<Position>(chessBoard.filter(chessBoard.getChessPieces().get(i)));

 		 		chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));

 		 		int size=beforprotection.size();
 		 		ArrayList<Position>m= new ArrayList<Position>(new KingFilterCriteria().checkKingProtection(beforprotection,chessBoard.getChessPieces().get(i)));
 				
 		 		chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));
 		 		
 				if(size>m.size()) {
 					for(int j=0;j<m.size();j++) {
 						for (int k=0;k<ChessPiecevalidPosition.size();k++) {
 							if (m.get(j).getRow() == ChessPiecevalidPosition.get(k).getRow() && m.get(j).getColumn() == ChessPiecevalidPosition.get(k).getColumn())
 							{
 								return false ;
 							}
 						}
 						
 					}
 				}
 			}
 		}
 		
 		chessBoard.setValidPositions(chessBoard.filter(kingChessPiece));
		chessBoard.setValidPositions(currentPiecevalidPosition);

 					int length= kingChessPiece.getValidMoves().size();
 					
 		if(length!=friendCounter	 && length == friendCounter + enemyCounter ) {
 			return true;
 		}
 		
 		return false ;
    }

	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {
		friendCounter=0;
		enemyCounter=0;
		
		chessBoard.setValidPositions(chessPieceHolder.getValidMoves());

		for (int i=0;i< chessBoard.getChessPieces().size();i++){
			if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())){	

				for(int j=0;j<chessBoard.getValidPositonsArray().size();) 
					if(chessBoard.getValidPositonsArray().get(j).getColumn()==chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn()&&chessBoard.getValidPositonsArray().get(j).getRow()==chessBoard.getChessPieces().get(i).getCurrentPosition().getRow()){
						friendCounter++;

						chessBoard.getValidPositonsArray().remove(j);
				       }
					else j++;
				}
			else if(!chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())) {
				filterEnemyPositions(chessBoard.getChessPieces().get(i), chessPieceHolder, chessBoard.getValidPositonsArray());
			}
		}
		if(!chessPieceHolder.isFirstMove() && chessBoard.getCcurrentPiece() instanceof King) {
			start:for(ChessPiece rook: chessBoard.getChessPieces()) {
				if(rook instanceof Rook &&!rook.isFirstMove()&&rook.getPieceColor()==chessPieceHolder.getPieceColor()) {
						for(int i=Math.min(rook.getCurrentPosition().getColumn(),chessPieceHolder.getCurrentPosition().getColumn())+1;i<Math.max(rook.getCurrentPosition().getColumn(),chessPieceHolder.getCurrentPosition().getColumn());i++)
							if(chessBoard.hasPieceInPositon(new Position(chessPieceHolder.getCurrentPosition().getRow(),i) ))
								break start;
						if(chessPieceHolder.getCurrentPosition().getColumn()>rook.getCurrentPosition().getColumn()) 
							chessBoard.getValidPositonsArray().add(new Position(chessPieceHolder.getCurrentPosition().getRow(),chessPieceHolder.getCurrentPosition().getColumn()-2));								
						else chessBoard.getValidPositonsArray().add(new Position(chessPieceHolder.getCurrentPosition().getRow(),chessPieceHolder.getCurrentPosition().getColumn()+2));
					
						}			
			}
		}
	}
	
    private void filterEnemyPositions(ChessPiece enemy,ChessPiece King,ArrayList<Position> validPositionsOfKing) {
    	ArrayList<Position> validPositionsHolder;
		if(enemy instanceof King) {
			for(int j=0;j<validPositionsOfKing.size();) {
				double length=Math.floor((Math.sqrt((Math.pow((validPositionsOfKing.get(j).getRow()-enemy.getCurrentPosition().getRow()), 2))
				+(Math.pow((validPositionsOfKing.get(j).getColumn()-enemy.getCurrentPosition().getColumn()), 2)))));
				if(length==1.0)						       
				{
					enemyCounter++;
					chessBoard.getValidPositonsArray().remove(j);
				}
			    else j++;
			}
		}
	    else {

		validPositionsHolder=chessBoard.filter(enemy);

		for(int j=0;j< validPositionsHolder.size();j++) {				
			for(int ii=0;ii<validPositionsOfKing.size();) { 

				if(validPositionsOfKing.get(ii).getColumn()==validPositionsHolder.get(j).getColumn()&&validPositionsOfKing.get(ii).getRow()==validPositionsHolder.get(j).getRow())				
					{if(!(enemy instanceof Knight)&&!chessBoard.hasPieceInPositon(validPositionsOfKing.get(ii)))enemyCounter++;
					validPositionsOfKing.remove(ii);																	
					}
				else ii++;
		 }		
		}
	   }
		chessBoard.setValidPositions(validPositionsOfKing);	
	}
	
    private void assignNewPosition(ChessPiece kingPiece,ChessPiece rook,int column,int i) {
    	ImageIcon iconHolder = (ImageIcon)  chessBoard.getSquares()[rook.getCurrentPosition().getRow()][rook.getCurrentPosition().getColumn()].getIcon();
		  chessBoard.getSquares()[rook.getCurrentPosition().getRow()][rook.getCurrentPosition().getColumn()].setIcon(null);
		  chessBoard.getSquares()[kingPiece.getCurrentPosition().getRow()][column+i].setIcon(iconHolder);
		rook.setCurrentPosition(new Position(kingPiece.getCurrentPosition().getRow(),column+i));
    }
    
    public void Castling(ChessPiece kingPiece,int column) {
    	ChessPiece rook;
    	if(Math.abs(kingPiece.getCurrentPosition().getColumn()-column)==2) {
    		if(kingPiece.getCurrentPosition().getColumn()>column) {
   		        rook = chessBoard.getPiece(new Position(kingPiece.getCurrentPosition().getRow(),0));
    		    assignNewPosition(kingPiece,rook,column,1);
    		}
    		else {  
    		  rook = chessBoard.getPiece(new Position(kingPiece.getCurrentPosition().getRow(),7));
		      assignNewPosition(kingPiece,rook,column,-1);
		
		    }
			  rook.setFirstMove(true);
    	}
    }



    
}
