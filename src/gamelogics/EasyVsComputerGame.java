package gamelogics;

import java.awt.event.ActionEvent;

import extra.Position;
import pieces.ChessPiece;
import players.Player;

public class EasyVsComputerGame extends EasyChessGame{

	public EasyVsComputerGame(Player playerOne, Player playerTwo) {
		super(playerOne, playerTwo);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		for (int i = 0;i<squares.length;i++)
		{
			for (int j=0;j<squares.length;j++)
			{
				if (event.getSource() == squares[i][j])
				{
					
				}
			}
		}
	}

}
