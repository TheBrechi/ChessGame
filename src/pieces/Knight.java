package pieces;

public class Knight extends Piece{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Knight(String name, int color, int i, int j) {
			pieceName = name;
			pieceColor = color;
			isAlive = true;
			piecePosition[0] = i;
			piecePosition[1] = j;
			findCase(piecePosition).setCaseOccuped(name);
			findCase(piecePosition).setCaseAvailable(color);
			}
	int[][] listMovementAllowed = {
			{1,2},
			{2,1},
			{-1,2},
			{2,-1},
			{-2,-1},
			{-1,-2},
			{-2,1},
			{1,-2}
	};
	public int[][] getListMovement(){return listMovementAllowed;}
}
