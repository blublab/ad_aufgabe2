package balancedms.tapes;

import java.io.File;
import java.io.IOException;


public interface Tape {
	int[] readSequence(int len) throws IOException;
	void writeSequence(int[] seq) throws IOException;
	boolean isEoF();
	void resetForWrite() throws IOException;
	void resetForRead() throws IOException;
	void setEoF(boolean b);
	File getFile();
	void close() throws IOException;
	void print() throws IOException;
}
