package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import extra.Position;
import filters.BishopFilterCriteria;
import filters.KingFilterCriteria;
import filters.KnightFilterCriteria;
import filters.PawnFilterCriteria;
import filters.QueenFilterCriteria;
import filters.RookFilterCriteria;
import gamelogics.EasyChessGame;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard extends JFrame{

	public static ArrayList<Position> validPositions; 
	private static ChessBoard chessBoard;
	public static ArrayList<ChessPiece> chessPieces;
	private static ArrayList<ChessPiece> capturedPieces;
	public static ChessPiece currentPiece;
	
	public static JFrame boardFrame;
    private static JPanel contents;
    private static JButton[][] squares;
	private static Color colorBlack;
	private static Color colorWhite;
	
	public static ChessBoard getChessBoard()
	{
		if (chessBoard == null)
			chessBoard = new ChessBoard();
		return chessBoard;
	}
	
	
	private ChessBoard()
	{
		initBoard();
		initialize();
	}
	
	private void initBoard()
	{
		chessPieces = new ArrayList<>();
		capturedPieces = new ArrayList<>();
		validPositions=new ArrayList<Position>();

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
	
	private void initialize()
	{
		squares = new JButton[8][8];
		colorBlack = Color.black;
		colorWhite = Color.white;
		
		boardFrame = new JFrame("Chess Game");
		boardFrame.setSize(new Dimension(700,700));
		boardFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		contents = (JPanel) getContentPane();
		contents.setLayout(new GridLayout(8,8));
		contents.setBackground(Color.gray);
		
		for(int i=0; i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				squares[i][j]=new JButton();
				if((i+j)%2 !=0)
				{
					squares[i][j].setBackground(colorBlack);
				}
				else
					squares[i][j].setBackground(colorWhite);
				
				contents.add(squares[i][j]);
			}
		}
		

		squares[7][0].setIcon(new ImageIcon(getClass().getResource("/assets/white_rook.png")));
		squares[7][1].setIcon(new ImageIcon(getClass().getResource("/assets/white_knight.png")));
		squares[7][2].setIcon(new ImageIcon(getClass().getResource("/assets/white_bishop.png")));
		squares[7][3].setIcon(new ImageIcon(getClass().getResource("/assets/white_queen.png")));
		squares[7][4].setIcon(new ImageIcon(getClass().getResource("/assets/white_king.png")));
		squares[7][5].setIcon(new ImageIcon(getClass().getResource("/assets/white_bishop.png")));
		squares[7][6].setIcon(new ImageIcon(getClass().getResource("/assets/white_knight.png")));
		squares[7][7].setIcon(new ImageIcon(getClass().getResource("/assets/white_rook.png")));
		
		for(int m=0;m<8;m++)
		{
			squares[6][m].setIcon(new ImageIcon(getClass().getResource("/assets/white_pawn.png")));
		}
		
		// index [0][0] starts from upperLeft
		
		squares[0][0].setIcon(new ImageIcon(getClass().getResource("/assets/black_rook.png")));
		squares[0][1].setIcon(new ImageIcon(getClass().getResource("/assets/black_knight.png")));
		squares[0][2].setIcon(new ImageIcon(getClass().getResource("/assets/black_bishop.png")));
		squares[0][3].setIcon(new ImageIcon(getClass().getResource("/assets/black_queen.png")));
		squares[0][4].setIcon(new ImageIcon(getClass().getResource("/assets/black_king.png")));
		squares[0][5].setIcon(new ImageIcon(getClass().getResource("/assets/black_bishop.png")));
		squares[0][6].setIcon(new ImageIcon(getClass().getResource("/assets/black_knight.png")));
		squares[0][7].setIcon(new ImageIcon(getClass().getResource("/assets/black_rook.png")));
		
		for(int n=0;n<8;n++)
		{
			squares[1][n].setIcon(new ImageIcon(getClass().getResource("/assets/black_pawn.png")));
		}
		
		setSize(500,700);
		setResizable(false);
		setLocationRelativeTo(null);
		boardFrame.setContentPane(contents);
		boardFrame.setLocationRelativeTo(null);
		boardFrame.setVisible(true);
	}
	
	
	public static ArrayList<Position> getValidPositions(ChessPiece chessPiece){
		currentPiece = chessPiece;
		return new KingFilterCriteria().checkKingProtection(filter(chessPiece));
	}


    public static ArrayList<Position> filter(ChessPiece chessPiece) {
		        
		        if (chessPiece instanceof Pawn) 
		        	return new PawnFilterCriteria().filterPositions(chessPiece);
				else if (chessPiece instanceof Bishop)
		        	return new BishopFilterCriteria().filterPositions(chessPiece);
				else if (chessPiece instanceof Rook)
		        	return new RookFilterCriteria().filterPositions(chessPiece);
				else if (chessPiece instanceof Knight)
		        	return new KnightFilterCriteria().filterPositions(chessPiece);
				else if (chessPiece instanceof Queen)
		        	return new QueenFilterCriteria().filterPositions(chessPiece);
				else if (chessPiece instanceof King)
		        	return new KingFilterCriteria().filterPositions(chessPiece);
				
				//return validPositions;
		        return null;
	 }

	public static boolean hasPieceInPositon(Position position)
	{
		return(getPiece(position) != null);   
	}
	
	public static ChessPiece getPiece(Position position){
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getCurrentPosition().getRow() == position.getRow() && 
					chessPiece.getCurrentPosition().getColumn() == position.getColumn())
				return chessPiece;	
		}
		return null;
	}

	public static void pieceCaptured(ChessPiece chessPiece)
	{
		chessPieces.remove(chessPiece);
		chessPiece.captured();
		capturedPieces.add(chessPiece);
	}
	
	public static void resetBoard()
	{
		chessBoard = new ChessBoard();
	}
	
	public static JButton[][] getSquares()
	{
		return squares;
	}
	
}
