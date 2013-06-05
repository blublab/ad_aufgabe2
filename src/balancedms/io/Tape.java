package balancedms.io;

public interface Tape {
	/**
	 * Liefert einen Iterator ueber die Runs eines Tapes.
	 */
	public TapeIterator iterator();
	
	/**
	 * Loescht alle Runs auf einem Tape
	 */
	public void reset();
}