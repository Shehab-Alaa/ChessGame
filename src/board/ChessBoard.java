package board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import extra.Position;
import filters.BishopFilterCriteria;
import filters.FiltersHelper;
import filters.KingFilterCriteria;
import filters.KnightFilterCriteria;
import filters.PawnFilterCriteria;
import filters.QueenFilterCriteria;
import filters.RookFilterCriteria;
import game.GameMenu;
import gamelogics.EasyChessGame;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard{

	private ArrayList<Position> validPositions; 
	private static ChessBoard chessBoard;
	private ArrayList<ChessPiece> chessPieces;
	private ArrayList<ChessPiece> capturedPieces;
	private ChessPiece currentPiece;
	private FiltersHelper filtersHelper;
	private int friendCounter = 0, enemyCounter = 0;
	
	
	public static ChessBoard getChessBoardInstance()
	{
		if (chessBoard == null)
			chessBoard = new ChessBoard();
		return chessBoard;
	}
	
	
	private ChessBoard()
	{
		initBoard();
	}
	
	private void initBoard()
	{
		chessPieces = new ArrayList<>();
		capturedPieces = new ArrayList<>();
		validPositions=new ArrayList<Position>();
		
		filtersHelper = new FiltersHelper();

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
	

	public ArrayList<Position> getValidPositions(ChessPiece chessPiece){
		currentPiece = chessPiece;
		return new KingFilterCriteria().checkKingProtection(filter(chessPiece),currentPiece);
	}

    public ArrayList<Position> filter(ChessPiece chessPiece) {
		filtersHelper.filterPositions(chessPiece);        
		return validPositions;
	 }

	public boolean hasPieceInPositon(Position position)
	{
		return(getPiece(position) != null);   
	}
	
	public ChessPiece getPiece(Position position){
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getCurrentPosition().getRow() == position.getRow() && 
					chessPiece.getCurrentPosition().getColumn() == position.getColumn())
				return chessPiece;	
		}
		return null;
	}

	public void pieceCaptured(ChessPiece chessPiece)
	{
		chessPieces.remove(chessPiece);
		capturedPieces.add(chessPiece);
	}
	
	public ArrayList<ChessPiece> getChessPieces()
	{
		return chessPieces;
	}
	
	public ArrayList<ChessPiece> getCapturedPieces()
	{
		return capturedPieces;
	}
	
	public ChessPiece getCurrentPiece()
	{
	   return currentPiece;	
	}
	
	public ArrayList<Position> getValidPositonsArray()
	{
		return this.validPositions;
	}
	
	public void setValidPositions(ArrayList<Position> validPositions)
	{
		this.validPositions = validPositions;
	}
	
	public void resetBoard()
	{
		initBoard();
	}
	
	public void setChessPieces(ArrayList<ChessPiece> chessPieces)
	{
		this.chessPieces = new ArrayList<>(chessPieces);
	}
	
	public void setCapturedPieces(ArrayList<ChessPiece> capturedPieces)
	{
		this.capturedPieces = new ArrayList<>(capturedPieces);
	}
	
	public void setFriendCounter(int friendCounter)
	{
		this.friendCounter = friendCounter;
	}
	
	public void setEnemyCounter(int enemyCounter)
	{
		this.enemyCounter = enemyCounter;
	}
	
	public void incrementFriendCounter()
	{
		friendCounter++;
	}
	
	public void incrementEnemyCounter()
	{
		enemyCounter++;
	}
	
	public int getFriendCounter()
	{
		return friendCounter;
	}
	
	public int getEnemyCounter()
	{
		return enemyCounter;
	}
	public int evaluateBoard(ArrayList<ChessPiece> evaluatedPieces) {
		int value=0;
		for(ChessPiece currentPiece: evaluatedPieces) 
			value += currentPiece.getPieceValue() * (currentPiece.getPieceColor().equals("White") ? 1 : -1);
		return value;
	}

}
