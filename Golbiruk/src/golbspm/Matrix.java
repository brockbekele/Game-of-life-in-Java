package golbspm;


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
	
    
    private int countNoAliveNeighbours(int i, int j) {
		int result = 0;
		int prevRow = i - 1, succRow = i + 1, prevColumn = j - 1, succColumn = j + 1;

		if (i == 0)
			prevRow = Golskandium.MATRIX_SIZE;
		if (j == 0)
			prevColumn = Golskandium.MATRIX_SIZE;
		if (j == Golskandium.MATRIX_SIZE - 1)
			succColumn = 0;
		if (i == Golskandium.MATRIX_SIZE - 1)
			succRow = 0;
		
		result = Matrix.matrix[prevRow][prevColumn] + Matrix.matrix[prevRow][j] + Matrix.matrix[prevRow][succColumn]
				+ Matrix.matrix[i][prevColumn] + Matrix.matrix[i][succColumn] + Matrix.matrix[succRow][prevColumn]
				+ Matrix.matrix[succRow][j] + Matrix.matrix[succRow][succColumn];

		return result;
	}

	static int[][] tempNext = new int[Golskandium.MATRIX_SIZE + 2][Golskandium.MATRIX_SIZE + 2];

	public Interval neighbors2(Interval interval) {

		int m = interval.a;
		int n = interval.b;
		// tempNext=Matrix.matrix;
		for (int i = m; i <= n; i++) {
			for (int j = 1; j <= Golskandium.MATRIX_SIZE; j++) {
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

		
		//Matrix.next_matrix = tempNext;

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
                for (j = 0; j <= Golskandium.MATRIX_SIZE+1; j++) {
                    System.out.print(" " + Matrix.matrix[i][j]);
                }
                System.out.println(" |");
            }        
    }
}