package pieces;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

class InputArea extends Board{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean state = false; 
//	public static boolean getInputState() {return state;}
	public static void resetInputState() {state = false; input="";txtArea.setText("");}
	
	public static String input = "";
//	public static String getInput() {return input;}
	static JTextArea txtArea = new JTextArea("");
	public static  JTextArea getJTextArea() {return txtArea;}
	
	InputArea(){
		txtArea.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e){
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	input = txtArea.getText();
		        	state = true;
//		        	System.out.println("\nEnter key pressed");
		        	txtArea.setText("");
		    		e.consume();
		    		}
		    }

		    public void keyTyped(KeyEvent e) {}

		    public void keyReleased(KeyEvent e) {}
			
		});
	}
	
}




