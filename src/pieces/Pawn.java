package pieces;

public class Pawn extends Piece {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Pawn(String name, int color, int i, int j){
		pieceName = name;
		pieceColor = color;
		isAlive = true;
		piecePosition[0] = i;
		piecePosition[1] = j;
		findCase(piecePosition).setCaseOccuped(name);
		findCase(piecePosition).setCaseAvailable(color);
		}
	
	
	int[][] listMovementAllowed = {
			{-1, 1},
			{0, 1},
			{1, 1},
			{0, 2}
			}; // 4 max movements allowed
	
	public int[][] getListMovement(){return listMovementAllowed;}
	
	
	}

