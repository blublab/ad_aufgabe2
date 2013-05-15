package balancedms.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import balancedms.io.BinFileRun;

public class TestBinRunFile {

	BinFileRun bfr = null;
	
	@Before
	public void setUp() throws Exception {
		bfr	= new BinFileRun();
	}

	@Test
	public void testToString() {
		assert(bfr.toString() != null);
		System.out.println(bfr.toString());
	}
	
	@Test
	public void testIterator() throws IOException{
		Iterator<Integer> it = bfr.iterator();
		assertEquals(it.hasNext(), false);
		bfr.add(new int[]{1,3,2});
		assertEquals(it.hasNext(), true);
		assertEquals(it.next(),Integer.valueOf(1));
		assertEquals(it.next(),Integer.valueOf(3));
		assertEquals(it.next(),Integer.valueOf(2));
		assertEquals(it.hasNext(), false);
	}

}
