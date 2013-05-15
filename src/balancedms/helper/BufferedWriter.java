package balancedms.helper;

import java.io.IOException;

import balancedms.controls.Constants;
import balancedms.io.OldTape;

public class BufferedWriter {
	private OldTape oldTape			= null;
	private int[] buffer		= null;
	private int currentIndex	= 0;
	
	public BufferedWriter(OldTape t) throws IOException{
		oldTape = t;
		//tape.resetForWrite();
		buffer = new int[Constants.WRITE_BUFFER];
	}
	
	public void add(int elem) throws IOException{
		oldTape.writeSequence(new int[]{elem}); // No Buffer Style
		//System.out.println("Got "+elem);
//		assert(buffer != null);
//		if (currentIndex < (Constants.WRITE_BUFFER)){
//			buffer[currentIndex++] = elem;
//		} else {
//			flush();
//			buffer[currentIndex++] = elem;
//		}
	}
	
	public void switchToTape(OldTape t) throws IOException{
		flush();
		oldTape = t;
		
	}
	
	public void flush() throws IOException{
		//if (buffer.length > 0)
		//	tape.writeSequence(buffer);
//		for (int i: buffer){
//			System.out.println("Schreibe: "+i);
//		}
		//currentIndex = 0;
	}
}
