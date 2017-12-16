package spm.GameOfLifeProject;

/**
 * The Matrix class  which represents the instruction and World of this Game of Life
 *https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * @author biruk
 *
 */
public class Matrix {

    public static int[][] matrix;
    public static int[][] next_matrix;
    public static int MATRIX_SIZE;
    /**
     * Constructor method to copy the current matrix
     * @param arg used as temp to get and set matrix value 
     */
    public Matrix(int MATRIX_SIZE) {
            
    }
    
    public static void swap_matrices() {
    	int[][] temp = matrix;
		matrix = tempNext;
		tempNext = temp;
	}
    public final static int DEAD = 0;
	public final static int ALIVE = 1;
	
	/** A function that counts the alive neighbours of a cell
	 * 
	 * @param i row identifier
	 * @param j column identifier
	 * @return the number of alive cells around (i,j)
	 */
   private int countNoAliveNeighbours(int i, int j) {
		int result = 0;
		int prevRow = i - 1, succRow = i + 1, prevColumn = j - 1, succColumn = j + 1;

		if (i == 0)
			prevRow = GolSkandium.MATRIX_SIZE;
		if (j == 0)
			prevColumn = GolSkandium.MATRIX_SIZE;
		if (j == GolSkandium.MATRIX_SIZE - 1)
			succColumn = 0;
		if (i == GolSkandium.MATRIX_SIZE - 1)
			succRow = 0;
		
		result = Matrix.matrix[prevRow][prevColumn] + Matrix.matrix[prevRow][j] + Matrix.matrix[prevRow][succColumn]
				+ Matrix.matrix[i][prevColumn] + Matrix.matrix[i][succColumn] + Matrix.matrix[succRow][prevColumn]
				+ Matrix.matrix[succRow][j] + Matrix.matrix[succRow][succColumn];

		return result;
	}

	static int[][] tempNext = new int[GolSkandium.MATRIX_SIZE + 2][GolSkandium.MATRIX_SIZE + 2];
    /**
     * 
     * @param interval indicate the start of the row and no of rows
     * @return matrix  the element in position (i,j) in the Matrix board
     */
	public Interval neighbors2(Interval interval) {
		int m = interval.a;
		int n = interval.b;
		for (int i = m; i <= n; i++) {
			for (int j = 1; j <= GolSkandium.MATRIX_SIZE; j++) {
				int aliveNeighbours = countNoAliveNeighbours(i, j);
				if (Matrix.matrix[i][j] == ALIVE) {
					switch (aliveNeighbours) {
					case 2:
						tempNext[i][j] = ALIVE;
						break;
					case 3:
						tempNext[i][j] = ALIVE;
						break;
					default:
						tempNext[i][j] = DEAD;
						break;
					}
				} else {
					switch (aliveNeighbours) {
					case 3:
						tempNext[i][j] = ALIVE;
						break;
					default:
						tempNext[i][j] = DEAD;
						break;
					}
				}
			}
		}


		return interval;

	}
    /**
     * To print the the evoultion 
     * @param interval used to get the start and end of the board 
     */
    public static void print(Interval interval) {                
            int i, j;
            /* show new board */
            System.out.println("-----------------------");
            for (i = interval.a-1; i <= interval.b+1; i++) {
                System.out.print(" |");
                for (j = 0; j <= GolSkandium.MATRIX_SIZE+1; j++) {
                    System.out.print(" " + Matrix.matrix[i][j]);
                }
                System.out.println(" |");
            }        
    }
}