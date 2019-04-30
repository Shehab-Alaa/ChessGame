package filters;

import java.util.ArrayList;

import board.ChessBoard;
import extra.Position;
import pieces.ChessPiece;

public class BishopFilterCriteria implements FilterCriteria{
	
	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {
		
		chessBoard.setValidPositions(chessPieceHolder.getValidMoves());
		
		for (int i=0; i<chessBoard.getChessPieces().size();i++)
		{
			for (int j=0;j<chessBoard.getValidPositonsArray().size();j++)
			{
				if (chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == chessBoard.getValidPositonsArray().get(j).getRow() && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == chessBoard.getValidPositonsArray().get(j).getColumn())
				{
					
					//northweast
					if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() > 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() < 0)
					{
						if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
							chessBoard.getValidPositonsArray().remove(j);
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() > 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() < 0)
									chessBoard.getValidPositonsArray().remove(k);
								else k++;
							}
					}
					
					//northwest
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() > 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() > 0)
					{
						if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
							chessBoard.getValidPositonsArray().remove(j);
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() > 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() > 0)
									chessBoard.getValidPositonsArray().remove(k);
								else k++;
							}
					}
					
					//southwest
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() < 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() > 0)
					{
						if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
							chessBoard.getValidPositonsArray().remove(j);
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() < 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() > 0)
									chessBoard.getValidPositonsArray().remove(k);
								else k++;
							}
					}
					
					//southeast
					else if(chessPieceHolder.getCurrentPosition().getRow() - chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() < 0 && chessPieceHolder.getCurrentPosition().getColumn() - chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() < 0)
					{
						if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
							chessBoard.getValidPositonsArray().remove(j);
							int k=0; 
							while (k<chessBoard.getValidPositonsArray().size())
							{
								if(chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() - chessBoard.getValidPositonsArray().get(k).getRow() < 0 && chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() - chessBoard.getValidPositonsArray().get(k).getColumn() < 0)
									chessBoard.getValidPositonsArray().remove(k);
								else k++;
							}
					}
					break;
				}
			}
		}
	}

}
