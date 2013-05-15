package balancedms.helper;

import java.io.IOException;

import balancedms.controls.Constants;
import balancedms.io.Tape;

public class TapeIterator {
	public boolean isEOF	= false;
	public boolean isEOR	= false;

	private Tape tape				= null;
	private int[] buffer 			= null;
	private int runLength			= 0;
	private boolean becomesEOF		= false;
	private boolean becomesEOR		= false;
	private int returnedElements	= 0;
	private int currentIndex		= 0;
	
	public TapeIterator(Tape t, int runLength) throws IOException{
		this.tape	= t;
		this.tape.resetForRead();
		this.runLength = runLength;
		buffer = new int[Constants.READ_BUFFER];
		//fillBuffer();
	}
	
	public int next() throws IOException{
		assert( !(isEOF||isEOR) );
		assert( runLength - returnedElements > 0);
		assert( buffer != null);
		
		if (currentIndex < (buffer.length-1)){
			incrementAndCheck();
			return buffer[currentIndex++];
			
		} else {
			if (!becomesEOR && !becomesEOF){
				int temp = buffer[currentIndex];
				fillBuffer();
				assert(buffer!=null);
				incrementAndCheck();
				return temp;
				
			} else {
				if (becomesEOR)
					isEOR = true;
				if (becomesEOF){
					isEOF = true;
					isEOR = true;
					}
				incrementAndCheck();
				return buffer[currentIndex];
			}
		}
	}
	
	public void nextRun() throws IOException{
		if (!isEOF){
			isEOR 				= false;
			becomesEOF 			= false;
			becomesEOR 			= false;
			returnedElements	= 0;
			fillBuffer();
		}
	}
	
	private void incrementAndCheck(){
		returnedElements++;
		if (this.runLength - returnedElements <= 0)
			isEOR = true;
	}
	
	private void fillBuffer() throws IOException{
		
		/*Falls verbleibende Elemente weniger als Buffersize*/
		if ((this.runLength - returnedElements) <= Constants.READ_BUFFER){
			buffer = tape.readSequence(this.runLength - returnedElements);
			assert(buffer != null);
			becomesEOR = true;
			if (buffer.length < (this.runLength - returnedElements)){
				becomesEOF = true;
			}
		} 
		
		/*Sonst fuelle gesamten Buffer*/
		else {
			buffer = tape.readSequence(Constants.READ_BUFFER);
			assert(buffer!=null);
			if (buffer.length < Constants.READ_BUFFER){
				becomesEOF = true;
				if (buffer.length == 0){
					isEOF = true;
					isEOR = true;
				}
			}
		}
		assert(buffer!=null);
		currentIndex	= 0;
	}
}
