package memento;

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

    	for (ChessPiece chessPiece : chessPieces)
    		this.chessPieces.add(chessPiece.cloneChessPiece());
    	
    	for (ChessPiece chessPiece : capturedPieces)
    		this.capturedPieces.add(chessPiece.cloneChessPiece());
    	
    	
       	for (int i =0;i<8;i++)
    		for (int j=0;j<8;j++)
    			this.squares[i][j] = new JButton(squares[i][j].getIcon());
    	
    	this.playTurn = playTurn;
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
