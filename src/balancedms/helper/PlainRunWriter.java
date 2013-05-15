package balancedms.helper;

import java.util.ArrayList;
import java.util.List;

import balancedms.io.Tape;

public class PlainRunWriter implements RunWriter{
	List<Tape> tapes = null;
	
	public PlainRunWriter(Tape t1, Tape t2){
		tapes = new ArrayList<Tape>();
		tapes.add(t1);
		tapes.add(t2);
	}

	@Override
	public void merge(Integer key1, Integer key2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flip() {
		replaceAll(tapes.get(1), tapes.get(0));
	}

	@Override
	public void replaceAll(Tape t1, Tape t2) {
		tapes.clear();
		tapes.add(t1);
		tapes.add(t2);		
	}

}
