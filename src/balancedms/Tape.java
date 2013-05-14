package balancedms;

import java.io.IOException;

public interface Tape {
	int[] readSequence(int len) throws IOException;
	void writeSequence(int[] seq) throws IOException;
	//boolean isWritable();
	//boolean isEoF();
	void resetForRead() throws IOException;
	void resetForWrite() throws IOException;
}
