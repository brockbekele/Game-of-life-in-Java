package spm.GameOfLifeProject;

import cl.niclabs.skandium.muscles.Split;


public class Splitter implements Split<Interval, Interval> {
	private int threads;
	/**
	* Takes the number of threads or processors
	* @param threads 
	*/
    public Splitter(int threads) {
		this.threads = threads;
    }
	/**
	* Method split,takes the interval from 1 upto Matrix size and
	* splits the original matrix size by the number of processors and returns the list of intervals
	* @param arg0 the interval received as input from main class
	* @return returns the list of intervals
	* @throws Exception 
	*/
    @Override
    public Interval[] split(Interval arg0) throws Exception {
        int rowLength;
        int rowSize = GolSkandium.MATRIX_SIZE / threads;//to distribute number of rows to each processor(Thread) 
        int rem = GolSkandium.MATRIX_SIZE % threads;//keep the reminder after equal distribution of row
        Interval[] result = new Interval[threads];
		int rowStart = arg0.a;//1
        for (int i = 0; i < threads; i++) {
			rowLength = (i < rem) ? rowSize + 1 : rowSize;
            Interval interval = new Interval(rowStart, rowStart + (rowLength - 1));
            result[i] = interval;//holds only interval of rows no data, since the application runs on shared memory
            rowStart += rowLength;
        }
		return result;
    }
}