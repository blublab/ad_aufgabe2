package balancedms.io;

/*
 * Iteriert ueber die 'int's eines Run
 */
public interface RunIterator {
	
	/*
	 * liefert den aktuellen int-Value und inkrementiert
	 */
	public int next();
	
	/*
	 * 
	 */
	public boolean hasNext();
	
	/*
	 * liefert aktuellen int-Value ohne zu inkrementieren
	 */
	public int read();
}
