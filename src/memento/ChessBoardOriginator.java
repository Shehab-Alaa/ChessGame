package memento;


public class ChessBoardOriginator {
	   
	private ChessBoardState chessBoardState;

	public void setState(ChessBoardState chessBoardState){
	      this.chessBoardState = chessBoardState;
	   }

    public ChessBoardState getState(){
	      return chessBoardState; 
	   }

	public ChessBoardMemento saveStateToMemento(){
	      return new ChessBoardMemento(chessBoardState);
	   }

	public void getStateFromMemento(ChessBoardMemento chessBoardMemento){
	      chessBoardState = chessBoardMemento.getState();
	   }

}
