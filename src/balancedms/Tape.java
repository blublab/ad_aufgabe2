package balancedms;

import java.io.IOException;

public interface Tape {
	int[] readSequence(int len) throws IOException;
	void writeSequence(int[] seq) throws IOException;
	boolean isWritable();
	void setWritable(boolean b);
	boolean isEoF();
	void reset() throws IOException;
	public void resetForWrite() throws IOException;
	public void resetForRead() throws IOException;
	public void setEoF(boolean b);
}
