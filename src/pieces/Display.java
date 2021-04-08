package pieces;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Display extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static Board b;
	ArrayList<Case> ListCase;
	static JFrame frame;
	
	Display(Board board){
		b =  board;}
	
	public void createAndshowGUI() {
		
		frame = new JFrame("A simple graphics program");
		frame.setBackground(Color.darkGray );  
		frame.setSize(800, 800);
//		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//		frame.setSize(d);
//	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container mainPan = frame.getContentPane();
//	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//		mainPan.setSize(d);
//	   
	    
	    JButton p1 = new JButton();
	    mainPan.add(p1);
 
//        p1 = new JButton();
//        frame.add(p1);
//        p1 = new JButton();
//        frame.add(p1);
	   
	      mainPan.setLayout(new BorderLayout());
        
	    mainPan.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JButton button = new JButton("Button 1");
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .5;
        c.weighty = .5;
        c.gridwidth = 3;
        mainPan.add(button, c);
        
        button = new JButton("Button 2");
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 2;
        mainPan.add(button, c);
        
        button = new JButton("Button 3");
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPan.add(button, c);
        
        
        //BOARD
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.BOTH;
//        b.showBoard();
        mainPan.add(b, c);//BorderLayout.CENTER);
        
        InputArea inputArea = new InputArea();
        JTextArea txtArea = inputArea.getJTextArea();
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        mainPan.add(txtArea, c);//BorderLayout.SOUTH);

       
        button = new JButton("Button 4");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        mainPan.add(button, c);
        
        
     
        
//        frame.add(d,BorderLayout.CENTER); 
        frame.pack();
		frame.setVisible(true);
        
	}
	
	//Case display parameters 
	
	
	public JFrame getFrame() {return frame;}	
	
	public static void refreshBoard() {
//		System.out.print("\n"+"Board should refresh");
//		frame.remove(b);
//		b.revalidate();
		b.repaint();
		}
}

