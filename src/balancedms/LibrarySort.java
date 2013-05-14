package balancedms;

import java.util.Arrays;

public class LibrarySort implements MemSort{

	@Override
	public int[] sortSequence(int[] seq) {
		Arrays.sort(seq);
		return seq;
	}
}