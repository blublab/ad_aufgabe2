package balancedms;

import java.io.IOException;

public class BufferedWriter {
	private Tape tape			= null;
	private int[] buffer		= null;
	private int currentIndex	= 0;
	
	BufferedWriter(Tape t) throws IOException{
		tape = t;
		tape.resetForWrite();
	}
	
	public void add(int elem) throws IOException{
		if (currentIndex < (Constants.WRITE_BUFFER -1)){
			buffer[currentIndex++] = elem;
		} else {
			flush();
			buffer[currentIndex++] = elem;
		}
	}
	
	public void switchToTape(Tape t) throws IOException{
		flush();
		tape = t;
		
	}
	
	public void flush() throws IOException{
		tape.writeSequence(buffer);
		currentIndex = 0;
	}
}
