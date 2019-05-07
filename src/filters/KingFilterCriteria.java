package filters;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import board.ChessBoard;
import extra.Position;
import gamelogics.ChessGameLogic;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Rook;

public class KingFilterCriteria implements FilterCriteria {

	public ChessPiece getOppositeKingPiece(String color) {
		for (ChessPiece chessPiece : chessBoard.getChessPieces()) {
			if (chessPiece instanceof King && chessPiece.getPieceColor() != color)
				return chessPiece;
		}
		return null;
	}

	public ChessPiece getKingPiece(String color) {
		for (ChessPiece chessPiece : chessBoard.getChessPieces()) {
			if (chessPiece instanceof King && chessPiece.getPieceColor() == color)
				return chessPiece;
		}
		return null;
	}

	private boolean filterationHelper(Position position, ArrayList<Position> bigvalidpositions,
			int indexofbigvalidpositions, ChessPiece ChessPiece) {
		ArrayList<Position> enemyValidPositions, currentPieceValidPositions;
		Position current = ChessPiece.getCurrentPosition();
		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {
			if (!chessBoard.getChessPieces().get(i).getPieceColor().equals(ChessPiece.getPieceColor())
					&& (!(chessBoard.getChessPieces().get(i) instanceof King))) {
				ChessPiece.setCurrentPosition(position);
				ChessPiece king = getKingPiece(ChessPiece.getPieceColor());
				currentPieceValidPositions = new ArrayList<Position>(chessBoard.getValidPositonsArray());
				enemyValidPositions = chessBoard.filter(chessBoard.getChessPieces().get(i));
				chessBoard.setValidPositions(new ArrayList<Position>(currentPieceValidPositions));
				for (int j = 0; j < enemyValidPositions.size(); j++) {
					if (enemyValidPositions.get(j).getRow() == king.getCurrentPosition().getRow()
							&& enemyValidPositions.get(j).getColumn() == king.getCurrentPosition().getColumn()) {
						if (chessBoard.getChessPieces().get(i).getCurrentPosition().getRow() == position.getRow()
								&& chessBoard.getChessPieces().get(i).getCurrentPosition().getColumn() == position
										.getColumn())
							continue;
						bigvalidpositions.remove(indexofbigvalidpositions);
						ChessPiece.setCurrentPosition(current);
						return true;
					}
				}
			}

		}

		ChessPiece.setCurrentPosition(current);
		return false;
	}

	public ArrayList<Position> checkKingProtection(ArrayList<Position> validPositions, ChessPiece ChessPiece) {
		for (int i = 0; i < validPositions.size();) {
			if (!filterationHelper(validPositions.get(i), validPositions, i, ChessPiece))
				i++;
		}
		return validPositions;
	}

