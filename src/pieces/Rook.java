package pieces;

public class Rook extends Piece{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Rook(String name, int color, int i, int j) {
		pieceName = name;
		pieceColor = color;
		isAlive = true;
		piecePosition[0] = i;
		piecePosition[1] = j;
		findCase(piecePosition).setCaseOccuped(name);
		findCase(piecePosition).setCaseAvailable(color);
		}
	int[][] listMovementAllowed = {
		{-7,0},{-6,0},{-5,0},{-4,0},{-3,0},{-2,0},{-1,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},
	    {0,-7},{0,-6},{0,-5},{0,-4},{0,-3},{0,-2},{0,-1},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}
		};
	public int[][] getListMovement(){return listMovementAllowed;}	
		
		
		
		
}