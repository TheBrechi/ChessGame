package pieces;

import java.util.ArrayList;

public class Game implements Runnable{
	
	
		/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
		
	Display d;
	String input = "";
	public static Turn activeTurn = null; 
	public static Board b = null;
	boolean isCheckMate = false;
	ArrayList<String> moveList = new ArrayList<String>();
	
	Game(){
		b = new Board(); 
		b.createBoard();
		b.createPieces();
		d = new Display(b);
		d.createAndshowGUI();
	}
	//b.initializePieces(); not use anymore
	
	
	public void run(){
		activeTurn = new Turn();
		
		do {
			activeTurn.runTurn();
			if(!activeTurn.state) {
				moveList.add(activeTurn.moves);
				activeTurn = new Turn();
			}
//			if(activeThread == null) {activeThread = new Thread(activeTurn);activeThread.start();}
//			if(activeThread.isAlive()) {
//				try {
//					Thread.sleep(500); //static method
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}}
//			
			} while(!isCheckMate);	
	}
	
}
	
 

