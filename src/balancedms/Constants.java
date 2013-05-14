package balancedms;

/**
 * Kontrollparameter fuer Balanced 4-way Mergesort
 * 
 *	Nicht instanziierbar
 */
final class Constants {
	private Constants(){};
	
	static final int FOLGENLAENGE		= 128;		// Anzahl der zu sortierenden Schluessel
	static final int MEMSORT_BUFFER		= 64;		// Anzahl der Schluessel die gleichzeitig 
													// in den Speicher geladen werden
	static final int SCHLUESSELGROESSE	= 4;		// Lanege eines Schluessels in Bytes
	static final int READ_BUFFER		= MEMSORT_BUFFER / 4;
	static final int WRITE_BUFFER		= MEMSORT_BUFFER / 2;
}