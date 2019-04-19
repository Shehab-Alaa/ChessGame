package gamelogics;

import java.awt.event.ActionEvent;

import extra.Position;
import pieces.ChessPiece;
import players.Player;

public class HardChessGame extends ChessGameLogic{

	public HardChessGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}

	private void pressedButton(Position buttonPosition)
	{
		// hard logic is here --
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					ChessPiece holder = chessBoard.getPiece(new Position(i,j));
					if(squares[i][j].getIcon()==null || EasyChessGame.playTurn % 2 == 0 && holder.getPieceColor().equals("White") 
							||EasyChessGame.playTurn % 2 == 1 && holder.getPieceColor().equals("Black"))
						           pressedButton(new Position(i,j));
				}
			}
		}
	}

}
