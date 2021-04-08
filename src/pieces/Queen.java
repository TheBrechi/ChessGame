package pieces;

public class Queen extends Piece{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Queen(String name, int color, int i, int j) {
		pieceName = name;
		pieceColor = color;
		isAlive = true;
		piecePosition[0] = i;
		piecePosition[1] = j;
		findCase(piecePosition).setCaseOccuped(name);
		findCase(piecePosition).setCaseAvailable(color);
		}
	int[][] listMovementAllowed = {
				{-7,-7},{-6,-6},{-5,-5},{-4,-4},{-3,-3},{-2,-2},{-1,-1},
				{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},
				{-7,7},{-6,6},{-5,5},{-4,4},{-3,3},{-2,2},{-1,1},
				{7,-7},{6,-6},{5,-5},{4,-4},{3,-3},{2,-2},{1,-1},
				{-7,0},{-6,0},{-5,0},{-4,0},{-3,0},{-2,0},{-1,0},{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},
			    {0,-7},{0,-6},{0,-5},{0,-4},{0,-3},{0,-2},{0,-1},{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7}
				};
	public int[][] getListMovement(){return listMovementAllowed;}
}
