
package spm.GameOfLifeProject;

import cl.niclabs.skandium.muscles.Execute;

public class Worker implements Execute<Interval, Interval> {
	public static int MATRIX_SIZE;
	/**
	 * Method used to execute the application on each worker provided
	 *
	 * @param arg0 accepts the list of intervals
	 * @return
	 * @throws Exception
	 */
	
	
	@Override
	public Interval execute(Interval arg0) throws Exception {
		 Matrix mat = new Matrix(MATRIX_SIZE);
		 return  mat.neighbors2(arg0);
	}

}