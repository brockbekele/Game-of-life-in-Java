
package golbspm;

import cl.niclabs.skandium.muscles.Execute;

public class Worker implements Execute<Interval, Interval> {

	/**
	 * Method used to execute the application on each worker provided
	 *
	 * @param arg0
	 *            accepts the list of intervals
	 * @return
	 * @throws Exception
	 */
	public static int MATRIX_SIZE;
	

	@Override
	public Interval execute(Interval arg0) throws Exception {
		//System.out.println("Executing..." + arg0.a + " - " + arg0.b);
		//System.out.println(
			//	"********************************************************************************************");
		// if(arg0.start==1)
		// print(arg0);
		//System.out.println(
				//"********************************************************************************************");
		 Matrix mat = new Matrix(MATRIX_SIZE);
		return  mat.neighbors2(arg0);
	}


	

	

}