package balancedms;

import java.io.IOException;
import java.util.Iterator;

public class TapeIterator {
	Tape t			= null;
	int[] buffer 	= null;
	int runLength	= 0;
	boolean isEOF	= false;
	boolean isEOR	= false;
	boolean becomesEOF	= false;
	boolean becomesEOR	= false;
	int returnedElements	= 0;
	int currentIndex	= 0;
	
	TapeIterator(Tape t, int runLength){
		this.t	= t;
		this.runLength = runLength;
		buffer = new int[Constants.TAPE_BUFFER];
	}
	
	public int next(){
		return buffer[currentIndex++];
	}
	
	private void fillBuffer() throws IOException{
		
		/*Falls verbleibende Elemente weniger als Buffersize*/
		if ((this.runLength - returnedElements) < Constants.TAPE_BUFFER){
			buffer = t.readSequence(this.runLength - returnedElements);
			becomesEOR = true;
			if (buffer.length < (this.runLength - returnedElements)){
				becomesEOF = true;
			}
		} 
		
		/*Sonst fuelle gesamten Buffer*/
		else {
			buffer = t.readSequence(Constants.TAPE_BUFFER);
			if (buffer.length < Constants.TAPE_BUFFER){
				becomesEOF = true;
			}
		}
		currentIndex	= 0;
	}
	
	private void flushBuffer(){
		currentIndex	= 0;
		buffer 			= null;
	}
}
