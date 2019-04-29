package undo;


public class ChessBoardMemento {
     private ChessBoardState chessBoardState;
     
     public ChessBoardMemento(ChessBoardState chessBoardState){
	      this.chessBoardState = chessBoardState;
	   }

	 public ChessBoardState getState(){
	      return chessBoardState;
	   }	
}
