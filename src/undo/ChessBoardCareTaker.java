package undo;

import java.util.Stack;

public class ChessBoardCareTaker {
	  private Stack<ChessBoardMemento> chessBoardStatesUndo = new Stack<ChessBoardMemento>();
	  private Stack<ChessBoardMemento> chessBoardStatesRedo = new Stack<ChessBoardMemento>();	  
	  
	  public void addChessBoardState(ChessBoardMemento chessBoardMemento){
	      chessBoardStatesUndo.push(chessBoardMemento);
	   }

	  public ChessBoardMemento getUndoState(){
		  if (chessBoardStatesUndo.size() >= 2)
		  { 
		     chessBoardStatesRedo.push(chessBoardStatesUndo.peek());
		     chessBoardStatesUndo.pop();
	         return chessBoardStatesUndo.peek();
		  }
		  return null; 
	   }
	  
	  public ChessBoardMemento getRedoState(){
		  ChessBoardMemento chessBoardMementoHolder = null;
		  if (!chessBoardStatesRedo.isEmpty()) {
		    chessBoardMementoHolder = chessBoardStatesRedo.peek();
		    chessBoardStatesRedo.pop();
		  }
		  return chessBoardMementoHolder;
	  }
	  
	  public void deleteRedoChessBoardStates()
	  {
		  chessBoardStatesRedo.clear();
	  }

}
