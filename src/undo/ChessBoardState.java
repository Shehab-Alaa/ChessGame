package undo;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;

import extra.Position;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoardState {

	private ArrayList<ChessPiece> chessPieces;
	private ArrayList<ChessPiece> capturedPieces;
    private JButton[][] squares;
    private int playTurn;
    
    public ChessBoardState(ArrayList<ChessPiece> chessPieces , ArrayList<ChessPiece> capturedPieces,
    		JButton[][] squares , int playTurn)
    {
    	this.chessPieces =  new ArrayList<>();
    	this.capturedPieces = new ArrayList<>();
    	this.squares = new JButton[8][8];

    	copyArrayValues(this.chessPieces , chessPieces);
    	copyArrayValues(this.capturedPieces,capturedPieces);
    	
       	for (int i =0;i<8;i++)
    		for (int j=0;j<8;j++)
    			this.squares[i][j] = new JButton(squares[i][j].getIcon());
    	
    	this.playTurn = playTurn;
    }

    private int getChessPieceType(ChessPiece chessPiece)
    {
    	if (chessPiece instanceof Pawn)
    		return 1;
    	else if (chessPiece instanceof Rook)
    		return 2;
    	else if (chessPiece instanceof Bishop)
    		return 3;
    	else if (chessPiece instanceof Knight)
    		return 4;
    	else if (chessPiece instanceof Queen)
    		return 5;
    	else if (chessPiece instanceof King)
    		return 6;
    	return -1;
    }
    
    private void copyArrayValues(ArrayList<ChessPiece> chessPieces,ArrayList<ChessPiece> chessPiecesHolder)
    {
    	for (int i =0;i<chessPiecesHolder.size();i++)
    	{
			int typeValue = getChessPieceType(chessPiecesHolder.get(i));
			String pieceColor = chessPiecesHolder.get(i).getPieceColor();
			int row = chessPiecesHolder.get(i).getCurrentPosition().getRow();
			int column = chessPiecesHolder.get(i).getCurrentPosition().getColumn();
			switch (typeValue)
			{  
				case 1:
					chessPieces.add(new Pawn(new Position(row,column) , pieceColor));
					break;
				case 2:
					chessPieces.add(new Rook(new Position(row,column) , pieceColor));
					break;
				case 3:
					chessPieces.add(new Bishop(new Position(row,column) , pieceColor));
					break;
				case 4:
					chessPieces.add(new Knight(new Position(row,column) , pieceColor));
					break;
				case 5:
					chessPieces.add(new Queen(new Position(row,column) , pieceColor));
					break;
				case 6:
					chessPieces.add(new King(new Position(row,column) , pieceColor));
					break;
			}
    	}
    }
    
	public ArrayList<ChessPiece> getChessPieces() {
		return chessPieces;
	}

	public ArrayList<ChessPiece> getCapturedPieces() {
		return capturedPieces;
	}

	public JButton[][] getSquares() {
		return squares;
	}

	public int getPlayTurn() {
		return playTurn;
	}    
    
}
