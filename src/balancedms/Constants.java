package balancedms;

import java.math.BigInteger;

/**
 * Kontrollparameter fuer Balanced 4-way Mergesort
 * 
 *	Nicht instanziierbar
 */
public final class Constants {
	private Constants(){};
	
	public static final int SCHLUESSELGROESSE	= 4;		// Lanege eines Schluessels in Bytes

	static BigInteger temp1 =  BigInteger.valueOf(10);
	static BigInteger temp2 =  BigInteger.valueOf(1);
	static BigInteger bla = temp1.multiply(temp2);
	public static final BigInteger FOLGENLAENGE		= bla;		// Anzahl der zu sortierenden Schluessel
	public static final int MEMSORT_BUFFER		= 2;		// Anzahl der Schluessel die gleichzeitig 
													// in den Speicher geladen werden
	public static final int READ_BUFFER		= 1; //Math.max(1,MEMSORT_BUFFER/2);
	public static final int WRITE_BUFFER		= 1; //Math.max(1,MEMSORT_BUFFER/2);
}