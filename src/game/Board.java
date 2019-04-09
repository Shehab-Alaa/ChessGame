package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JFrame {

	public JFrame boardFrame;
	private JPanel contents;
	private JButton[][] squares;
	private Color colorBlack;
	private Color colorWhite;
	
	private ImageIcon whiteBishop;
	private ImageIcon blackBishop;
	private ImageIcon whiteKnight;
	private ImageIcon blackKnight;
	private ImageIcon whiteRook;
	private ImageIcon blackRook;
	private ImageIcon whiteQueen;
	private ImageIcon blackQueen;
	private ImageIcon whitePawn;
	private ImageIcon blackPawn;
	private ImageIcon whiteKing;
	private ImageIcon blackKing;
	
	public Board()
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
		
		whiteBishop = new ImageIcon(getClass().getResource("/assets/white_bishop.png"));
		blackBishop = new ImageIcon(getClass().getResource("/assets/black_bishop.png"));
		whiteKnight = new ImageIcon(getClass().getResource("/assets/white_knight.png"));
		blackKnight = new ImageIcon(getClass().getResource("/assets/black_knight.png"));
		whiteRook = new ImageIcon(getClass().getResource("/assets/white_rook.png"));
		blackRook = new ImageIcon(getClass().getResource("/assets/black_rook.png"));
		whiteQueen = new ImageIcon(getClass().getResource("/assets/white_queen.png"));
		blackQueen = new ImageIcon(getClass().getResource("/assets/black_queen.png"));
		whitePawn = new ImageIcon(getClass().getResource("/assets/white_pawn.png"));
		blackPawn = new ImageIcon(getClass().getResource("/assets/black_pawn.png"));
		whiteKing = new ImageIcon(getClass().getResource("/assets/white_king.png"));
		blackKing = new ImageIcon(getClass().getResource("/assets/black_king.png"));

		squares[7][0].setIcon(whiteRook);
		squares[7][1].setIcon(whiteKnight);
		squares[7][2].setIcon(whiteBishop);
		squares[7][3].setIcon(whiteQueen);
		squares[7][4].setIcon(whiteKing);
		squares[7][5].setIcon(whiteBishop);
		squares[7][6].setIcon(whiteKnight);
		squares[7][7].setIcon(whiteRook);
		
		for(int m=0;m<8;m++)
		{
			squares[6][m].setIcon(whitePawn);
		}
		
		// index [0][0] starts from upperLeft
		
		squares[0][0].setIcon(blackRook);
		squares[0][1].setIcon(blackKnight);
		squares[0][2].setIcon(blackBishop);
		squares[0][3].setIcon(blackQueen);
		squares[0][4].setIcon(blackKing);
		squares[0][5].setIcon(blackBishop);
		squares[0][6].setIcon(blackKnight);
		squares[0][7].setIcon(blackRook);
		
		for(int n=0;n<8;n++)
		{
			squares[1][n].setIcon(blackPawn);
		}
		
		setSize(500,700);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		boardFrame.setContentPane(contents);
		boardFrame.setLocationRelativeTo(null);
		boardFrame.setVisible(true);
		
	}
}
