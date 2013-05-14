package balancedms.algorithm;

import java.util.Arrays;

public class LibrarySort implements MemSort{

	@Override
	public void sortSequence(int[] seq, int length) {
		Arrays.sort(seq);
	}
}