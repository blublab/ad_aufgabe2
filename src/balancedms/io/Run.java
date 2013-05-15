/**
 * 
 */
package balancedms.io;

import java.io.IOException;
import java.util.Iterator;

/**
 * Runs sind die geordneten Elemente von Tapes und verwalten die 
 * Schluessel (Integers)
 *
 */
public interface Run {
	void add(int[] seq) throws IOException;
	public Iterator<Integer> iterator();
	public void print();
}