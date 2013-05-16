package balancedms.buffer;

import java.io.IOException;

import balancedms.*;
import balancedms.tapes.Tape;


public class BufferedTapeWriter implements TapeWriter{

	private Tape tape		= null;
	private int size		= Constants.WRITE_BUFFER;
	private int index		= 0;
	private int[] buffer	= null; 
	
	public BufferedTapeWriter(Tape t){
		this.tape = t;
		this.buffer = new int[size];
	}
	
	@Override
	public void add(int elem) throws IOException {
		buffer[index] = elem;
		index++;
		if (isFull())
			write();
	}

	/**
	 * TODO writeSequence die genaue Länge der Sequenz übergeben?
	 */
	@Override
	public void flush() throws IOException {
		int[] temp = new int[index];
		for (int k = 0; k<index; k++){
			temp[k] = buffer[k];
		}
		tape.writeSequence(temp);
		reset();
	}

	@Override
	public void replaceTape(Tape t) throws IOException {
		if (isFull())
			write();
		else
			flush();
		tape = t;
		
	}
	
	private void write() throws IOException{
		tape.writeSequence(buffer);
		reset();
	}
	
	private boolean isFull(){
		return size - index == 0;
	}
	
	private void reset(){
		index = 0;
	}
}
