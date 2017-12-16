package spm.GameOfLifeProject;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;



import java.util.concurrent.CyclicBarrier;

/** The GolThread class represents the multithreaded version of Game of Life
 * 
 * @author biruk
 *
 */
public class GolThread {
	int THREADS; 
	int matrix_size;
	public static int ngen;
	public static long seed ;
        public static int seedBool;
	
	public GolThread (int tHREADS, int iter,int bOARD) {
		THREADS = tHREADS;
	        ngen =iter;
		matrix_size = bOARD;
	}

	public void execute() throws Exception {
		Golboard board = new Golboard(matrix_size);
		Interval[] bounds = board.splitBoard(THREADS);
		if(seedBool== 1){
			board.seedintialize(seed);
		} else {
         	board.Randomintialize();
		} 
		 
		final CyclicBarrier barrier = new CyclicBarrier(THREADS, board::swapplDsil);
		ExecutorService threadpool = Executors.newFixedThreadPool(THREADS);
		long init = System.currentTimeMillis();
		
		for (int j = 0; j < THREADS; j++) {
			threadpool.execute(new WorkerThread(board,bounds[j].a,bounds[j].b ,ngen, barrier));
		}
		threadpool.shutdown();
		threadpool.awaitTermination(10, TimeUnit.MINUTES);
		final long endTime = System.currentTimeMillis();
		System.out.println("Multi-Thread**Gol: matrix size : " + matrix_size + " Processor: "+ THREADS + " genertion: " + ngen );
		System .out.println("Computation time: " + (endTime - init) + "[ms]");
		System.exit(0);
	}

}
