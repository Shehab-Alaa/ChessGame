package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;
import pieces.Queen;

public class RookFilterCriteria implements FilterCriteria {
	
	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {
		chessBoard.setValidPositions(chessPieceHolder.getValidMoves());
		filterration(chessPieceHolder);		
	}

	public void filterPositions(ChessPiece chessPieceHolder,ArrayList<Position> holder) {
		filterration(chessPieceHolder);
	}
	
	private void filterration(ChessPiece chessPieceHolder) {
		for (int i=0; i<chessBoard.getChessPieces().size();i++)
		{
			for (int j=0;j<chessBoard.getValidPositonsArray().size();j++)
			{
				
				if (chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == chessBoard.getValidPositonsArray().get(j).getRow() && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == chessBoard.getValidPositonsArray().get(j).getColumn())
				{
					//Position piecePostion = new Position(ChessBoard.validPositions.get(i).getRow(),ChessBoard.validPositions.get(i).getColumn());
					if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
						{
						chessBoard.getValidPositonsArray().remove(j);
						}
					//forward
					if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() > 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == 0)
					{
						if(chessPieceHolder instanceof Queen) {}
						
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() > 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() == 0)
									{
									
									chessBoard.getValidPositonsArray().remove(k);
									}
								else k++;
							}
					}
					
					//backward
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() < 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == 0)
					{
							int k=j; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() < 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() == 0)
									{						
									  chessBoard.getValidPositonsArray().remove(k);
									}
								else k++;
							}
					}
					
					//left
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() > 0)
					{
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() == 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() > 0)
								{
									chessBoard.getValidPositonsArray().remove(k);
								}
								else k++;
							}
					}
					
					//right
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() < 0)
					{
							int k=j; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() == 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() < 0)
									{
									chessBoard.getValidPositonsArray().remove(k);
									}
								else k++;
							}
					}
					
					break;
				}
				
			}
			
		}
	}
}
