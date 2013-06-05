package balancedms.io;

/**
 * Spezieller Iterator zum liefern der Runs eines Tapes.
 */
public interface TapeIterator {
	
	/*
	 * Liefert den aktuellen Run eines Tapes und inkrementiert
	 */
	public Run next();
	
	/*
	 * 'true', falls next noch einen Run liefert, 'false' sonst
	 */
	public boolean hasNext();
	
	/*
	 * liefert den Run am aktuellen Index ohne zu inkrementieren
	 */
	public Run read();
}
