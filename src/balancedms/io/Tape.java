package balancedms.io;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tape {
	private List<Run> runs	= null;
	
	public Tape(){
		runs = new ArrayList<Run>();
	}
	
	public Run newRun() throws FileNotFoundException{
		Run temp = new BinFileRun();
		runs.add(temp);
		return temp;
	}
	public Iterator<Run> iterator(){
		return new Iterator<Run>(){
			int index = 0;

			@Override
			public boolean hasNext() {
				return !runs.isEmpty();
			}

			@Override
			public Run next() {
				return runs.get(index++);
			}

			@Override
			public void remove() {
				// Not implemented
				assert(false); 
			}
			
		};
	}
}