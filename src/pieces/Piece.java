package pieces;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Piece extends Board{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel; public void setJPanel(JPanel panel){this.panel = panel;};
	String pieceName;
	int pieceColor; // 1 for white, -1 for black
	int[] piecePosition = new int[2];
	boolean isAlive; public void dead() {isAlive = false; removePiece(this);}
	
	String piece = ""; private String color;
    String path = "C:/Users/maxbr/eclipse-workspace/ChessGame/src/ChessPieces/";
	
    Image kwIcone = null;
	public boolean hasAlreadyMoved = false;
    	
	void downloadImage() {
		
		String colorLetter = pieceName.charAt(pieceName.length()-1)+"";
		
		if(colorLetter.charAt(0) == "w".charAt(0)) {color = "white";}
	    else if(colorLetter.charAt(0) == "b".charAt(0)) {color = "black";}
	    else {throw new IllegalArgumentException("t'as encore foiré");}
		
		switch( ""+pieceName.charAt(0) ){
		    case "p":
		    	piece = "Pawn";
		    	break;
		    case "n":
		    	piece = "Knight";
		    	break;
		    case "b":
		    	piece = "Bishop";
		    	break;
		    case "r":
		    	piece = "Rook";
		    	break;
		    case "q":
		    	piece = "Queen";
		    	break;
		    case "k":
		    	piece = "King";
		    	break;
		    }
			
		   try {
				kwIcone = ImageIO.read(new File(path + color + piece + ".png"));
				kwIcone = kwIcone.getScaledInstance(width, height, 2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    
	}
	
	void setNewPos(int[] newPosition) {
		if(piecePosition != null) {
			findCase(piecePosition).setCaseAvailable((int) 0);
			findCase(piecePosition).setCaseOccuped("");
		}
		this.piecePosition = newPosition;
		findCase(newPosition).setCaseAvailable((int) this.pieceColor);
		findCase(newPosition).setCaseOccuped(pieceName);
		this.hasAlreadyMoved = true;
		}
	
	
	public int[][] getListMovement(){return new int[0][0];}
	
		public void drawPiece(Graphics g) {
//	    super.paintComponent(g);
		if(kwIcone==null) {this.downloadImage();}
	    if(this.isAlive) {
		    g.drawImage(kwIcone, width + pas*piecePosition[0], height + pas*(7-piecePosition[1]), null);
	    }
	}			
	      
	    
	    
	    
}
	

