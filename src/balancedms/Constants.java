package balancedms;

/**
 * Kontrollparameter fuer Balanced 4-way Mergesort
 * 
 *	Nicht instanziierbar
 */
final class Constants {
	private Constants(){};
	
	static final int SCHLUESSELGROESSE	= 4;		// Lanege eines Schluessels in Bytes

	
	static final int FOLGENLAENGE		= 1000;		// Anzahl der zu sortierenden Schluessel
	static final int MEMSORT_BUFFER		= 1;		// Anzahl der Schluessel die gleichzeitig 
													// in den Speicher geladen werden
	static final int READ_BUFFER		= Math.max(1,MEMSORT_BUFFER/2);
	static final int WRITE_BUFFER		= Math.max(1,MEMSORT_BUFFER/2);
}