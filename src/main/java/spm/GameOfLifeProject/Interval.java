package spm.GameOfLifeProject;

public class Interval {
	public  int a;
        public  int b;
    /**
     * Constructor method used to set the start and no of the row that one
     * worker can update
     *
     * @param a used to indicate the start of the row
     * @param b used to indicate the no  of the row */
    
    public Interval(int a, int b){
	    this.a = a;
            this.b = b;
    }
    
    // @Override
    public String toString() {
	    return "("+a+", "+b+")";
    }
}
