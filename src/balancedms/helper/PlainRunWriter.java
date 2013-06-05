package balancedms.helper;

import java.util.ArrayList;
import java.util.List;

import balancedms.io._BinTape;

public class PlainRunWriter implements RunWriter{
	List<_BinTape> tapes = null;
	
	public PlainRunWriter(_BinTape t1, _BinTape t2){
		tapes = new ArrayList<_BinTape>();
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
	public void replaceAll(_BinTape t1, _BinTape t2) {
		tapes.clear();
		tapes.add(t1);
		tapes.add(t2);		
	}

}
