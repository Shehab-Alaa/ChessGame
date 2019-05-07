package gamelogics;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import extra.Position;
import filters.KingFilterCriteria;
import pieces.ChessPiece;
import pieces.King;
import pieces.Pawn;
import players.Player;

public class HardVsComputerGame extends HardChessGame {

	ChessPiece bestPieceMove = null;
	Position bestPositionMove = null;

	public HardVsComputerGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}
	
	
	private void gameLogic(Position buttonPosition, ChessPiece piece) {
		if (checkFirst) {
			if (piece != null) {
				validMoves = chessBoard.getValidPositions(piece);
				currentImageIcon = (ImageIcon) squares[buttonPosition.getRow()][buttonPosition.getColumn()].getIcon();
				currentPiece = piece;
				OverTakeRemoved(buttonPosition);
				checkFirst = false;
			}
		} else {
			checkFirst = true;
			if (piece != null && piece.getPieceColor().equals(currentPiece.getPieceColor())) {
				gameLogic(buttonPosition, piece);
			}

			else {
				for (int i = 0; i < validMoves.size(); i++) {
					if (validMoves.get(i).getRow() == buttonPosition.getRow()
							&& validMoves.get(i).getColumn() == buttonPosition.getColumn()) {
						if (piece != null) {
							chessBoard.pieceCaptured(piece);
							squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(null);
						}
					
						OverTakeSaved(buttonPosition);
						squares[buttonPosition.getRow()][buttonPosition.getColumn()].setIcon(currentImageIcon);
						squares[currentPiece.getCurrentPosition().getRow()][currentPiece.getCurrentPosition()
						//currentImageIcon = null;
								.getColumn()].setIcon(null);
						
						
						
						if(currentPiece instanceof King)
			    	    	  kingFilterCriteria.Castling(currentPiece, buttonPosition.getColumn());
				    	  
			    	      currentPiece.setFirstMove(true);
						currentPiece.setCurrentPosition(buttonPosition);
						//System.out.println(currentPiece.getPieceColor());
						
						  if(new KingFilterCriteria().Checkmate(new KingFilterCriteria().getOppositeKingPiece(currentPiece.getPieceColor()),currentPiece)){
							//here check mate\
			    	    	  JOptionPane.showMessageDialog(null, "Dead");
			    	       }
						currentPiece = null;
						seconds = 30;
						playTurn++;
						execute();
					}
				}
				if (currentPiece instanceof Pawn
						&& (buttonPosition.getRow() - currentPiece.getCurrentPosition().getRow() == -1
								|| buttonPosition.getRow() - currentPiece.getCurrentPosition().getRow() == 1)) {
					takeOver(buttonPosition);
				}
			}
		}

	}

	private ArrayList<ChessPiece> makeMove(int indexOfHolderPiece, Position holderPiecePosition,
			ArrayList<ChessPiece> chessPieces) {
		ArrayList<ChessPiece> holderChessPieces = new ArrayList<ChessPiece>();
		for (ChessPiece chessPiece : chessPieces)
			holderChessPieces.add(chessPiece.cloneChessPiece());

		for (int i = 0; i < holderChessPieces.size(); i++) {
			if (holderChessPieces.get(i).getCurrentPosition().getRow() == holderPiecePosition.getRow()
					&& holderChessPieces.get(i).getCurrentPosition().getColumn() == holderPiecePosition.getColumn()) {
				holderChessPieces.get(indexOfHolderPiece).setCurrentPosition(holderPiecePosition);
		
				holderChessPieces.remove(i);

				return holderChessPieces;
			}
		}
		holderChessPieces.get(indexOfHolderPiece).setCurrentPosition(holderPiecePosition);
		return holderChessPieces;
	}

	private int minimax(ArrayList<ChessPiece> chessPieces, boolean checkMiniMax, int Depth, int alpha, int beta) {

		// String color=checkMiniMax? "White":"Black";
		if (Depth == 0) {
			return chessBoard.evaluateBoard(chessPieces);// evaluate board
		}
		int bestValue = (checkMiniMax) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

		for (int i = 0; i < chessPieces.size(); i++) {
			ArrayList<Position> positions;
			if ((checkMiniMax && chessPieces.get(i).getPieceColor().equals("White"))
					|| (!checkMiniMax && chessPieces.get(i).getPieceColor().equals("Black"))) {
				positions = chessBoard.getValidPositions(chessPieces.get(i));

				for (int j = 0; j < positions.size(); j++) {
					int currentValue = minimax(makeMove(i, positions.get(j), chessPieces), !checkMiniMax, Depth - 1,
							alpha, beta);

					if (checkMiniMax) {
						bestValue = Math.max(currentValue, bestValue);
						alpha = Math.max(bestValue, alpha);
					}

					else {
						bestValue = Math.min(currentValue, bestValue);
						beta = Math.min(bestValue, alpha);
					}

					if (beta <= alpha) {
						System.out.println(alpha + "   " + beta);
						return bestValue;

					}
				}
			}
		}
		return bestValue;
	}

	private void execute() {

		int minValue = Integer.MAX_VALUE;
		bestPieceMove = null;
		bestPositionMove = null;

		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {

			if (chessBoard.getChessPieces().get(i).getPieceColor().equals("Black")) {
				ArrayList<Position> positions = chessBoard.getValidPositions(chessBoard.getChessPieces().get(i));
				for (int j = 0; j < positions.size(); j++) {

					int currentValue = minimax(makeMove(i, positions.get(j), chessBoard.getChessPieces()), true, 3,
							Integer.MIN_VALUE, Integer.MAX_VALUE);
					if (currentValue <= minValue) {
						minValue = currentValue;
						bestPieceMove = chessBoard.getChessPieces().get(i);
						bestPositionMove = positions.get(j);
					}
				}
			}
		}
		// move here

		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {
			if (chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == bestPositionMove.getRow()
					&& chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == bestPositionMove
							.getColumn()) {
				chessBoard.getChessPieces().remove(i);
				break;
			}
		}

		ImageIcon iconHolder = (ImageIcon) squares[bestPieceMove.getCurrentPosition().getRow()][bestPieceMove
				.getCurrentPosition().getColumn()].getIcon();
		squares[bestPieceMove.getCurrentPosition().getRow()][bestPieceMove.getCurrentPosition().getColumn()]
				.setIcon(null);
		squares[bestPositionMove.getRow()][bestPositionMove.getColumn()].setIcon(iconHolder);
		bestPieceMove.setCurrentPosition(bestPositionMove);
		playTurn++;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares.length; j++) {
				if (event.getSource() == squares[i][j]) {
					ChessPiece holder = chessBoard.getPiece(new Position(i, j));
					if (holder != null && playTurn % 2 == 0 && holder.getPieceColor().equals("White")
							|| holder != null && playTurn % 2 == 1 && holder.getPieceColor().equals("Black")
							|| checkFirst == false)
						gameLogic(new Position(i, j), holder);
				}
			}
		}
	}

}
