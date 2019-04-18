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
import gamelogics.EasyChessGame;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard extends JFrame{

	private static ChessBoard chessBoard;
	private static ArrayList<ChessPiece> chessPieces;
	private static ArrayList<ChessPiece> capturedPieces;
	private static ChessPiece currentPiece;
	
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
	
	public static ChessPiece getPiece(Position position)
	{
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getCurrentPosition().getRow() == position.getRow() && 
					chessPiece.getCurrentPosition().getColumn() == position.getColumn())
				return chessPiece;
		}
		return null;
	}
	
	public static ArrayList<Position> getValidPositions(ChessPiece chessPiece)
	{
		ArrayList<Position> validPositions = chessPiece.getValidMoves();
		currentPiece = chessPiece;
		
		if (chessPiece instanceof Pawn)
			filterPawnMovements(validPositions);
		
		else if (chessPiece instanceof Bishop)
			filterBishopMovements(validPositions);
		
		else if (chessPiece instanceof Rook)
			filterRookMovements(validPositions);
		
		else if (chessPiece instanceof Knight)
			filterKnightMovements(validPositions);
		
		else if (chessPiece instanceof Queen)
			filterQueenMovements(validPositions);
		
		else if (chessPiece instanceof King)
			filterKingMovements(validPositions);
		
		return validPositions;
	}
	
	private static void filterKingMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
				validPositions.remove(chessPiece.getCurrentPosition());
			else //remove position that king   captured on it
				for(Position oppositePieceposition : chessPiece.getValidMoves())
				    if(currentPiece.getValidMoves().contains(oppositePieceposition))
					   validPositions.remove(oppositePieceposition);		
		}		
	}

	private static void filterQueenMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				filterPositionsBlocked(validPositions.indexOf(chessPiece.getCurrentPosition()), validPositions);
				validPositions.remove(chessPiece.getCurrentPosition());
			}
			else
				filterPositionsBlocked(validPositions.indexOf(chessPiece.getCurrentPosition()), validPositions);
		}	
	}
	
	private static void filterPositionsBlocked(int indextOfBlockingPiece, ArrayList<Position> validPosition) {
		Position blockingPiece = validPosition.get(indextOfBlockingPiece);
		
		//forward
		if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() == 0)
		{
				for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
					if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() == 0)
						validPosition.remove(i);
		}
		
		//backward
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() == 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() == 0)
					validPosition.remove(i);
		}
		
		//right
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() == 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() == 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
		
		//left
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() == 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() == 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//northwest
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//northeast
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() > 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() > 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
		
		//southwest
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() > 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() > 0)
					validPosition.remove(i);
		}
		
		//southeast
		else if(currentPiece.getCurrentPosition().getRow() - blockingPiece.getRow() < 0 && currentPiece.getCurrentPosition().getColumn() - blockingPiece.getColumn() < 0)
		{
			for (int i=indextOfBlockingPiece+1; i<validPosition.size(); i++)
				if(blockingPiece.getRow() - validPosition.get(i).getRow() < 0 && blockingPiece.getColumn() - validPosition.get(i).getColumn() < 0)
					validPosition.remove(i);
		}
	}

	private static void filterKnightMovements(ArrayList<Position> validPositions) {
	
		for  (ChessPiece chessPiece : chessPieces)
		{
			if (currentPiece.getPieceColor().equals(chessPiece.getPieceColor()) &&
					!currentPiece.getCurrentPosition().equals(chessPiece.getCurrentPosition()))
			{
				for (int i =0;i<validPositions.size();i++)
				{
					if (validPositions.get(i).getRow() == chessPiece.getCurrentPosition().getRow() &&
							validPositions.get(i).getColumn() == chessPiece.getCurrentPosition().getColumn())
					{
                         validPositions.remove(i);
                         break;
					}
				}
			}
		}
		
		
	}

	private static void filterRookMovements(ArrayList<Position> validPositions) {

		Position  currentpostion = currentPiece.getCurrentPosition();
		for(ChessPiece chessPiece : chessPieces)
		{
			if(chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				Position piecePostion  = chessPiece.getCurrentPosition();
				if(currentpostion.getColumn()<piecePostion.getColumn()&&currentpostion.getRow()==piecePostion.getRow())
				{
					for(int i=piecePostion.getColumn();i<8;i++)
					{
						Position a = new Position(piecePostion.getRow(),i);
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getColumn()>piecePostion.getColumn()&&currentpostion.getRow()==piecePostion.getRow())
				{
					for(int i=piecePostion.getColumn();i>=0;i--)
					{
						Position a = new Position(piecePostion.getRow(),i);
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getRow()<piecePostion.getRow()&&currentpostion.getColumn()==piecePostion.getColumn())
				{
					for(int i=piecePostion.getRow();i<8;i++)
					{
						Position a = new Position(i,piecePostion.getColumn());
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getRow()>piecePostion.getRow()&&currentpostion.getColumn()==piecePostion.getColumn())
				{
					for(int i=piecePostion.getRow();i>=0;i--)
					{
						Position a = new Position(i,piecePostion.getColumn());
						filterRookbyIndex(a,validPositions);
					}
				}
		}
			else
			{
				Position piecePostion2  = chessPiece.getCurrentPosition();
				if(currentpostion.getColumn()<piecePostion2 .getColumn()&&currentpostion.getRow()==piecePostion2.getRow())
				{
					for(int i=piecePostion2.getColumn()+1;i<8;i++)
					{
						Position a = new Position(piecePostion2.getRow(),i);
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getColumn()>piecePostion2.getColumn()&&currentpostion.getRow()==piecePostion2.getRow())
				{
					for(int i=piecePostion2.getColumn()-1;i>=0;i--)
					{
						Position a = new Position(piecePostion2.getRow(),i);
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getRow()<piecePostion2.getRow()&&currentpostion.getColumn()==piecePostion2.getColumn())
				{
					for(int i=piecePostion2.getRow()+1;i<8;i++)
					{
						Position a = new Position(i,piecePostion2.getColumn());
						filterRookbyIndex(a,validPositions);
					}
				}
				if(currentpostion.getRow()>piecePostion2.getRow()&&currentpostion.getColumn()==piecePostion2.getColumn())
				{
					for(int i=piecePostion2.getRow()-1;i>=0;i--)
					{
						Position a = new Position(i,piecePostion2.getColumn());
						filterRookbyIndex(a,validPositions);
					}
				}
				
			}
		}
	}
	
	private static void filterRookbyIndex(Position p ,ArrayList<Position>validposition)
	{
		for(int i=0;i<validposition.size();i++)
		{
			if(validposition.get(i).getRow()==p.getRow()&&validposition.get(i).getColumn()==p.getColumn())
			{
				validposition.remove(i);
				break;
			}
		}
	}

	private static void filterBishopMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor()))
			{
				validPositions.remove(chessPiece.getCurrentPosition());
				for(int i=0;i<7;i++)
					{
					 	if((currentPiece.getCurrentPosition().getRow()+i)==chessPiece.getCurrentPosition().getRow()&&(currentPiece.getCurrentPosition().getColumn()+i)==chessPiece.getCurrentPosition().getColumn()&&chessPiece.getCurrentPosition().getRow()<7&&chessPiece.getCurrentPosition().getColumn()<7)
					 	{
					 		for(int j=0;(chessPiece.getCurrentPosition().getRow()<7&&chessPiece.getCurrentPosition().getColumn()<7);j++)
					 		{
					 			Position p=new Position(chessPiece.getCurrentPosition().getRow()+j,chessPiece.getCurrentPosition().getColumn()+j);
								validPositions.remove(p);	
					 		}
					 	}
					 	if((currentPiece.getCurrentPosition().getRow()-i)==chessPiece.getCurrentPosition().getRow()&&(currentPiece.getCurrentPosition().getColumn()-i)==chessPiece.getCurrentPosition().getColumn()&&chessPiece.getCurrentPosition().getRow()>0&&chessPiece.getCurrentPosition().getColumn()>0)
					 	{
					 		for(int j=0;(chessPiece.getCurrentPosition().getRow()>0&&chessPiece.getCurrentPosition().getColumn()>0);j++)
					 		{
					 			Position p1=new Position(chessPiece.getCurrentPosition().getRow()-j,chessPiece.getCurrentPosition().getColumn()-j);
								validPositions.remove(p1);	
					 		}
					 	}
					 	if((currentPiece.getCurrentPosition().getRow()+i)==chessPiece.getCurrentPosition().getRow()&&(currentPiece.getCurrentPosition().getColumn()-i)==chessPiece.getCurrentPosition().getColumn()&&chessPiece.getCurrentPosition().getRow()<7&&chessPiece.getCurrentPosition().getColumn()>0)
					 	{
					 		for(int j=0;(chessPiece.getCurrentPosition().getRow()<7&&chessPiece.getCurrentPosition().getColumn()>0);j++)
					 		{
					 			Position p2=new Position(chessPiece.getCurrentPosition().getRow()+j,chessPiece.getCurrentPosition().getColumn()-j);
								validPositions.remove(p2);	
					 		}
					 	}
					 	if((currentPiece.getCurrentPosition().getRow()-i)==chessPiece.getCurrentPosition().getRow()&&(currentPiece.getCurrentPosition().getColumn()+i)==chessPiece.getCurrentPosition().getColumn()&&chessPiece.getCurrentPosition().getRow()>0&&chessPiece.getCurrentPosition().getColumn()<7)
					 	{
					 		for(int j=0;(chessPiece.getCurrentPosition().getRow()>0&&chessPiece.getCurrentPosition().getColumn()<7);j++)
					 		{
					 			Position p3=new Position(chessPiece.getCurrentPosition().getRow()-j,chessPiece.getCurrentPosition().getColumn()+j);
								validPositions.remove(p3);	
					 		}
					 	}
					}
			}
		}
	}


	private static void filterPawnMovements(ArrayList<Position> validPositions) {
		for (ChessPiece chessPiece : chessPieces)
		{
			if (chessPiece.getPieceColor().equals(currentPiece.getPieceColor())) 
				{
					validPositions.remove(chessPiece.getCurrentPosition());
					if(currentPiece.getCurrentPosition().getRow()==1&&chessPiece.getCurrentPosition().getRow()==2&&currentPiece.getCurrentPosition().getColumn()==chessPiece.getCurrentPosition().getColumn())/////////////2 step
					{
						Position p=new Position(chessPiece.getCurrentPosition().getRow()+1,chessPiece.getCurrentPosition().getColumn());
						validPositions.remove(p);
					}
					else if(currentPiece.getCurrentPosition().getRow()==6&&chessPiece.getCurrentPosition().getRow()==7&&currentPiece.getCurrentPosition().getColumn()==chessPiece.getCurrentPosition().getColumn())/////////////2 step
					{
						Position p1=new Position(chessPiece.getCurrentPosition().getRow()-1,chessPiece.getCurrentPosition().getColumn());
						validPositions.remove(p1);
					}
				}
		}
	}


	public static boolean hasPieceInPositon(Position position)
	{
		return(getPiece(position) != null);   
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
