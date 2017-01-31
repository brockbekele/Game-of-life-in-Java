

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package golbspm;

import java.util.Random;



/**
 *
 * @author Biruk
 */
public class Golboard{

    public final static int DEAD = 0;
    public final static int ALIVE = 1;
    /* the two NXN matrices that are used to store the game life board*/
  public static  int[][] curr_grid;
    public int[][] next_grid;
	
      int matrix_size;
	public static int ngen ;
	long seed;
    
    /* allocate memory for the matrices  constructor*/
    public Golboard(int matrix_size) {
    this.matrix_size = matrix_size;
    	

       
   
        curr_grid = new int[matrix_size + 2][matrix_size + 2];
        next_grid = new int[matrix_size + 2][matrix_size + 2];
        for (int i = 0; i < matrix_size + 2; i++) {
            curr_grid[i] = new int[matrix_size + 2];
            next_grid[i] = new int[matrix_size + 2];
        }
    }
    
/*random generation of initial values indepedent of inputs */
  
  public void Randomintialize() {
      Random r = new Random();
      for (int i = 0; i < matrix_size; i++) {
          for (int j = 0; j < matrix_size; j++) {
              byte number = (byte) (Math.abs(r.nextInt()) % 2);
              if (number == 1) {
                  curr_grid[i][j] = ALIVE;
              } else {
                  curr_grid[i][j] = DEAD;
              }
          }
      }
      /* Initialize the boundaries of the matrix(the outer boundaries) */
      for (int h = 1; h <= matrix_size; h++) {
          curr_grid[h][0] = curr_grid[h][matrix_size];//left
          curr_grid[h][matrix_size + 1] = curr_grid[h][1];//right
          curr_grid[matrix_size + 1][h] = curr_grid[1][h];//bottom
          curr_grid[0][h] = curr_grid[matrix_size][h];//top
      }
      /* invisible corners */
      curr_grid[0][0] = curr_grid[matrix_size][matrix_size];//top-left
      curr_grid[0][matrix_size + 1] = curr_grid[matrix_size][1];//top-right
      curr_grid[matrix_size + 1][0] = curr_grid[1][matrix_size];//Bottom-left
      curr_grid[matrix_size + 1][matrix_size + 1] = curr_grid[1][1];//Bottom-right   
  }
  
  /*** random generation dependent on inputs seed ***/
  public void seedintialize(long seed) {
      Random r = new Random(seed);
      for (int i = 0; i < matrix_size; i++) {
          for (int j = 0; j < matrix_size; j++) {
              byte number = (byte) (Math.abs(r.nextInt()) % 2);
              if (number == 1) {
                  curr_grid[i][j] = ALIVE;
              } else {
                  curr_grid[i][j] = DEAD;
              }
          }
      }
      /* Initialize the boundaries of the matrix(the outer boundaries) */
      for (int h = 1; h <= matrix_size; h++) {
          curr_grid[h][0] = curr_grid[h][matrix_size];//left
          curr_grid[h][matrix_size + 1] = curr_grid[h][1];//right
          curr_grid[matrix_size + 1][h] = curr_grid[1][h];//bottom
          curr_grid[0][h] = curr_grid[matrix_size][h];//top
      }
      /* invisible corners */
      curr_grid[0][0] = curr_grid[matrix_size][matrix_size];//top-left
      curr_grid[0][matrix_size + 1] = curr_grid[matrix_size][1];//top-right
      curr_grid[matrix_size + 1][0] = curr_grid[1][matrix_size];//Bottom-left
      curr_grid[matrix_size + 1][matrix_size + 1] = curr_grid[1][1];//Bottom-right   
  }

  public void makegenerate(int n, int startRow, int nRows) {
      for (int i = 1; i <=n; i++) {
          new_generation(1, nRows);
          
     }

  }
  /**** Calculation of the neighbors ****/
  
   public  void new_generation(int startRow, int nRows) {
        int i, j, sum;
        for (i = startRow; i < startRow+ nRows; i++) {
            for (j = 1; j <= matrix_size; j++) {
                /* find out the (eight) neighbors of the current cell */
                sum = curr_grid[i - 1][j - 1] + curr_grid[i - 1][j] + curr_grid[i - 1][j + 1]
                        + curr_grid[i][j - 1] + curr_grid[i][j + 1]
                        + curr_grid[i + 1][j - 1] + curr_grid[i + 1][j] + curr_grid[i + 1][j + 1];

                if (sum < 2 || sum > 3) {
                	
                  next_grid[i][j] = DEAD;
                   
                } else if (sum == 3) {
                	
                    next_grid[i][j] = ALIVE;
                   
                } else {

                    next_grid[i][j] = curr_grid[i][j];
                	
                    
                }
            }
        }
        
        //Initialize the boundaries of the matrix(the outer boundaries) 
        for (int h = 1; h <= matrix_size; h++) {
            next_grid[h][0] = next_grid[h][matrix_size];//left
            next_grid[h][matrix_size + 1] = next_grid[h][1];//right
            next_grid[matrix_size + 1][h] = next_grid[1][h];//bottom
            next_grid[0][h] = next_grid[matrix_size][h];//top
        }
         //invisible corners 
        next_grid[0][0] = next_grid[matrix_size][matrix_size];//top-left
        next_grid[0][matrix_size + 1] = next_grid[matrix_size][1];//top-right
        next_grid[matrix_size + 1][0] = next_grid[1][matrix_size];//Bottom-left
        next_grid[matrix_size + 1][matrix_size + 1] = next_grid[1][1];//Bottom-right   
        
        

        
        
    }
   /*A function that swaps the read board with the write board, to be called
   at the end of each iteration*/
   public void swap_matrices() {
        int[][] temp = curr_grid;
        curr_grid = next_grid;
        next_grid = temp;
    }

  /* for each iteration to display the generated matrix */
   public void play() {
    	int startRow = 1, nrows= matrix_size;
       

        for (int gen = 0; gen <= ngen; gen++) {
        	 
            new_generation(startRow,nrows);
	        
        //   display();
             swap_matrices();
            
           
           
            
        }
        
        
    }
   
   /*** for the parallel one  to call the swap matrices and display***/
   
   public void swapplDsil(){
	   
	  //  display();
	  swap_matrices();
	   
	   
	}

   void output_life_board() {
        int i, j;
        /* show new board */
        for (i = 0; i <=matrix_size + 1; i++) {
            System.out.print(" |");
            for (j = 0; j <= matrix_size + 1; j++) {
                System.out.print(" " + curr_grid[i][j]);
                
                
            }
            System.out.println(" |"); 
        }
    }
  
   
  /* Displays the current matrix */

     public  void display() {
        System.out.println("DONE GENERATION: ");
        System.out.println("-----------------------");
        output_life_board();
       
        

    }
    
    /* This method give an intervals by Splitting the  board matrix Row wise*/ 
    
    public Interval[] splitBoard(int THREADS){
  
        int step = matrix_size / (THREADS);
        int extra = matrix_size % THREADS;
        int start = 1;
        int chunk = 0;
        Interval[] bounds = new Interval[THREADS];

        for (int j = 0; j < THREADS; j++) {
            start = start + chunk;
            chunk = step + (extra-- > 0 ? 1:0);            
            bounds[j] = new Interval(start, chunk);
        }
        
        return bounds;
    }

	

}
