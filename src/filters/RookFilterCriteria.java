package filters;

import java.util.ArrayList;

import extra.Position;
import game.ChessBoard;
import pieces.ChessPiece;

public class RookFilterCriteria implements FilterCriteria {

	@Override
	public ArrayList<Position> filterPositions(ChessPiece chessPieceHolder) {
		ChessBoard.validPositions = chessPieceHolder.getValidMoves();
		for (int i=0; i<ChessBoard.chessPieces.size();i++)
		{
			for (int j=0;j<ChessBoard.validPositions.size();j++)
			{
				if (ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() == ChessBoard.validPositions.get(j).getRow() && ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() == ChessBoard.validPositions.get(j).getColumn())
				{
					if (ChessBoard.chessPieces.get(i).getPieceColor().equals(chessPieceHolder.getPieceColor()))
						ChessBoard.validPositions.remove(j);
					
					//forward
					if(chessPieceHolder.getCurrentPosition().getRow() - ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() > 0 && chessPieceHolder.getCurrentPosition().getColumn() - ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() == 0)
					{
							int k=0; 
							while (k<ChessBoard.validPositions.size())
							{
								if(ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() - ChessBoard.validPositions.get(k).getRow() > 0 && ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() - ChessBoard.validPositions.get(k).getColumn() == 0)
									ChessBoard.validPositions.remove(k);
								else k++;
							}
					}
					
					//backward
					else if(chessPieceHolder.getCurrentPosition().getRow() - ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() < 0 && chessPieceHolder.getCurrentPosition().getColumn() - ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() == 0)
					{
							int k=j; 
							while (k<ChessBoard.validPositions.size())
							{
								if(ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() - ChessBoard.validPositions.get(k).getRow() < 0 && ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() - ChessBoard.validPositions.get(k).getColumn() == 0)
									ChessBoard.validPositions.remove(k);
								else k++;
							}
					}
					
					//left
					else if(chessPieceHolder.getCurrentPosition().getRow() - ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() == 0 && chessPieceHolder.getCurrentPosition().getColumn() - ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() > 0)
					{
							int k=0; 
							while (k<ChessBoard.validPositions.size())
							{
								if(ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() - ChessBoard.validPositions.get(k).getRow() == 0 && ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() - ChessBoard.validPositions.get(k).getColumn() > 0)
									ChessBoard.validPositions.remove(k);
								else k++;
							}
					}
					
					//right
					else if(chessPieceHolder.getCurrentPosition().getRow() - ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() == 0 && chessPieceHolder.getCurrentPosition().getColumn() - ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() < 0)
					{
							int k=j; 
							while (k<ChessBoard.validPositions.size())
							{
								if(ChessBoard.chessPieces.get(i).getCurrentPosition().getRow() - ChessBoard.validPositions.get(k).getRow() == 0 && ChessBoard.chessPieces.get(i).getCurrentPosition().getColumn() - ChessBoard.validPositions.get(k).getColumn() < 0)
									ChessBoard.validPositions.remove(k);
								else k++;
							}
					}
					
					break;
				}
			}
		}
		return ChessBoard.validPositions;
	}

}
