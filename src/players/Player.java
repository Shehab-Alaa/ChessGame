package players;

public class Player {
   private String name;
   private String pieceColor;
   
   public Player(String name , String pieceColor)
   {
	   this.name = name;
	   this.pieceColor = pieceColor;
   }
   
   public String getName() {
		return name;
	}

	public String getPieceColor() {
		return pieceColor;
	}

}
