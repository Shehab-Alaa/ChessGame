package gamelogics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import game.GameMenu;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import players.Player;

abstract public class ChessGameLogic extends JFrame implements ActionListener {
  
   protected Player playerOne;
   protected Player playerTwo;
   protected static ChessBoard chessBoard;
   protected int playTurn; // even for White , odd for Black
   protected ChessPiece currentPiece;
   protected ChessPiece promotedPiece;
   protected static JButton squares[][];
   protected KingFilterCriteria kingFilterCriteria;

   protected JFrame boardFrame;
   private JPanel contents;
   private Color colorBlack;
   private Color colorWhite;
   private JLabel playerTwoNameLabel;
   private JLabel playerOneNameLabel;
   protected JPanel panel;		
   private JPanel playerOneCapturedPieces;
   private JPanel playerTwoCapturedPieces;
   protected ArrayList<ChessPiece>Enpassent;
   public ChessGameLogic(Player playerOne,Player playerTwo)
   {
	   this.playerOne = playerOne;
	   this.playerTwo = playerTwo;
	   chessBoard = ChessBoard.getChessBoardInstance();
	   initializeUI();
	   setPlayersNames(playerOne.getName(), playerTwo.getName());
	   currentPiece = null;
	   playTurn = 0;
	   Enpassent = new ArrayList<ChessPiece>();	   
   }
   
