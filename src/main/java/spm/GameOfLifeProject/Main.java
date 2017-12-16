package spm.GameOfLifeProject;
/**
 * 
 * @author biruk
 *
 */

public class Main {
	/** 
	 * 
	 * @param args the command line arguments
	 * @throws Exception
	 */

	public static void main(String[] args) throws Exception {
		int THREADS;
		int BOARD ; 
		int Type;
		int ngen ;
		long seed ;
		int seedon;
		 
		if (args.length == 0) {
			errorMessage();
		}
		if (args[0] == null || args[1] == null) {
			errorMessage();
		}
		
		Type = Integer.parseInt(args[0]);
		BOARD = Integer.parseInt(args[1]);
		seedon= Integer.parseInt(args[2]);
	    seed = Integer.parseInt(args[3]);
		
		if (Type == 1  && seedon == 1){
			if (args.length != 5) {
				errorMessage();
			}

			ngen= Integer.parseInt(args[4]);
			GOL.seedBool = 1;
			Golboard.ngen = ngen;
			GOL gol = new GOL(BOARD);
			gol.execute();
		
		} else if ( Type ==1 && seedon == 0 ){
			
		    if (args.length != 4) {
				errorMessage();
			}
			ngen= Integer.parseInt(args[3]);
			GOL.seedBool = 0;
			Golboard.ngen = ngen;
		    GOL gol = new GOL(BOARD);
			gol.execute();
			
		} else if (Type == 2 && seedon == 1) {
			if (args.length != 6) {
				errorMessage();
			}
			ngen= Integer.parseInt(args[4]);
			THREADS = Integer.parseInt(args[5]);
		    GolThread.seedBool =1;
		    GolThread.seed = seed;
            GolThread multi = new GolThread(THREADS,ngen, BOARD);
			multi.execute();
			
		} else if (Type == 2 && seedon == 0) {
			if (args.length != 5) {
				errorMessage();
			}
			
			ngen= Integer.parseInt(args[3]);
			THREADS = Integer.parseInt(args[4]);
			GolThread.seedBool = 0;
			GolThread multi = new GolThread(THREADS,ngen, BOARD);
			multi.execute();
			
		} else if (Type == 3 && seedon == 1) {
			if (args.length != 6) {
				errorMessage();
			}
			ngen= Integer.parseInt(args[4]);
			THREADS = Integer.parseInt(args[5]);
			GolSkandium.seedBool =1;
			GolSkandium.seed = seed;
			GolSkandium golskan = new GolSkandium(THREADS,ngen, BOARD);
			golskan.execute();
			
		} else if (Type == 3 && seedon == 0) {
			ngen= Integer.parseInt(args[3]);
			THREADS = Integer.parseInt(args[4]);
			if (args.length != 5) {
				errorMessage();
			}
			GolSkandium.seedBool = 0;
			GolSkandium golskan = new GolSkandium(THREADS,ngen, BOARD);
			golskan.execute();
		}
	}
		
	private static void errorMessage() {
		System.out.println("Wrong Typing For Sequental Type Please type: Size of Board: Generation ");
		System.out.println("Wrong Typing For  parallel  Type Please type: Size of Board: Seedon/off,Generation,Threads ");
		System.exit(0);

	}
}