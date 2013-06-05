package ebms;
import java.io.IOException;

public interface TapeIterator {
	
	/**
	 * 
	 * @return
	 * @throws IOException 
	 */
	public int next() throws IOException;
	
	/*
	 * 
	 */
	public boolean hasNext();
	
	/**
	 * 
	 * @return
	 */
	public int peek();

}
