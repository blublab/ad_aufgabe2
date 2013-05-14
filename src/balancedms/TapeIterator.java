package balancedms;

import java.io.IOException;

public class TapeIterator {
	Tape tape			= null;
	int[] buffer 	= null;
	int runLength	= 0;
	boolean isEOF	= false;
	boolean isEOR	= false;
	boolean becomesEOF		= false;
	boolean becomesEOR		= false;
	int returnedElements	= 0;
	int currentIndex		= 0;
	
	TapeIterator(Tape t, int runLength) throws IOException{
		this.tape	= t;
		this.tape.resetForRead();
		this.runLength = runLength;
		buffer = new int[Constants.READ_BUFFER];
		fillBuffer();
	}
	
	public int next() throws IOException{
		if (currentIndex < (buffer.length-1)){
			return buffer[currentIndex++];
		} else {
			if (!becomesEOR && !becomesEOF){
				int temp = buffer[currentIndex];
				fillBuffer();
				return temp;
			} else {
				if (becomesEOR)
					isEOR = true;
				if (becomesEOF)
					isEOF = true;
					isEOR = true;
				return buffer[currentIndex];
			}
		}
	}
	
	public void nextRun() throws IOException{
		if (!isEOF){
			isEOR 		= false;
			becomesEOF 	= false;
			becomesEOR 	= false;
			fillBuffer();
		}
	}
	
	private void fillBuffer() throws IOException{
		
		/*Falls verbleibende Elemente weniger als Buffersize*/
		if ((this.runLength - returnedElements) < Constants.READ_BUFFER){
			buffer = tape.readSequence(this.runLength - returnedElements);
			becomesEOR = true;
			if (buffer.length < (this.runLength - returnedElements)){
				becomesEOF = true;
			}
		} 
		
		/*Sonst fuelle gesamten Buffer*/
		else {
			buffer = tape.readSequence(Constants.READ_BUFFER);
			if (buffer.length < Constants.READ_BUFFER){
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
