package gamelogics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import board.ChessBoard;
import extra.Position;
import filters.KingFilterCriteria;
import game.Promotion;
import memento.ChessBoardCareTaker;
import memento.ChessBoardOriginator;
import memento.ChessBoardState;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
import players.Player;

public class EasyChessGame extends ChessGameLogic{

	private ChessBoardOriginator chessBoardOriginator;
	private ChessBoardCareTaker chessBoardCareTaker;	
	private JButton undo;
	private JButton redo;
	
	public EasyChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
		chessBoardOriginator = new ChessBoardOriginator();
		chessBoardCareTaker = new ChessBoardCareTaker();
		kingFilterCriteria = new KingFilterCriteria();
		
		extraUIFunctionality();
	}

	private void extraUIFunctionality()
	{
		undo = new JButton("");
		undo.setBackground(new Color(230,220,200));
		undo.setIcon(new ImageIcon(getClass().getResource("/assets/undo_icon.png")));
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    undo();
			}
		});
		undo.setBounds(208, 162, 50, 50);
		panel.add(undo);
		
		redo = new JButton("");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redo();
			}
		});
		redo.setBackground(new Color(230,220,200));
		redo.setIcon(new ImageIcon(getClass().getResource("/assets/redo_icon.png")));
		redo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		redo.setBounds(268, 162, 50, 50);
		panel.add(redo);
		
	    boardFrame.setVisible(true);
	}

	public void colorValidPositions(ArrayList<Position> positionsHolder)
	{
		for (Position position : positionsHolder)
		{
			if (chessBoard.hasPieceInPositon(position))
			{
				  squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.yellow , 3));
			}
			else
			{
			      squares[position.getRow()][position.getColumn()].setBorder(new LineBorder(Color.green , 3));
			}
		}
		
	}
	
	protected void gameLogic(Position buttonPosition)
	{		
		if (!squares[buttonPosition.getRow()][buttonPosition.getColumn()].getBorder().equals(new JButton().getBorder())&& !OvertakeCheckedColored())
	      {
	    	 // colored
			  if (playTurn == 0)
				  saveChessBoardState();
			  OverTakeRemoved(buttonPosition);
	    	  if(chessBoard.hasPieceInPositon(buttonPosition))
	    	  {
	    		  ChessPiece enemy = chessBoard.getPiece(buttonPosition);
	    		  squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
	    		  if(enemy instanceof King)
    			  {
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
    			  }
	    		  chessBoard.pieceCaptured(enemy);
	    	  }
	     	  	   OverTakeSaved(buttonPosition);
	    		  ImageIcon iconHolder = (ImageIcon) squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].getIcon();
	    		  squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition().getColumn()].setIcon(null);
	    	      squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(iconHolder);
	    	      removeColoredBorder();
	    	      
	    	      if(currentPiece instanceof King)
	    	    	  kingFilterCriteria.Castling(currentPiece, buttonPosition.getColumn());
		    	  
	    	      currentPiece.setFirstMove(true);
	    	      currentPiece.setCurrentPosition(buttonPosition);
	    	      if(kingFilterCriteria.Checkmate(kingFilterCriteria.getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
	    			  //here check mate\
	    	    	  JOptionPane.showMessageDialog(null, "Dead");
	    	      }
	    	      if(checkPromotion())
	    	      {
	    	    	  Promotion promotion = new Promotion(this);
	    	    	  boardFrame.setEnabled(false);
	    	      }
	    	      else
	    	      {
	    	        currentPiece = null;
	    	        promotedPiece = null;
	    	        playTurn++;
	    	        saveChessBoardState();
	    	        displayCapturedPieces();
	    	      }
	      }
	      else
	      {
	    	 // uncolored
	    	  if (chessBoard.hasPieceInPositon(buttonPosition) && hasTurn(buttonPosition)==true)
	    	  {
	    		  if(hasCurrentPiece())
	    		  {
	    			  removeColoredBorder();
	    		  }
	    		  currentPiece = chessBoard.getPiece(buttonPosition);
	    		  colorValidPositions(chessBoard.getValidPositions(currentPiece));
	    		  OvertakeCheckedColored();
	    	  }
	    	  else if (currentPiece instanceof Pawn &&(buttonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==-1
	    			  ||buttonPosition.getRow()-currentPiece.getCurrentPosition().getRow()==1))
	    	  {
	    		  takeOver(buttonPosition);
	    		  removeColoredBorder();
	    	  }
	    	  else 
	    	  {
	    		  removeColoredBorder();
	    	  }
	    	  
	      }
	  
	}
	
	public boolean hasTurn(Position position)
	{
		if (playTurn % 2 == 0 && chessBoard.getPiece(position).getPieceColor().equals("White"))
			return true;
		else if (playTurn % 2 == 1 && chessBoard.getPiece(position).getPieceColor().equals("Black"))
			return true;
		return false;
	}
		
	public void removeColoredBorder()
	{
		for (int i =0;i<8;i++)
			for (int j=0;j<8;j++)
			  squares[i][j].setBorder(UIManager.getBorder("Button.border"));
	
	}
	
	public boolean hasCurrentPiece()
	{
		return(currentPiece != null);
	}

	public void saveChessBoardState()
	{
		chessBoardOriginator.setState(new ChessBoardState(chessBoard.getChessPieces() , chessBoard.getCapturedPieces() , squares , playTurn));
		chessBoardCareTaker.addChessBoardState(chessBoardOriginator.saveStateToMemento());
		chessBoardCareTaker.deleteRedoChessBoardStates();
	}
	
	public void undo()
	{
	   try {
		    chessBoardOriginator.getStateFromMemento(chessBoardCareTaker.getUndoState());
			ChessBoardState chessBoardStateHolder = chessBoardOriginator.getState();
			
			if (!chessBoardStateHolder.equals(null))
			{
				chessBoard.setChessPieces(chessBoardStateHolder.getChessPieces());
				chessBoard.setCapturedPieces(chessBoardStateHolder.getCapturedPieces());
				setSquares(chessBoardStateHolder.getSquares());
				playTurn = chessBoardStateHolder.getPlayTurn();
				displayCapturedPieces();
			}
	   }catch(NullPointerException ex)
	   {
			JOptionPane.showMessageDialog(boardFrame, "Nothing to Undo");
	   }
		
	}

	public void redo()
	{
		try {
			chessBoardOriginator.getStateFromMemento(chessBoardCareTaker.getRedoState());
			ChessBoardState chessBoardStateHolder = chessBoardOriginator.getState();
			
			if (chessBoardStateHolder != null)
			{
				chessBoard.setChessPieces(chessBoardStateHolder.getChessPieces());
				chessBoard.setCapturedPieces(chessBoardStateHolder.getCapturedPieces());
				setSquares(chessBoardStateHolder.getSquares());
				playTurn = chessBoardStateHolder.getPlayTurn();
				displayCapturedPieces();
			}
		}catch(NullPointerException ex)
		{
			JOptionPane.showMessageDialog(boardFrame, "Nothing to Redo");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));;
					if (currentPiece != null)
					{
						holder = currentPiece;
					}
	              
					if(squares[i][j].getIcon()==null || playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							|| playTurn % 2 == 1 && holder.getPieceColor().equals("Black"))
						           gameLogic(new Position(i,j));
				}
			}
		}
	}

	@Override
	void afterPromotion() {
		currentPiece = null;
		promotedPiece = null;
		saveChessBoardState();
        displayCapturedPieces();	
		boardFrame.setEnabled(true);
        playTurn++;
	}
	
	
	
	
	
}
