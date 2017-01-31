package golbspm;

public class Main {

	public static void main(String[] args) throws Exception {

		int THREADS;
		int BOARD ; 
		int Type;
		int ngen ;
		long seed ;
		int seedon;
		
		//
		// Type =1 for Sequential Type
		// Type =2 for Skandium Type
		// Type= 3 for Java Thread Type
		//  Seedon = 1 dependent on the input seed.
		//  Seedon = 0 Independent of input seed

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
		}
		else if ( Type ==1 && seedon == 0 ){
				
			ngen= Integer.parseInt(args[3]);
				if (args.length != 4) {
					errorMessage();
				}
			
			GOL.seedBool = 0;
			Golboard.ngen = ngen;
			

			GOL gol = new GOL(BOARD);
			gol.execute();
		
		
}
	
		 else if (Type == 2 && seedon == 1) {
				if (args.length != 6) {
					errorMessage();
				}
				ngen= Integer.parseInt(args[4]);
				THREADS = Integer.parseInt(args[5]);
		
				 GolThread.seedBool =1;
				
			    GolThread.seed = seed;

			 GolThread multi = new GolThread(THREADS,ngen, BOARD);
				
			
				multi.execute();
		 }
		 else if (Type == 2 && seedon == 0) {
			 ngen= Integer.parseInt(args[3]);
			 THREADS = Integer.parseInt(args[4]);
				 if (args.length != 5) {
						errorMessage();
					}
				
				GolThread.seedBool = 0;
				
				

			 GolThread multi = new GolThread(THREADS,ngen, BOARD);
				
			
				multi.execute();
		 }
		
		
		 else if (Type == 3 && seedon == 1) {
				if (args.length != 6) {
					errorMessage();
				}
				ngen= Integer.parseInt(args[4]);
				THREADS = Integer.parseInt(args[5]);
				 Golskandium.seedBool =1;
				
			    Golskandium.seed = seed;

			 Golskandium golskan = new Golskandium(THREADS,ngen, BOARD);
				
			
				golskan.execute();
		 }
		 else if (Type == 3 && seedon == 0) {
			 ngen= Integer.parseInt(args[3]);
			 THREADS = Integer.parseInt(args[4]);
				 if (args.length != 5) {
						errorMessage();
					}
				
				Golskandium.seedBool = 0;
				
				

			 Golskandium golskan = new Golskandium(THREADS,ngen, BOARD);
				
			
				golskan.execute();
		 }
		 }
		
		
        

			
			
	
		

	private static void errorMessage() {
		System.out.println("Wrong Typing For Sequental Type Please type: Size of Board: Generation ");
		System.out.println("Wrong Typing For  parallel  Type Please type: Size of Board: Seedon/off,Generation,Threads ");
		
		System.exit(0);

	}
}