package pieces;

import java.awt.*;
import java.awt.List;

import javax.swing.*;

import java.lang.*;
import java.util.*;

public class Main {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Game g = new Game();
		Thread t = new Thread(g);
		t.start();
		
		
//		Board b = new Board(); 
//		b.createBoard();
//		b.createPieces();
//		Turn turn = new Turn();
////		turn.blackToMouv();
////		
////		
//		int[] ind = {4, 4};
//		Case caseAimed = b.findTargetCase(370,270);
//		System.out.print(caseAimed.name);
		
//		Case c = new Case(4,4);
//		Piece p = new Pawn("oui", 1, 4, 4);
//		char[] listChar = "4".toCharArray();
//		
//		System.out.print(p.piecePosition[1] == Character.getNumericValue(listChar[0]) );
//		
		
		
//		String inp = "nc6";
//		int[] ind = {4, 4};
//		
//		Case c = b.findCase(ind); 
//		Piece p = b.findPiece("p5b"); 		
//		int[] v = b.vector(p, c);
//		String str = "bonjour";
//		char[] chr = str.toCharArray();
//		int n = chr.length;
//		char[] temp = new char[n];
//		
//		System.arraycopy(chr,1,temp,0, n-2);
//		
//		String out = "";
//		for(char elt : temp) {out+=elt;}
//		
//		chr = temp;
//		System.out.print(chr);
		
//		boolean bool = b.isInArray(p.getListMovement(), ind);//turn.mouvIsDoable(p, c);
//		System.out.print(bool);
//		System.out.println(c.posIndex[0]);
//		System.out.println(c.posIndex[1]);
//		System.out.println(p.piecePosition[0]);
//		System.out.println(p.piecePosition[1]);
		
//		Thread t = new Thread(turn);
//		t.start();
		
//		int[] ind = {0, 3};
//		String str = "a4";
//		Turn.inputTreatment(str);
		
		
//		int n = str.length();
//		System.out.print(n);
//		Case c = b.findCase(b.stringToIndex("nc6"));
//		System.out.print(c.name);
//		Piece p = b.findPiece("n", c, -1);
//		System.out.println(p.pieceName);
//		int[][] list = p.getListMovement();
//		boolean bool = b.mouvIsDoable(p, c); //b.isInArray(list, ind);
//		System.out.println(bool);
//		c.getCaseAvailable();
//		
//		
//		
//		var frame = new JFrame("A simple graphics program");
//		frame.setBackground(Color.darkGray );  
//		frame.setSize(800, 800);
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	       
//		
//		
////		JPanel pan = new JPanel(); pan.setSize(400, 400);
//		JLabel lab = new JLabel();
//		
//		Display d = new Display(b);
////		d.setLayout(new BorderLayout());
//		
////		pan.add(d);
////		lab.setIcon(kwIcone); pan.add(lab);
//		frame.add(d,BorderLayout.CENTER);
//		
//       
//        JButton button = new JButton("Button 1 (PAGE_START)");
//        frame.add(button, BorderLayout.PAGE_START);
// 
//        button = new JButton("Button 3 (LINE_START)");
//        frame.add(button, BorderLayout.LINE_START);
//
//        JTextArea txtArea = new JTextArea("This text is default text for text area.", 10, 20);
//        frame.add(txtArea, BorderLayout.PAGE_END);
//
//        button = new JButton("5 (LINE_END)");
//        frame.add(button, BorderLayout.LINE_END);
//        
////        frame.add(d,BorderLayout.CENTER); 
//		frame.setVisible(true);
        
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
}
