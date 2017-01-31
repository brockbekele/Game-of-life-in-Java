package golbspm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author biruk
 */
public class GOL {

    
    /**
     * @param args the command line arguments
     * 
     */
	int matrix_size;
	public static long seed ;
	public static int seedBool;
	public GOL(int matrix_size){
		this.matrix_size = matrix_size;
		
	}
	 
	public void execute() {
    	
        Golboard m = new Golboard(matrix_size);
     {
           if(seedBool==1){
               m.seedintialize(seed);
           } else
            	 m.Randomintialize();
        }
        long time = System.currentTimeMillis();
        m.play();
        

        long endTime = System.currentTimeMillis();
       
        
        // TODO code application logic here
      System.out.println("Done with Matrix size: " + m.matrix_size + " x " + m.matrix_size + " with Generation: " + Golboard.ngen  );
      
      System.out.println ("Computation Time : " + (endTime - time) + "[ms]");
    }

    
}