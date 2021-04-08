package pieces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Board extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final static String[] abscisses = {"a", "b" ,"c", "d", "e", "f", "g", "h"};
	final static String[] ordonnees = {"1", "2", "3", "4", "5", "6", "7", "8"};
	
	static ArrayList<Case> board = new ArrayList<>();
	public ArrayList<Case> getCaseList() {return board;}
	
	static ArrayList<Piece> whitePieces = new ArrayList<>();
	static ArrayList<Piece> blackPieces = new ArrayList<>(); 
	
	public static boolean isRoqueAllowed = false; public static void resetRoque() {isRoqueAllowed = false;}
	
	Piece targetPiece = null;
	Case targetCase = null;
		
	int width = 50;
	int height = 50;
//	int x = 100;
//	int y = 100;
	int pas = width;
	Color darkCaseColor = new Color((float) 136/255,(float) 66/255,(float) 29/255);
	
	
	
	
	
	
	
	public ArrayList<Piece> getListPieces(int color){
		if(color==1) {return whitePieces;}
		else if(color==-1) {return blackPieces;}
		else if(color==0){
			ArrayList<Piece> list = new ArrayList<Piece>();
			list.addAll(whitePieces);list.addAll(blackPieces);
			return list;
		}
		else {throw new IllegalArgumentException();}
	}
	
	
	public Board() {
			this.setPreferredSize(new Dimension(10*width, 10*height));
//			this.setLayout(new GridBagLayout());
			this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.print("click on :");
				Case caseAimed = findTargetCase(e.getX(),e.getY());
				System.out.print(caseAimed.name+"\n");
				
				if(targetPiece == null) {
				targetPiece = findPiece(caseAimed.occuped);
				try {
					System.out.print("Piece is " + targetPiece.pieceName+"\n");
				} catch (Exception exc) {}
				
				} else {
					targetCase = findTargetCase(e.getX(),e.getY());
					boolean b = false;
					if(targetPiece.pieceColor == Game.activeTurn.color) {
						b = moveIsDoable(targetPiece, targetCase);
					}
				
					if(b) {
						Game.activeTurn.pieceMovement(targetPiece, targetCase);
						e.consume();
						}
					
					targetPiece = null;
					targetCase = null;					
				}
				e.consume();
			}	
				
			

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		
	});
			}//END CONSTRUCTOR
	
	public boolean isInArray(int[][] arr , int[] elt ) {
		/**Does not check the sign*/
		for( int[] elt_arr : arr) {
			if(Math.abs(elt_arr[0]) == Math.abs(elt[0]) 
					&& Math.abs(elt_arr[1]) == Math.abs(elt[1])) {return true;}
		}
		return false;
		
	}

	public String indexToString(int[] index) {
		String finalString = "";
			finalString = abscisses[index[0]] + ordonnees[index[1]];
		return finalString;
	}
	
	public int[] stringToIndex(String string)  throws IllegalArgumentException{
		
		if(string.length() != 2) {
			throw new IllegalArgumentException("String argument length must be 2"); 
		}
		
		int[] finalIndex = new int[2];
		int finalI = -1;
		int finalJ = -1;
		int increment = 0;
		char[] listTemp = string.toCharArray();
		
		for(String abs : abscisses) {
			if(listTemp[0] == abs.charAt(0)) {
				finalI = increment;
				}
			increment++;
		}
		
		increment = 0;
		for(String ord : ordonnees) {
			if(listTemp[1] == ord.charAt(0)) {
				finalJ = increment;
				}
			increment++;
		}
		
		if((finalI == -1) || (finalJ == -1)) {
			System.out.println("error durring conversion");
			return null;
			}
		finalIndex[0] = finalI;
		finalIndex[1] = finalJ; 
		
		return finalIndex;
	}
	public int columnToIndex(String str) {
		for(int i=0;i<8;i++) { if(abscisses[i]==str) {return i;} }
		throw new IllegalArgumentException();
	}
	
	public Case findCase(int[] index) {
		if(board.size() == 0 || index.length != 2) {throw new IllegalArgumentException("board empty");}
		for(Case var_case : board) {
			if(var_case.posIndex[0] == index[0]
					&& var_case.posIndex[1] == index[1]) {return var_case;}
		}
		return null;
		
	}
	
	private ArrayList<Piece> targetPieces = new ArrayList<Piece>();
	public ArrayList<Piece> getTargetPieces(){return targetPieces;}
	public void findPiece( String str, Case nextCase, int color){
		targetPieces.clear();
		ArrayList<Piece> list = this.getListPieces(color);
		
		for(Piece elt : list) { if(
					(elt.pieceName.charAt(0) == str.charAt(0)) 
					&& moveIsDoable(elt, nextCase))
			{targetPieces.add(elt);} }
	}
	
	public Piece findOnPassant(Piece p, int[] move) {
		if(p.pieceColor == 1) {
			for(Piece el : blackPieces) {
				if(el.piecePosition[1] == p.piecePosition[1]
						&& el.piecePosition[0] == (p.piecePosition[0] + move[0])) {
					return el;
				}
			}
		} else if(p.pieceColor == -1) {
			for(Piece el : blackPieces) {
				if(el.piecePosition[1] == p.piecePosition[1]
						&& el.piecePosition[0] == (p.piecePosition[0] + move[0])) {
					return el;
				}
			}
		}
		return null; 
	}
	
	public Piece findPiece( String str){
		ArrayList<Piece> list = new ArrayList<Piece>();
		list.addAll(whitePieces);list.addAll(blackPieces);
		
		if(str.length()==1) {throw new IllegalArgumentException("Maybe a parameter of type Case is missing");
		} else { for(Piece elt : list) { if(elt.pieceName.equals(str)) {return elt;}}}
		return null;
	}
	
	public Case findTargetCase(int x, int y) {
		int i = x / width - 1;
		int j = 7 - y / height + 1;
		int[] ind = {i, j};
		
		return findCase(ind);
	}
	
	public void removePiece(Piece piece) {
		if(piece.pieceColor == 1) {whitePieces.remove(piece);}
		else if(piece.pieceColor == -1) {blackPieces.remove(piece);}
		else {throw new IllegalArgumentException();}
	}
	
	public int[] vector(Piece piece, Case ncase){
		int var_x =  (ncase.posIndex[0] - piece.piecePosition[0]); 
		int var_y =  (ncase.posIndex[1] - piece.piecePosition[1]); 
		int[] var_xy = {var_x, var_y};
		return var_xy;
	}
		
	public void createBoard() {	
		for(int i = 0; i<8; i++  ){
			for(int j = 0; j<8; j++) {
				board.add(new Case(i,j));
			}
		}
	}
	
	public void createPieces() {
		
	
	/*white side*/
		Piece p1w = new Pawn("p1w", 1, 0, 1); whitePieces.add(p1w); //default parameter ("p1w", 1, 0, 1)
		Piece p2w = new Pawn("p2w", 1, 1, 1); whitePieces.add(p2w); //default parameter ("p2w", 1, 1, 1)
		Piece p3w = new Pawn("p3w", 1, 2, 1); whitePieces.add(p3w); //default parameter 
		Piece p4w = new Pawn("p4w", 1, 3, 1); whitePieces.add(p4w); //default parameter 
		Piece p5w = new Pawn("p5w", 1, 4, 1); whitePieces.add(p5w); //default parameter 
		Piece p6w = new Pawn("p6w", 1, 5, 1); whitePieces.add(p6w); //default parameter 
		Piece p7w = new Pawn("p7w", 1, 6, 1); whitePieces.add(p7w); //default parameter 
		Piece p8w = new Pawn("p8w", 1, 7, 1); whitePieces.add(p8w); //default parameter 
	
		Piece n1w = new Knight("n1w", 1, 1, 0); whitePieces.add(n1w);
		Piece n2w = new Knight("n2w", 1, 6, 0); whitePieces.add(n2w);
		
		Piece b1w = new Bishop("b1w", 1, 2, 0); whitePieces.add(b1w);
		Piece b2w = new Bishop("b2w", 1, 5, 0); whitePieces.add(b2w);
		
		Piece r1w = new Rook("r1w", 1, 0, 0); whitePieces.add(r1w);
		Piece r2w = new Rook("r2w", 1, 7, 0); whitePieces.add(r2w);
		
		Piece qw = new Queen("qw", 1, 3, 0); whitePieces.add(qw);	
		Piece kw = new King("kw", 1, 4, 0); whitePieces.add(kw);
		
		

	/*black side*/
		Piece p1b = new Pawn("p1b", -1, 0, 6); blackPieces.add(p1b); //default parameter 
		Piece p2b = new Pawn("p2b", -1, 1, 6); blackPieces.add(p2b); //default parameter 
		Piece p3b = new Pawn("p3b", -1, 2, 6); blackPieces.add(p3b); //default parameter 
		Piece p4b = new Pawn("p4b", -1, 3, 6); blackPieces.add(p4b); //default parameter 
		Piece p5b = new Pawn("p5b", -1, 4, 6); blackPieces.add(p5b); //default parameter 
		Piece p6b = new Pawn("p6b", -1, 5, 6); blackPieces.add(p6b); //default parameter 
		Piece p7b = new Pawn("p7b", -1, 6, 6); blackPieces.add(p7b); //default parameter 
		Piece p8b = new Pawn("p8b", -1, 7, 6); blackPieces.add(p8b); //default parameter 
	
		Piece n1b= new Knight("n1b", -1, 1, 7); blackPieces.add(n1b);
		Piece n2b = new Knight("n2b", -1, 6, 7); blackPieces.add(n2b);
		
		Piece b1b = new Bishop("b1b", -1, 2, 7); blackPieces.add(b1b);
		Piece b2b = new Bishop("b2b", -1, 5, 7); blackPieces.add(b2b);
		
		Piece r1b = new Rook("r1b", 1, 0, 7); whitePieces.add(r1b);
		Piece r2b = new Rook("r2b", 1, 7, 7); whitePieces.add(r2b);
		
		Piece qb = new Queen("qb", -1, 3, 7); blackPieces.add(qb);
		Piece kb = new King("kb", -1, 4, 7); blackPieces.add(kb);
		
	}
		
