package balancedms.buffer;

import java.io.IOException;

import balancedms.tapes.Tape;

public interface TapeWriter {
	public void add(int elem) throws IOException;
	public void flush() throws IOException;
	public void replaceTape(Tape t) throws IOException;
}
