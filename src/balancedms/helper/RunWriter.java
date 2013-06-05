/**
 * 
 */
package balancedms.helper;

import balancedms.io._BinTape;

/**
 * Schreibt uebergebene Elemente auf die Runs von Tapes die er verwaltet
 *
 */
public interface RunWriter {
	
	/* schreibt die uebergebenen Werte in sortierter Reihenfolge auf den aktuellen Run */
	public void merge(Integer key1, Integer key2);
	
	/* Rotiert die Tapes auf die aktuell geschrieben wird*/
	public void flip();
	
	/* Ersetzt die aktuellen Tapes durch die uebergebenen*/
	public void  replaceAll(_BinTape t1, _BinTape t2);
}
