package balancedms.io;

import java.io.IOException;

public interface _Tape {
	public int[] readSequence(int len) throws IOException;
	void writeSequence(int[] seq) throws IOException;
	//boolean isWritable();
	//boolean isEoF();
	void resetForRead() throws IOException;
	void resetForWrite() throws IOException;
	void print() throws IOException;
}
