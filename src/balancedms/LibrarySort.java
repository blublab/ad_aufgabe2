package balancedms;

import java.util.Arrays;

public class LibrarySort<T> implements MemSort<T>{

	@Override
	public T[] sortSequence(T[] seq, int length) {
		Arrays.sort(seq);
		return seq;
	}
}