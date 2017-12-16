
package spm.GameOfLifeProject;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import cl.niclabs.skandium.muscles.Condition;

/**
 *
 * @author biruk
 */
public class Cond implements Condition<Interval> {
    
    @Override
    public boolean condition(Interval p) throws Exception {
        
    		if (Merger.maxGene >=0) {
    			return true;
    		} else {
    			return false;
    			}
    }
}