//	public void initializePieces() {
//	
//		
//		int[] newPosition = {0,0};
//		
//		findPiece("r1w").setNewPos(newPosition);
//		newPosition[0] = 1; findPiece("n1w").setNewPos(newPosition);
//		newPosition[0] = 2; findPiece("b1w").piecePosition[0] = 1;
//		abs++; newPosition[0] = abs; findPiece("qw").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("kw").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("b2w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("n2w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("r2w").setNewPos(newPosition);
//		
//		ord++; newPosition[1] = ord; 
//		abs = 0; newPosition[0] = abs; findPiece("p1w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p2w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p3w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p4w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p5w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p6w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p7w").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p8w").setNewPos(newPosition);
		
//		ord = 7; newPosition[1] = ord;
//		abs = 0; newPosition[0] = abs;
//		findPiece("r1b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("n1b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("b1b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("kb").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("qb").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("b2b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("n2b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("r2b").setNewPos(newPosition);
//		
//		ord--; newPosition[1] = ord; 
//		abs = 0; newPosition[0] = abs; findPiece("p1b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p2b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p3b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p4b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p5b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p6b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p7b").setNewPos(newPosition);
//		abs++; newPosition[0] = abs; findPiece("p8b").setNewPos(newPosition);
//		
//		
//		 //END BLACK PIECES INITIATION
//		
//}

	
	
	public boolean isPathClear(Piece piece, Case nextCase) {
		
		
		if(piece instanceof Knight) {return true;}
		
		int[] var_xy = vector(piece, nextCase);
		int var_x = var_xy[0];
		int var_y = var_xy[1];
//		if(!isInArray(piece.getListMovement(), var_xy)) {return false;} //useful?
		
		int[] pieceFictive = new int[2];
		pieceFictive[0]= piece.piecePosition[0] +  Integer.signum(var_x);
		pieceFictive[1] = piece.piecePosition[1] + Integer.signum(var_y);
		
		int safetyCounter = 0;	
		while(!(pieceFictive[0] == nextCase.posIndex[0] 
				&& pieceFictive[1] == nextCase.posIndex[1])  
				&& safetyCounter < 8
				&& !(pieceFictive[0]<0 || pieceFictive[1]<0
					|| pieceFictive[0]>7 || pieceFictive[1]>7)){

			Case caseFictive = findCase(pieceFictive); 
			if(caseFictive.getCaseAvailable() != 0) {return false;}

			if(var_x<0) {pieceFictive[0] -= 1;}
			if(var_x>0) {pieceFictive[0] += 1;}
			if(var_y<0) {pieceFictive[1] -= 1;}
			if(var_y>0) {pieceFictive[1] += 1;}
			
			
			safetyCounter++;
		}
		return true;
	}

	ArrayList<Piece> pieceKingCheck = new ArrayList<Piece>();
	public boolean isCheck(int col) {
		int[] v;
		boolean bool;
		pieceKingCheck.clear();		
//		System.out.print(col+"\n");
		
		
		if( col == -1 ) {
			Case whiteKingCase = findCase(findPiece("kw").piecePosition);
			
			for(Piece pb : blackPieces) {
				v = vector(pb, whiteKingCase);
				if(pb instanceof Pawn) {
					bool = (Math.abs(v[0]) == 1) && (Math.abs(v[1]) == 1);
				} else {
					bool = isInArray(pb.getListMovement(), v);
				}
				if(bool && isPathClear(pb, whiteKingCase)) {pieceKingCheck.add(pb);}
				}
			
		} 
			if(col == 1) {
				Case blackKingCase = findCase(findPiece("kb").piecePosition);
				
				for(Piece pw : whitePieces) {
					v = vector(pw, blackKingCase);
					if(pw instanceof Pawn) {
						bool = (v[0] != 0) && (v[1] != 0);
					} else {
						bool = isInArray(pw.getListMovement(), v);
					}
					if(bool && isPathClear(pw, blackKingCase)) {pieceKingCheck.add(pw);}
				}
	
			} 
		
		if(pieceKingCheck.isEmpty()) {return false;}
		else { 
			String out = "";
			for( Piece elt : pieceKingCheck) {out += elt.pieceName+" ";} 
			out += "check";
			System.out.print(out+"\n");
			return true;
		}
		
		
	}
	
	boolean checkmate = false; public boolean getCheckMate() {return checkmate;}
	public void isCheckmate() {
		if(pieceKingCheck.size()>1) {checkmate = true;}
		else{
			String color = "w";
			Case KingCheck = findCase(findPiece("k"+ color).piecePosition);
			
			for(Piece p : whitePieces) {
				for(int[] ind : p.getListMovement()) {
					ind[0] += p.piecePosition[0];
					ind[1] += p.piecePosition[1];
					if(moveIsDoable(p, findCase(ind))){
						
					}
				}
			}
			
		}
		
	}
		
	static Piece onPassantW = null; public void setOnPassantW(Piece p) {onPassantW = p; System.out.print("On Passant Piece cache: " + onPassantW.pieceName +"\n");}
	static Piece onPassantB = null; public void setOnPassantB(Piece p) {onPassantB = p; System.out.print("On Passant Piece cache: " + onPassantB.pieceName +"\n");}
	
	
	
	public boolean moveIsDoable(Piece piece, Case nextCase) {
		if(piece == null || nextCase == null) {return false;}
		
		
		int[] var_xy = vector(piece, nextCase);
		int var_x = var_xy[0];
		int var_y = var_xy[1];
		
		
		boolean moveIsDoable = isInArray( piece.getListMovement() , var_xy) 
				&& isPathClear(piece, nextCase) 
				&& piece.pieceColor != nextCase.getCaseAvailable();
		
		if(piece instanceof Pawn && moveIsDoable) {
		
//			mouvIsDoable = (!(((Pawn) piece).hasAlreadyMove()) && Math.abs(var_y) == 2)
//					^ ((nextCase.getCaseAvailable() == 0) && (var_x == 0))
//					^ ((nextCase.getCaseAvailable()==-piece.pieceColor) && (Math.abs(var_x)==1)); 
			boolean isOnPassantAllowed = false; 
			if(onPassantB != null && piece.pieceColor == 1) {isOnPassantAllowed = nextCase.posIndex[0] == onPassantB.piecePosition[0];
			} else if(onPassantW != null && piece.pieceColor == -1) {isOnPassantAllowed = nextCase.posIndex[0] == onPassantW.piecePosition[0];
			}		
					try {
					System.out.print("On Passant? " + isOnPassantAllowed +"\n");
					} catch(Exception e) {}
			
			if(Math.abs(var_y) == 2) {moveIsDoable = !(((Pawn) piece).hasAlreadyMoved);}
			else if(var_x == 0) {moveIsDoable = (nextCase.getCaseAvailable() == 0);}
			else if(Math.abs(var_x)==1) { moveIsDoable = (nextCase.getCaseAvailable() == -piece.pieceColor)
					|| isOnPassantAllowed;}
			}
		
		if(piece instanceof Rook || piece instanceof King) {
			Piece targetPiece = findPiece(nextCase.getCaseOccuped());
			if(targetPiece!=null) {
//				System.out.print(isPathClear(piece, nextCase));
				
				if(targetPiece.pieceColor == piece.pieceColor
						&& (targetPiece instanceof Rook || targetPiece instanceof King)
						&& isPathClear(piece, nextCase)
						&& !piece.hasAlreadyMoved 
						&& !targetPiece.hasAlreadyMoved) {
					if(isRoqueAllowed(piece, targetPiece)) {
						doRoque(piece, targetPiece);
					}
				}
			}
		}
		
//		System.out.print("Is this move doable? " + moveIsDoable +"\n");
		return moveIsDoable;	
		}
	
	
	/// Displaying
	
