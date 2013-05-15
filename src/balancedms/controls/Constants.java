package balancedms.controls;

/**
 * Kontrollparameter fuer Balanced 4-way Mergesort
 * 
 *	Nicht instanziierbar
 */
public final class Constants {
	private Constants(){};
	
	public static final int FOLGENLAENGE		= 64;		// Anzahl der zu sortierenden Schluessel
	public static final int MEMSORT_BUFFER		= 16;		// Anzahl der Schluessel die gleichzeitig 
															// in den Speicher geladen werden
	public static final int SCHLUESSELGROESSE	= 4;		// Lanege eines Schluessels in Bytes
	public static final int READ_BUFFER			= MEMSORT_BUFFER / 3;
	public static final int WRITE_BUFFER		= MEMSORT_BUFFER / 3;
}