	public boolean Checkmate(ChessPiece kingChessPiece, ChessPiece ChessPiece) {

		ArrayList<Position> currentPiecevalidPosition = new ArrayList<Position>(chessBoard.getValidPositonsArray());
		ArrayList<Position> ChessPiecevalidPosition = new ArrayList<Position>(chessBoard.filter(ChessPiece));

		chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));

		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {
			if (chessBoard.getChessPieces().get(i).getPieceColor().equals(kingChessPiece.getPieceColor())) {
				ArrayList<Position> beforprotection = new ArrayList<Position>(
						chessBoard.filter(chessBoard.getChessPieces().get(i)));
				chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));
				int size = beforprotection.size();
				ArrayList<Position> positiondAfterProtection = new ArrayList<Position>(
						checkKingProtection(beforprotection, chessBoard.getChessPieces().get(i)));

				chessBoard.setValidPositions(new ArrayList<Position>(currentPiecevalidPosition));

				if (size >= positiondAfterProtection.size()) {
					for (int j = 0; j < positiondAfterProtection.size(); j++) {
						for (int k = 0; k < ChessPiecevalidPosition.size(); k++) {

							if (positiondAfterProtection.get(j).getRow() == ChessPiecevalidPosition.get(k).getRow()
									&& positiondAfterProtection.get(j).getColumn() == ChessPiecevalidPosition.get(k)
											.getColumn()
									|| positiondAfterProtection.get(j).getRow() == ChessPiece.getCurrentPosition()
											.getRow()
											&& positiondAfterProtection.get(j).getColumn() == ChessPiece
													.getCurrentPosition().getColumn()) {
								return false;
							}

						}

					}
				}
			}
		}

		int length = kingChessPiece.getValidMoves().size();
		chessBoard.setValidPositions(chessBoard.filter(kingChessPiece));
		chessBoard.setValidPositions(currentPiecevalidPosition);
		if (length != chessBoard.getFriendCounter() && length == chessBoard.getFriendCounter() + chessBoard.getEnemyCounter()) {
			return true;
		}
		return false;
	}

	@Override
	public void filterPositions(ChessPiece chessPieceHolder) {
		
		chessBoard.setEnemyCounter(0);
		chessBoard.setFriendCounter(0);
		
		chessBoard.setValidPositions(chessPieceHolder.getValidMoves());
		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {
			if (chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())) {
				for (int j = 0; j < chessBoard.getValidPositonsArray().size();) {

					if (chessBoard.getValidPositonsArray().get(j).getColumn() == chessBoard.getChessPieces().get(i)
							.getCurrentPosition().getColumn()
							&& chessBoard.getValidPositonsArray().get(j).getRow() == chessBoard.getChessPieces().get(i)
									.getCurrentPosition().getRow()) {

						chessBoard.incrementFriendCounter();
						chessBoard.getValidPositonsArray().remove(j);
					} else
						j++;
				}
			}
		}
		for (int i = 0; i < chessBoard.getChessPieces().size(); i++) {

			if (!chessBoard.getChessPieces().get(i).getPieceColor().equals(chessPieceHolder.getPieceColor())) {

				filterEnemyPositions(chessBoard.getChessPieces().get(i), chessPieceHolder,
						chessBoard.getValidPositonsArray());

			}
		}

		addCastligPositions(chessPieceHolder);

	}

	private void filterEnemyPositions(ChessPiece enemy, ChessPiece King, ArrayList<Position> validPositionsOfKing) {
		ArrayList<Position> validPositionsHolder;
		if (enemy instanceof King) {
			for (int j = 0; j < validPositionsOfKing.size();) {
				double length = Math.floor((Math.sqrt((Math
						.pow((validPositionsOfKing.get(j).getRow() - enemy.getCurrentPosition().getRow()), 2))
						+ (Math.pow((validPositionsOfKing.get(j).getColumn() - enemy.getCurrentPosition().getColumn()),
								2)))));
				if (length == 1.0) {
					chessBoard.incrementEnemyCounter();
					chessBoard.getValidPositonsArray().remove(j);
				} else
					j++;
			}
		} else {
			ArrayList<ChessPiece> removedpieces = new ArrayList<ChessPiece>();

			for (int i = 0; i < validPositionsOfKing.size(); i++) {
				for (int j = 0; j < chessBoard.getChessPieces().size();) {
					if (chessBoard.getChessPieces().get(j).getCurrentPosition().getRow() == validPositionsOfKing.get(i)
							.getRow()
							&& !chessBoard.getChessPieces().get(j).getPieceColor().equals(King.getPieceColor())) {
						removedpieces.add(chessBoard.getChessPieces().get(j));
						chessBoard.getChessPieces().remove(j);
					} else
						j++;
				}
			}
			validPositionsHolder = chessBoard.filter(enemy);
			for (int j = 0; j < validPositionsHolder.size(); j++) {
				for (int ii = 0; ii < validPositionsOfKing.size();) {
					if ((validPositionsOfKing.get(ii).getColumn() == validPositionsHolder.get(j).getColumn()
							&& validPositionsOfKing.get(ii).getRow() == validPositionsHolder.get(j).getRow()
							&& !(enemy instanceof Pawn))
							|| (validPositionsOfKing.get(ii).getColumn() == validPositionsHolder.get(j).getColumn()
									&& validPositionsOfKing.get(ii).getRow() == validPositionsHolder.get(j).getRow()
									&& validPositionsHolder.get(j).getColumn() != enemy.getCurrentPosition().getColumn()
									&& enemy instanceof Pawn)) {
						// if (!(enemy instanceof Knight))//&&
						// !chessBoard.hasPieceInPositon(validPositionsOfKing.get(ii)))
						chessBoard.incrementEnemyCounter();
						validPositionsOfKing.remove(ii);
					} else
						ii++;
				}
			}
			chessBoard.getChessPieces().addAll(removedpieces);
		}
		chessBoard.setValidPositions(validPositionsOfKing);
	}

	private void assignNewPosition(ChessPiece kingPiece, ChessPiece rook, int column, int i) {
		ImageIcon iconHolder = (ImageIcon) ChessGameLogic.getSquares()[rook.getCurrentPosition().getRow()][rook
				.getCurrentPosition().getColumn()].getIcon();
		ChessGameLogic.getSquares()[rook.getCurrentPosition().getRow()][rook.getCurrentPosition().getColumn()]
				.setIcon(null);
		ChessGameLogic.getSquares()[kingPiece.getCurrentPosition().getRow()][column + i].setIcon(iconHolder);
		rook.setCurrentPosition(new Position(kingPiece.getCurrentPosition().getRow(), column + i));
	}

	public void Castling(ChessPiece kingPiece, int column) {
		ChessPiece rook;
		if (Math.abs(kingPiece.getCurrentPosition().getColumn() - column) == 2) {
			if (kingPiece.getCurrentPosition().getColumn() > column) {
				rook = chessBoard.getPiece(new Position(kingPiece.getCurrentPosition().getRow(), 0));
				assignNewPosition(kingPiece, rook, column, 1);
				
			} else {
				rook = chessBoard.getPiece(new Position(kingPiece.getCurrentPosition().getRow(), 7));
				assignNewPosition(kingPiece, rook, column, -1);

			}
			rook.setFirstMove(true);
		}
	}

	private void addCastligPositions(ChessPiece chessPieceHolder) {

		if (!chessPieceHolder.isFirstMove() && chessBoard.getCurrentPiece() instanceof King) {
			for (ChessPiece rook : chessBoard.getChessPieces()) {
				if (rook instanceof Rook && !rook.isFirstMove()
						&& rook.getPieceColor() == chessPieceHolder.getPieceColor()) {
					for (int i = Math.min(rook.getCurrentPosition().getColumn(),
							chessPieceHolder.getCurrentPosition().getColumn()) + 1; i < Math.max(
									rook.getCurrentPosition().getColumn(),
									chessPieceHolder.getCurrentPosition().getColumn()); i++) {
						if (chessBoard
								.hasPieceInPositon(new Position(chessPieceHolder.getCurrentPosition().getRow(), i)))
							break;
						else if (chessPieceHolder.getCurrentPosition().getColumn() > rook.getCurrentPosition()
								.getColumn()) {
							chessBoard.getValidPositonsArray()
									.add(new Position(chessPieceHolder.getCurrentPosition().getRow(),
											chessPieceHolder.getCurrentPosition().getColumn() - 2));
						} else {
							chessBoard.getValidPositonsArray()
									.add(new Position(chessPieceHolder.getCurrentPosition().getRow(),
											chessPieceHolder.getCurrentPosition().getColumn() + 2));
						}
					}

				}
			}
		}
	}

}
