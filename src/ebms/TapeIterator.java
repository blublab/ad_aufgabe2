package ebms;
import java.io.IOException;

public interface TapeIterator {
	
	/**
	 * 
	 * Incrementiert zum naechsten Integer und gibt diesen zurueck 
	 */
	public int next() throws IOException;
	
	/*
	 * 
	 */
	public boolean hasNext();
	
	/**
	 * Gibt den aktuellen Wert zurueck ohne zu inkrementieren
	 */
	public int peek();

	void close() throws IOException;

}