//	public void showBoard() {
//		GridBagConstraints c = new GridBagConstraints();
//		for(Case el : board) {
//			c.gridx = el.posIndex[0];
//		    c.gridy = el.posIndex[1];
//		    this.add((JButton) el,c);
//		}	
//	}
	
	
	
	
	private void doRoque(Piece piece, Piece targetPiece) {
		// TODO Auto-generated method stub
		Piece king = (piece instanceof King) ? piece : targetPiece; 
		Piece rook = (piece instanceof Rook) ? piece : targetPiece; System.out.print("Rook name: " + rook.pieceName + "\n");
		int var_x = rook.piecePosition[0] - king.piecePosition[0]; System.out.print("var_x = " + var_x + "\n");
		int row = king.piecePosition[1];
		int king_col;
		int rook_col;
		String code;
		
		if(var_x > 0) {
			king_col = 6; //column g
			rook_col = 5;
			code = "O-O";
		} else {
			king_col = 2;
			rook_col = 3;
			code = "O-O-O";
		}
		int[] index_rook = {rook_col, row}; 
		int[] index_king = {king_col, row}; System.out.print("index_king: " + index_king[0] + ", " + index_king[1] +"\n");
		king.setNewPos(index_king);
		rook.setNewPos(index_rook);
		
		Turn t = Game.activeTurn;
		if(t.color == 1) {t.moves+=code+" "; t.blackToMouv();}
			else {t.moves+=code; t.endTurn();}
		Display.refreshBoard();
	}


	boolean isRoqueAllowed(Piece piece, Piece targetKing) {
		// TODO Auto-generated method stub
		boolean result = true;
		ArrayList<Piece> listPieces = (piece.pieceColor == -1) ? whitePieces : blackPieces;
		
		int var_x = targetKing.piecePosition[0] - piece.piecePosition[0];
		int ref = piece.piecePosition[0];
		Case caseTemp = null;
		int[] indexTemp = {0,0}; indexTemp[0] = piece.piecePosition[0];
		
		for(int i = 0; i <= Math.abs(var_x); i++ ) {
			caseTemp = findCase(indexTemp);
			for(Piece p : listPieces) {
				if(isPathClear(p, caseTemp)) {
					result = false;
					break;
				}
			}
			indexTemp[0] += Integer.signum(var_x);
		}
		
		System.out.print("Is roque allowed ? " + !result + "\n");
		if(!result) {isRoqueAllowed = true;}
		return !result;
	}


	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    Piece piece; 
//	    setBackground(Color.darkGray );
	    g.setColor(darkCaseColor); g.fillRect(0, 0, 10*width, 10*height);
	     
	    for(Case el : board) {
		    el.drawCase(g);
			piece = findPiece(el.getCaseOccuped());
			if(piece != null) {piece.drawPiece(g);}
					
	    }  
	    
	    g.setColor(Color.white); g.drawRect(width, height,(int) (8*width),(int) (8*height));
	    //g.setClip(0, 0, 10*width+1, 10*height+1);
	    
	    
	}
		
	 
}// END CLASS	
	
		


