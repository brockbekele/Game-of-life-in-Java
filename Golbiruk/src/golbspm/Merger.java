package golbspm;


import cl.niclabs.skandium.muscles.Merge;

public class Merger implements Merge<Interval, Interval> {

    /**
     * The Merge method is used to merge the list intervals from each worker
     *
     * @param arg0 list intervals from each worker
     * @return the interval from start to end
     * @throws Exception
     */
    //Used for the maximim number of generation
    public static int maxGene;

    /**
     * Method used to initialize the maximum number of generation
     *
     * @param maxGene
     */
    public Merger(int maxGene) {
        Merger.maxGene = maxGene;
    }
    @Override
    public Interval merge(Interval[] arg0) throws Exception {
        int small = 100000000;
        int large = 0;

        for (int i = 0; i < arg0.length; i++) {
        	for (int ii = 1 ;ii<=maxGene;ii++){
            if (arg0[i].a< small) {
                small = arg0[i].a;
            }
            if (arg0[i].b> large) {
                large = arg0[i].b;
                }
        } 
        	
        }
      
        Matrix.swap_matrices();
        maxGene--;
        
        

       return new Interval(small, large);
    
}
}