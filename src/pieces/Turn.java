package pieces;
import java.util.ArrayList;
//import java.util.*;

public class Turn extends Board{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean state;
	public int color; public int getColor() {return color;}; 
	public boolean moveIsDoable; 
	public String moves;
	public String input = "";
	boolean inputState;
	
	public Turn() {
		System.out.print("------------- New turn start -------------"+"\n");
		state = true;
		color = 1;
//		Piece kw = super.findPiece("kw");
//		System.out.print(kw.hasAlreadyMoved);
		//color.addActionListener(this);
		}
	
	public void blackToMouv() {
		color = -1;
		System.out.print("---------- Black to mouv -----------"+"\n");
		onPassantB = null;
	} 
	public void endTurn() {
		state = false;
		System.out.print("--------------- End turn ---------------"+"\n");
		onPassantW = null;
	}
	
	private boolean isContended(String[] str, char chr) {
		for(String elt : str) {
			if(elt.charAt(0) == chr) {return true;}
		}
		return false;
	}
	
	Case targetCase;
	Piece targetPiece;
	private void inputTreatment(String str) {
		//Reset
		targetCase = null;
		targetPiece = null;
		char[] listChar = str.toCharArray();
		int n = str.length();
		char[] temp = new char[n];
		
		String indexName = "" + listChar[n-2] + listChar[n-1];
		System.arraycopy(listChar,0,temp,0, n-2);
		listChar = temp; 
		targetCase = findCase(stringToIndex(indexName));
		
		String[] listPiece = {"n","b","r","q","k"};
		if(isContended(listPiece, listChar[0])) {
			findPiece(""+listChar[0], targetCase, this.color);
			System.arraycopy(listChar,1,temp,0, listChar.length-1);
			listChar = temp;
			
			
		} else {
			findPiece("p", targetCase, this.color);	
		}
		
		ArrayList<Piece> listTargetPiece = getTargetPieces();
		if(listTargetPiece.size()>1) {
			System.out.print("Number of potential target piece : " + listTargetPiece.size() + "\n");
			int s = listTargetPiece.size();
			for(int i=0;i<s;i++) {System.out.print(listTargetPiece.get(i).pieceName+"\n");}
			for(Piece p : listTargetPiece) {
				boolean bool = false;
				try {
					bool = (p.piecePosition[1]+1) == Integer.parseInt(""+listChar[0]);
				} catch (NumberFormatException e) { 
					bool = abscisses[p.piecePosition[0]].charAt(0) == listChar[0];
					} 
				if(bool){
					targetPiece = p;
				}
			}
		} else if(listTargetPiece.size()!=0){targetPiece = listTargetPiece.get(0);}
		// else, targetPiece remains null
	
//	System.out.print(targetCase.name);
//	System.out.print(targetPiece.pieceName);
	}
	
	public void pieceMovement(Piece piece, Case nextCase) {
		int[] oldPosition = piece.piecePosition;
		int[] var_xy = vector(piece, nextCase);
		int[] temp = piece.piecePosition; 
//		temp[0] += var_xy[0];
		
		Piece pieceToTake = findPiece(nextCase.getCaseOccuped());
		if (piece instanceof Pawn) {
			if(Math.abs(var_xy[1])==2){
				if(piece.pieceColor == 1) {setOnPassantB(piece);}
				else {setOnPassantB(piece);}
			}  else if(var_xy[0] != 0 && nextCase.getCaseAvailable()==0) {
				if(piece.pieceColor == 1) {pieceToTake = onPassantB;}
				else if(piece.pieceColor == -1) {pieceToTake = onPassantW;}
			}
		} 
//		else if(piece instanceof Rook && pieceToTake instanceof King
//				&& piece.pieceColor == pieceToTake.pieceColor) {
//			if(var_xy[0]>0) {
//				temp[0] += 1; 
//				pieceToTake.setNewPos(temp);
//			} else {
//				temp[0] -= 2;
//				pieceToTake.setNewPos(temp);
//			}
//			pieceToTake = null;	
//		}
		
		piece.setNewPos(nextCase.posIndex);
		boolean isPiecePinned = isCheck(-piece.pieceColor);
		if(isPiecePinned) {
			piece.setNewPos(oldPosition);
			pieceToTake.setNewPos(nextCase.posIndex);
			System.out.print("This piece is pinned"+"\n");}
		else {
//			boolean check = isCheck(color);
//			if(check) {super.isCheckmate();}
			if(pieceToTake!=null) {pieceToTake.dead();}
			if(color==1) {moves+=input+" "; this.blackToMouv();}
				 	else {moves+=input; this.endTurn();
			 	}	
			}
		
		Display.refreshBoard();
    	
	}
	
	
//	public void inputRead() {
//		input = Display.getInput();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		try {
//			input = br.readLine(); //to do when the color value changes
//			
//			//System.out.println(input);
//			
//	//			imputTreatment(input);
//	//			pieceMovement(targetPiece, targetCase);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
	
	public void runTurn(){
//		while(state) {
			inputState = true; //InputArea.state;
//			System.out.println(" "+inputState); //???
			if(inputState) {
//				System.out.print(true);
				input = InputArea.input;
//				System.out.println(input);
				if(input.length()>1) {inputTreatment(input);
					if(targetPiece!=null && targetCase!=null) {	
							moveIsDoable = moveIsDoable(targetPiece, targetCase);
//							System.out.println(moveIsDoable);
							
							if(moveIsDoable) {pieceMovement(targetPiece, targetCase);
							} else {System.out.print("Mouv is not doable");}
						} else {System.out.print("target piece or case might be null"+"\n");}
					input = "";
					InputArea.resetInputState();
					}
				
			}
//		}
		
		}
}//END CLASSE


















