package balancedms;

import java.io.IOException;

public interface Tape {
	int[] readSequence(int length);
	void writeSequence(int[] seq) throws IOException;
}
