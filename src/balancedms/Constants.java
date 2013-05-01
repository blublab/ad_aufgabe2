package balancedms;

/**
 * Kontrollparameter fuer Balanced 4-way Mergesort
 * 
 *	Nicht instanziierbar
 */

final class Constants {
	private Constants(){};
	
	static final int FOLGENLAENGE		= 1000000;	// Anzahl der zu sortierenden Schluessel
	static final int MEMSORT_BUFFER		= 1024;		// Anzahl der Schluessel die gleichzeitig 
													// in den den Speicher geladen werden
	static final int SCHLUESSELGROESSE	= 4;		// Lanege eines Schluessel in Bytes
}