   private void initializeUI()
	{	
		boardFrame = new JFrame("Chess");
		setIconImage(new ImageIcon(getClass().getResource("/assets/game_icon.png")).getImage());
		boardFrame.setType(Type.POPUP);
		boardFrame.setFont(new Font("Arabic Typesetting", Font.BOLD, 19));
		boardFrame.setAlwaysOnTop(true);
		boardFrame.getContentPane().setBackground(new Color(51, 102, 102));
		boardFrame.getContentPane().setLayout(null);
	
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 0), null, new Color(128, 0, 0), null));
		panel.setBackground(new Color(250,250,250));
		panel.setBounds(729, 11, 515, 640);
		boardFrame.getContentPane().add(panel);
		panel.setLayout(null);
		
		playerTwoNameLabel = new JLabel("Player Two");
		playerTwoNameLabel.setBounds(10, 212, 276, 35);
		panel.add(playerTwoNameLabel);	
		playerTwoNameLabel.setForeground(new Color(0,0,0));
		playerTwoNameLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
		
	    playerTwoCapturedPieces = new JPanel();
		playerTwoCapturedPieces.setBounds(10, 246, 500, 40);
		panel.add(playerTwoCapturedPieces);
		playerTwoCapturedPieces.setBackground(new Color(230,220,200));
		playerTwoCapturedPieces.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		playerOneNameLabel = new JLabel("Player One");
		playerOneNameLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 22));
		playerOneNameLabel.setBounds(10, 368, 276, 35);
		panel.add(playerOneNameLabel);
		
		
		playerOneNameLabel.setForeground(new Color(0,0,0));
		
	    playerOneCapturedPieces = new JPanel();
		playerOneCapturedPieces.setBounds(10, 414, 500, 40);
		panel.add(playerOneCapturedPieces);
		playerOneCapturedPieces.setBackground(new Color(230,220,200));
		playerOneCapturedPieces.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(getClass().getResource("/assets/debian_one.png")));
		label_8.setBounds(459, 51, 51, 50);
		panel.add(label_8);
		
		JLabel lblChess = new JLabel("CHESS");
		lblChess.setBackground(new Color(100,50,10));
		lblChess.setForeground(new Color(0,128,128));
		lblChess.setFont(new Font("Bodoni MT Black", Font.BOLD, 99));
		lblChess.setBounds(74, 28, 378, 104);
		panel.add(lblChess);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/assets/debian_2.png")));
		lblNewLabel_1.setBounds(10, 51, 64, 50);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/assets/game_menu_background.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(0, 674, 515, -34);
		panel.add(lblNewLabel);	
		
		contents= new JPanel();
		contents.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(128, 0, 0), null, new Color(128, 0, 0), null));
		contents.setBounds(79, 11, 640, 640);
		
		
	    squares = new JButton[8][8];
	    colorBlack = new Color(51,102,102);
	    colorWhite = new Color(128,128,128);
		
		contents.setLayout(new GridLayout(8,8));
     	contents.setBackground(new Color(0, 0, 0));
		
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
		
	    for(int i=0; i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				squares[i][j].addActionListener(this);
			}
		}
		
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(false);
		boardFrame.getContentPane().add(contents);
		boardFrame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel_3 = new JLabel(" 8");
		lblNewLabel_3.setForeground(new Color(204,153,0));
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		lblNewLabel_3.setBounds(10, 30, 40, 40);
		boardFrame.getContentPane().add(lblNewLabel_3);
		
		JLabel label = new JLabel(" 7");
		label.setForeground(new Color(204,153,0));
		label.setBackground(Color.BLACK);
		label.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label.setBounds(10, 110, 40, 40);
		boardFrame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(" 6");
		label_1.setForeground(new Color(204,153,0));
		label_1.setBackground(Color.BLACK);
		label_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_1.setBounds(10, 185, 40, 40);
		boardFrame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel(" 5");
		label_2.setForeground(new Color(204,153,0));
		label_2.setBackground(Color.BLACK);
		label_2.setBackground(new Color(240, 240, 240));
		label_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_2.setBounds(10, 265, 40, 40);
		boardFrame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel(" 4");
		label_3.setForeground(new Color(204,153,0));
		label_3.setBackground(Color.BLACK);
		label_3.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_3.setBounds(10, 340, 40, 40);
		boardFrame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel(" 3");
		label_4.setForeground(new Color(204,153,0));
		label_4.setBackground(Color.BLACK);
		label_4.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_4.setBounds(10, 420, 40, 40);
		boardFrame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel(" 2");
		label_5.setForeground(new Color(204,153,0));
		label_5.setBackground(Color.BLACK);
		label_5.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_5.setBounds(10, 500, 40, 40);
		boardFrame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel(" 1");
		label_6.setForeground(new Color(204,153,0));
		label_6.setBackground(Color.BLACK);
		label_6.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		label_6.setBounds(10, 580, 40, 40);
		boardFrame.getContentPane().add(label_6);
		
		
		JLabel lblA = new JLabel("A       B        C       D       E       F      G       H");
		lblA.setForeground(new Color(204,153,0));
		lblA.setBackground(Color.BLACK);
		lblA.setFont(new Font("Microsoft YaHei", Font.BOLD, 26));
		lblA.setBounds(85, 653, 615, 40);
		boardFrame.getContentPane().add(lblA);
		
				JButton back =new JButton("");
				back.setBounds(1241, 0, 53, 50);
				boardFrame.getContentPane().add(back);
				back.setIcon(new ImageIcon(getClass().getResource("/assets/back.png")));
				back.setBackground(new Color(0, 0, 0));
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int x = JOptionPane.showConfirmDialog(boardFrame,"QUIT GAME ?","Question",JOptionPane.YES_NO_OPTION);
						if(x==JOptionPane.YES_OPTION)
						{
							boardFrame.setVisible(false);
							GameMenu gameMenu = new GameMenu();
						}
					}
				});
		
		boardFrame.setBackground(Color.WHITE);
		boardFrame.setBounds(15, 0, 1310, 735);//x,y,width,height
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardFrame.setAlwaysOnTop(false);
	}
   
   public void setSquares(JButton[][] squares)
	{
		for (int i =0;i<8;i++)
   		for (int j=0;j<8;j++)
   			this.squares[i][j].setIcon(squares[i][j].getIcon());
	}
	
	public static JButton[][] getSquares()
	{
		return squares;
	}
	
	public void setPlayersNames(String playerOneName , String playerTwoName)
	{
	   playerOneNameLabel.setText(playerOneName);
	   playerOneNameLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
	   playerTwoNameLabel.setText(playerTwoName);
	   playerTwoNameLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
	}
	
	public boolean checkPromotion()
	{
		if (currentPiece instanceof Pawn && (currentPiece.getPieceColor() == "White" && currentPiece.getCurrentPosition().getRow() == 0)
				|| (currentPiece.getPieceColor() == "Black" && currentPiece.getCurrentPosition().getRow() == 7))
		      return true;
		return false;
	}
	   
	   public boolean OvertakeCheckedColored()
		{
		   Boolean found= false;
		   
			for(int i=0;i<Enpassent.size();i++)
			  {
				  if(currentPiece instanceof Pawn &&
	 		           Enpassent.get(i).getCurrentPosition().getRow()==currentPiece.getCurrentPosition().getRow()&&
	 				  (Enpassent.get(i).getCurrentPosition().getColumn()-currentPiece.getCurrentPosition().getColumn()==1
	 				  ||Enpassent.get(i).getCurrentPosition().getColumn()-currentPiece.getCurrentPosition().getColumn()==-1)&&
	 				  !currentPiece.getPieceColor().equals(Enpassent.get(i).getPieceColor()))
				  {
					  if(currentPiece.getCurrentPosition().getRow()==3&&playTurn%2==0)
					  {
						  squares[Enpassent.get(i).getCurrentPosition().getRow()][Enpassent.get(i).getCurrentPosition().getColumn()].setBorder(new LineBorder(Color.yellow, 3));
		 			      squares[Enpassent.get(i).getCurrentPosition().getRow()-1][Enpassent.get(i).getCurrentPosition().getColumn()].setBorder(new LineBorder(Color.green, 3));
		 			     found=true;   
					  }else if(currentPiece.getCurrentPosition().getRow()==4&&playTurn%2==1)
					  {
						  squares[Enpassent.get(i).getCurrentPosition().getRow()][Enpassent.get(i).getCurrentPosition().getColumn()].setBorder(new LineBorder(Color.yellow, 3));
		 			      squares[Enpassent.get(i).getCurrentPosition().getRow()+1][Enpassent.get(i).getCurrentPosition().getColumn()].setBorder(new LineBorder(Color.green, 3));
		 			     found=true;
					  }
					  else
						  found=false;
					  
					   
	 			       
				  }
				  
			  }
			if(found)
				return true;
			else
				return false;
			
		}
		 
		public Boolean takeOver(Position OvertakePosition)
		  {
			 
			if(currentPiece!=null&&(OvertakePosition.getRow()-currentPiece.getCurrentPosition().getRow()==-1
	   			 ||OvertakePosition.getRow()-currentPiece.getCurrentPosition().getRow()==1))
			{
				for(int i=0;i<Enpassent.size();i++)
		  		  {
		  			 
		  			 if(currentPiece.getCurrentPosition().getRow()==3
				    			  &&(currentPiece.getCurrentPosition().getColumn()+1==Enpassent.get(i).getCurrentPosition().getColumn()
				    			  ||currentPiece.getCurrentPosition().getColumn()-1==Enpassent.get(i).getCurrentPosition().getColumn())
				    			  &&Enpassent.get(i).getPieceColor().equals("Black")&&playTurn%2==0&&currentPiece.getPieceColor().equals("White"))
				    	  {
			    			  if(Enpassent.get(i).getCurrentPosition().getColumn()!=OvertakePosition.getColumn())
			    				  continue;
		                     
			    			  
			    			  TakeOverremoved(OvertakePosition,i);
			    			  return true;
			    			  
				    	  }
				    	  else if(currentPiece.getCurrentPosition().getRow()==4
				    			  &&(currentPiece.getCurrentPosition().getColumn()+1==Enpassent.get(i).getCurrentPosition().getColumn()
				    			  ||currentPiece.getCurrentPosition().getColumn()-1==Enpassent.get(i).getCurrentPosition().getColumn())
				    			  &&Enpassent.get(i).getPieceColor().equals("White")&&playTurn%2==1&&currentPiece.getPieceColor().equals("Black"))
				    	  {
				    		  if(Enpassent.get(i).getCurrentPosition().getColumn()!=OvertakePosition.getColumn())
			    				  continue;
				    		  
				    		  TakeOverremoved(OvertakePosition,i);
				    	     
				    	      return true;
				    		  
				    	  }
				    	 

		  		  }
		  		  

			}
			return false;
		}
	    
	  	private void TakeOverremoved (Position positionOverTake,int index)
	  	{
	  		ChessPiece captured = chessBoard.getPiece(Enpassent.get(index).getCurrentPosition());
	  		squares[Enpassent.get(index).getCurrentPosition().getRow()][Enpassent.get(index).getCurrentPosition().getColumn()].setIcon(null);
	  		chessBoard.pieceCaptured(captured);
	  		ImageIcon iconHolder = (ImageIcon) squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].getIcon();
			  squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
			  
		      squares[positionOverTake.getRow()][positionOverTake.getColumn()].setIcon(iconHolder);
		      Enpassent.remove(index);
		      currentPiece.setCurrentPosition(positionOverTake);
		      currentPiece = null; 
		      playTurn++;
		      
	  	}
	  	public void OverTakeSaved(Position ButtonPosition)
	  	{
	  		if((currentPiece instanceof Pawn)&&
	  				(ButtonPosition.getRow()==4)
	  			  &&playTurn%2==0&&ButtonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==-2)
		      {
	  		  
	  		 Enpassent.add(currentPiece);
	  		 
	  	  }
	  	  else if ((currentPiece instanceof Pawn||chessBoard.getPiece(ButtonPosition) instanceof Pawn)&&ButtonPosition.getRow()==3&&playTurn%2==1&&ButtonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==2)
	  	  {
	  		  
	           Enpassent.add(currentPiece);
	           
	  	  }
	  	}
	  	public void OverTakeRemoved(Position positionRemoved)
	  	{
	  		 for(int i=0;i<Enpassent.size();i++)
			  {
				  if(currentPiece instanceof Pawn && 
					  currentPiece.getCurrentPosition().getRow()==Enpassent.get(i).getCurrentPosition().getRow()&&
					  currentPiece.getCurrentPosition().getColumn()==Enpassent.get(i).getCurrentPosition().getColumn()&&
							  positionRemoved.getColumn()==Enpassent.get(i).getCurrentPosition().getColumn()&&
									  positionRemoved.getColumn()!=Enpassent.get(i).getCurrentPosition().getRow())
				  {
					  Enpassent.remove(i);
				  }
			  }
	  	}
	 
	
	public void promote(int pieceType)
	{
		
		int row = currentPiece.getCurrentPosition().getRow();
		int column = currentPiece.getCurrentPosition().getColumn();
		
		switch(pieceType)
		{
		case 1:
			promotedPiece = new Queen(new Position(row ,column) , currentPiece.getPieceColor());
			break;
		case 2:
			promotedPiece = new Knight(new Position(row ,column) , currentPiece.getPieceColor());
			break;
		case 3:
			promotedPiece = new Bishop(new Position(row ,column) , currentPiece.getPieceColor());
			break;
		case 4:
			promotedPiece = new Rook(new Position(row ,column) , currentPiece.getPieceColor());
			break;
		}
		
		for (int i =0;i<chessBoard.getChessPieces().size();i++)
		{
			if (currentPiece.getCurrentPosition().getRow() == chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() &&
					currentPiece.getCurrentPosition().getColumn() == chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn())
				{
				   chessBoard.getChessPieces().remove(i);
				   break;
				}
		}
		
		chessBoard.getChessPieces().add(promotedPiece.cloneChessPiece());
		
		if (promotedPiece instanceof Queen)
		{
			if (promotedPiece.getPieceColor() == "White")
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/white_queen.png")));
			}
			else
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/black_queen.png")));
			}
		}
		else if (promotedPiece instanceof Knight)
		{
			if (promotedPiece.getPieceColor() == "White")
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/white_knight.png")));
			}
			else
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/black_knight.png")));
			}
		}
		else if (promotedPiece instanceof Bishop)
		{
			if (promotedPiece.getPieceColor() == "White")
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/white_bishop.png")));
			}
			else
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/black_bishop.png")));
			}
		}
		else if (promotedPiece instanceof Rook)
		{
			if (promotedPiece.getPieceColor() == "White")
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/white_rook.png")));
			}
			else
			{
				squares[row][column].setIcon(new ImageIcon(getClass().getResource("/assets/black_rook.png")));
			}
		}		
		afterPromotion();
	}
	
	abstract void afterPromotion();
	
	public void displayCapturedPieces()
	{
        playerOneCapturedPieces.removeAll();
        playerOneCapturedPieces.revalidate();
        playerOneCapturedPieces.repaint();
        
		playerTwoCapturedPieces.removeAll();
		playerTwoCapturedPieces.revalidate();
		playerTwoCapturedPieces.repaint();
		
		for (ChessPiece capturedPiece : chessBoard.getCapturedPieces())
		{
			if (capturedPiece.getPieceColor() == "White")
			{
				if  (capturedPiece instanceof Pawn)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_pawn.png")));
					playerOneCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Queen)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_queen.png")));
					playerOneCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof King)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_king.png")));
					playerOneCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Knight)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_knight.png")));
					playerOneCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Rook)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_rook.png")));
					playerOneCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Bishop)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_white_bishop.png")));
					playerOneCapturedPieces.add(holder);
				}
				
			}
			else
			{
				if  (capturedPiece instanceof Pawn)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_pawn.png")));
					playerTwoCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Queen)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_queen.png")));
					playerTwoCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof King)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_king.png")));
					playerTwoCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Knight)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_knight.png")));
					playerTwoCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Rook)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_rook.png")));
					playerTwoCapturedPieces.add(holder);
				}
				else if  (capturedPiece instanceof Bishop)
				{
					JLabel holder = new JLabel(new ImageIcon(getClass().getResource("/assets/small_black_bishop.png")));
					playerTwoCapturedPieces.add(holder);
				}
			}
		}
	}
	
}
 