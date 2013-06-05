package balancedms.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _BinTape implements _Tape {
	private List<_Run> runs	= null;
	
	public _BinTape(){
		runs = new ArrayList<_Run>();
	}
	
	public _Run newRun() throws FileNotFoundException{
		_Run temp = new _BinFileRun();
		runs.add(temp);
		return temp;
	}
	public Iterator<_Run> iterator(){
		return new Iterator<_Run>(){
			int index = 0;

			@Override
			public boolean hasNext() {
				return !runs.isEmpty();
			}

			@Override
			public _Run next() {
				return runs.get(index++);
			}

			@Override
			public void remove() {
				// Not implemented
				assert(false); 
			}
			
		};
	}

	@Override
	public int[] readSequence(int len) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeSequence(int[] seq) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetForRead() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetForWrite() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print() throws IOException {
		// TODO Auto-generated method stub
		
	}
}