package players;

public class Computer extends Player{

	public Computer(String name, String pieceColor) {
		super(name, pieceColor);
	}
	
	public Computer()
	{
		super("Computer" , "Black");
	}
}
