package golbspm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.CyclicBarrier;




/**
 *
 * @author biruk
 */
public class Workerthread implements Runnable {
    
    Golboard board;
    int start;
    int ngen;
    int matrix_size;
    private final CyclicBarrier barrier;

    public Workerthread(Golboard b, int startRows, int size, int gen,CyclicBarrier barrier)
    {
    	board = b; 
        start = startRows;
        matrix_size = size;
        ngen = gen;
       
       this.barrier = barrier;
    }

    @Override
    public void run() {
    	
        for (int i = 0; i <=ngen; i++) {  
        	
            board.new_generation(start,matrix_size);  
            
            
        	
           
            try {
            	
                barrier.await();
       
            } catch (InterruptedException | BrokenBarrierException ex) {
                
          
        }
    }
}
}
