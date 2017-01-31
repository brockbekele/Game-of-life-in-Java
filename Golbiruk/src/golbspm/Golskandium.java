package golbspm;


import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.Stream;
import cl.niclabs.skandium.skeletons.Map;
import cl.niclabs.skandium.skeletons.Skeleton;
import cl.niclabs.skandium.skeletons.While;
import golbspm.Interval;

import java.util.Random;

import java.util.concurrent.Future;

public class Golskandium {

	static Random random = new Random();
	static int x;
	public static int MATRIX_SIZE;
	public static long seed ;
	public static int seedBool;
	public static int ngen;
	int THREADS;
	
	public Golskandium(int tHREADS, int iter,int bOARD) {
		THREADS = tHREADS;
		MATRIX_SIZE = bOARD;
		ngen = iter;
	}

	public void execute()throws Exception {
		System.out.println("**Computing Game of Life  Threads = " + THREADS + ", Matrix Size = "
				+ MATRIX_SIZE + "X" + MATRIX_SIZE +  " no generation " + ngen );

		if(seedBool==1){
           generateseed(seed);
           
        } else
         	generate();
		//pick();
		Skandium skandium = new Skandium( THREADS);
		Skeleton<Interval, Interval> map = new Map<Interval, Interval>(new Splitter(THREADS), new Worker(),
				new Merger(ngen));// new Merger(MATRIX_SIZE)

		Skeleton<Interval, Interval> whileSkeleton = new While<Interval>(map, new Cond());
		Stream<Interval, Interval> stream = skandium.newStream(whileSkeleton);
		
     


		
		Interval input = new Interval(1, MATRIX_SIZE);
		//Interval result = null;
		
		    Future<Interval> future = stream.input(input);
			long init = System.currentTimeMillis();
			try {
				input = future.get();
				
				//Used to print the last updated Mesh

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(
					"**Computing Time in " + (System.currentTimeMillis() - init) + "[ms]");
			//Matrix.print(result);
			
		skandium.shutdown();
		
		

		System.exit(1);
	}

	/**
	 * Method used to generate the initial matrix 
	 * 
	 * @param n
	 *            equals to the matrix size
	 * @return
	 */
	public final static int DEAD = 0;
	public final static int ALIVE = 1;
	
	public static void generate() {
		Matrix.matrix = new int[MATRIX_SIZE + 2][MATRIX_SIZE + 2];
		Random r = new Random();

		try {

			for (int ii = 0; ii < MATRIX_SIZE; ii++) {
				for (int jj = 0; jj < MATRIX_SIZE; jj++) {
					byte number = (byte) (Math.abs(r.nextInt()) % 2);
					if (number == 1) {
						Matrix.matrix[ii][jj] = ALIVE;
					} else {
						Matrix.matrix[ii][jj] = DEAD;
					}
				}
			}

			// Use for the ghost column
			for (int i = 1; i < Matrix.matrix.length; i++) {
				Matrix.matrix[i][Matrix.matrix.length - 1] = Matrix.matrix[i][1]; // rightmost
																					// ghost
																					// column
				Matrix.matrix[i][0] = Matrix.matrix[i][Matrix.matrix.length - 2]; // leftmost
																					// ghost
																					// column
				// System.out.println(matrix[i][n + 1]);
			}
			// Used for the ghost row

			for (int j = 1; j < Matrix.matrix.length; j++) {
				Matrix.matrix[Matrix.matrix.length - 1][j] = Matrix.matrix[1][j];
				Matrix.matrix[0][j] = Matrix.matrix[Matrix.matrix.length - 2][j];
			}
			// Used for the ghost corner points

			Matrix.matrix[0][0] = Matrix.matrix[Matrix.matrix.length - 2][Matrix.matrix.length - 2];
			Matrix.matrix[Matrix.matrix.length - 1][0] = Matrix.matrix[1][Matrix.matrix.length - 2];
			Matrix.matrix[0][Matrix.matrix.length - 1] = Matrix.matrix[Matrix.matrix.length - 2][1];
			Matrix.matrix[Matrix.matrix.length - 1][Matrix.matrix.length - 1] = Matrix.matrix[1][1];

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
		
		
		public static void generateseed(long seed) {
			Matrix.matrix = new int[MATRIX_SIZE + 2][MATRIX_SIZE + 2];
			Random r = new Random(seed);

			try {

				for (int ii = 0; ii < MATRIX_SIZE; ii++) {
					for (int jj = 0; jj < MATRIX_SIZE; jj++) {
						byte number = (byte) (Math.abs(r.nextInt()) % 2);
						if (number == 1) {
							Matrix.matrix[ii][jj] = ALIVE;
						} else {
							Matrix.matrix[ii][jj] = DEAD;
						}
					}
				}

				// Use for the ghost column
				for (int i = 1; i < Matrix.matrix.length; i++) {
					Matrix.matrix[i][Matrix.matrix.length - 1] = Matrix.matrix[i][1]; // rightmost
																						// ghost
																						// column
					Matrix.matrix[i][0] = Matrix.matrix[i][Matrix.matrix.length - 2]; // leftmost
																						// ghost
																						// column
					// System.out.println(matrix[i][n + 1]);
				}
				// Used for the ghost row

				for (int j = 1; j < Matrix.matrix.length; j++) {
					Matrix.matrix[Matrix.matrix.length - 1][j] = Matrix.matrix[1][j];
					Matrix.matrix[0][j] = Matrix.matrix[Matrix.matrix.length - 2][j];
				}
				// Used for the ghost corner points

				Matrix.matrix[0][0] = Matrix.matrix[Matrix.matrix.length - 2][Matrix.matrix.length - 2];
				Matrix.matrix[Matrix.matrix.length - 1][0] = Matrix.matrix[1][Matrix.matrix.length - 2];
				Matrix.matrix[0][Matrix.matrix.length - 1] = Matrix.matrix[Matrix.matrix.length - 2][1];
				Matrix.matrix[Matrix.matrix.length - 1][Matrix.matrix.length - 1] = Matrix.matrix[1][1];

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		
		

	}
		
		
		
}