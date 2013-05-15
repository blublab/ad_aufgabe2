package balancedms.helper;

import java.io.IOException;

import balancedms.controls.Constants;
import balancedms.io.Tape;

public class BufferedWriter {
	private Tape tape			= null;
	private int[] buffer		= null;
	private int currentIndex	= 0;
	
	public BufferedWriter(Tape t) throws IOException{
		tape = t;
		//tape.resetForWrite();
		buffer = new int[Constants.WRITE_BUFFER];
	}
	
	public void add(int elem) throws IOException{
		tape.writeSequence(new int[]{elem}); // No Buffer Style
		System.out.println("Got "+elem);
//		assert(buffer != null);
//		if (currentIndex < (Constants.WRITE_BUFFER)){
//			buffer[currentIndex++] = elem;
//		} else {
//			flush();
//			buffer[currentIndex++] = elem;
//		}
	}
	
	public void switchToTape(Tape t) throws IOException{
		flush();
		tape = t;
		
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
