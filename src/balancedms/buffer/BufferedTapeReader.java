package balancedms.buffer;

import java.io.IOException;

import balancedms.*;
import balancedms.tapes.Tape;

public class BufferedTapeReader implements TapeReader{

	private Tape tape = null;
	private int index = 0;
	private int[] buffer = null;
	private final int maxSize = Constants.READ_BUFFER;
	private boolean tapeEoF = false;
	private boolean EoF = false;
	private int runSize = 0;
	private int runPos = 0;
	private boolean becomesEoR = false;
	private int currentSize = 0;
	private boolean EoR = false;
	
	public BufferedTapeReader(Tape t, int runSize) throws IOException{
		this.tape = t;
		this.runSize = runSize;
		this.buffer = new int[maxSize];
		reset();
	}

	@Override
	public int read() throws IOException {
		assert(!EoR);
		assert(!EoF);
		int ret = buffer[index++];
		if(isEmpty()){
			if(tapeEoF){
				EoR = true;
				EoF = true;
			}
			else 
				if(becomesEoR)
					EoR = true;
				else
					reset();
			runPos++;
			checkEoR();
			return ret;
		}
		else{
			runPos++;
			checkEoR();
			return ret;
		}
	}

	
	@Override
	public boolean isEoR() {
		return EoR;
	}

	@Override
	public void replaceTape(Tape t) throws IOException {
		tape = t;
		EoF = false;
		tapeEoF = false;
		reset();
	}
	
	@Override
	public void nextRun() throws IOException{
		runPos = 0;
		EoR = false;
		reset();
	}

	@Override
	public boolean isEoF(){
		return EoF;
	}
	
	public boolean isTapeEoF(){
		return tapeEoF;
	}
	
	private void reset() throws IOException{
		checkEoR();
		if(becomesEoR){
			buffer = tape.readSequence(runSize - runPos);
			currentSize = buffer.length;
			if(currentSize < runSize -runPos){
				tapeEoF = true;
			}
			if(currentSize == 0){
				EoR = true;
				EoF = true;
			}
		}else{
			buffer = tape.readSequence(maxSize);
			currentSize = buffer.length;
			if(currentSize < maxSize)
				tapeEoF = true;
			if(currentSize == 0){
				EoR = true;
				EoF = true;
			}
		}
		index = 0;
	}
	
	private void checkEoR(){
		becomesEoR = runPos + maxSize >= runSize;
	}

	private boolean isEmpty(){
		return currentSize - index == 0;
	}


}
