package pieces;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class Case extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name; // useful ?
	int[] posIndex = new int[2];
	final int color;
	Board b = Game.b;
	
	String occuped = ""; 
	ArrayList<String> nextPieces = new ArrayList<String>();
	
	public void setCaseOccuped(String string) {occuped = string;}
	public String getCaseOccuped() {return occuped;}
	
	int caseAvailable = 0;
	int getCaseAvailable() {return caseAvailable;}
	void setCaseAvailable(int arg) {caseAvailable = arg;} //1 for white, -1 for black, 0 if case empty
	
	public int caseColor(int[] posIndex) throws IllegalArgumentException {
		if(!(posIndex.length == 2)) { 
			throw new IllegalArgumentException();
		} else if(((posIndex[0] + posIndex[1]) % 2 )== 0){
			return -1;
		} else {
			return 1;
		}
	}	
	public String caseName(int[] posIndex) {		
		return Board.abscisses[posIndex[0]] + Board.ordonnees[posIndex[1]];
}
		
	Case(int abs, int ord) {
		this.posIndex[0] = abs; //abs from 1 to 8
		this.posIndex[1] = ord; //ord from 1 to 8
		this.color = caseColor(posIndex);
		this.name = caseName(posIndex);
		}
	
	boolean aimed = false;
	public void drawCase(Graphics g) {
//	    super.paintComponent(g);
		if(this.color == 1) {g.setColor(Color.white);}
		else if(this.color == -1) {g.setColor(b.darkCaseColor);}
		else {throw new IllegalArgumentException("the case color is unknown");}
		  
	    int x = b.width + b.pas*this.posIndex[0];
	    int y = b.height + b.pas*(7-this.posIndex[1]);
		g.fillRect(x, y, b.width, b.height);
		if(aimed && this.caseAvailable == 0) {g.drawOval(x, y, b.width/2, b.width/2);
		}
	}
	
	
//	public JPanel JCase() {
//		
//		
//		JPanel pan = new JPanel();
//		Graphics g = pan.getGraphics();
//		
//		if(this.color == 1) {g.setColor(Color.white);}
//        else if(this.color == -1) {g.setColor(darkCaseColor);}
//        else {throw new IllegalArgumentException("the case color is unknown");}
//        
//        g.fillRect((this.x + this.pas*posIndex[0]), (this.y + this.pas*posIndex[1]), this.width, this.height);
//		
//		return pan;
//	}
	
	
	
//	public void paintComponent(Graphics g) throws IllegalArgumentException{
//        super.paintComponent(g);
//        
//        if(this.color == 1) {g.setColor(Color.white);}
//        else if(this.color == -1) {g.setColor(darkCaseColor);}
//        else {throw new IllegalArgumentException("the case color is unknown");}
//        
//        g.fillRect((this.x + this.pas*posIndex[0]), (this.y + this.pas*posIndex[1]), this.width, this.height);
//              
//        
//}
	
	
	
} // End class body
