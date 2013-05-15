/**
 * 
 */
package balancedms.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import balancedms.algorithm.Merger;
import balancedms.io.BinFile;
import balancedms.io.Tape;


/**
 * @author m215025
 *
 */
public class TestMerger {

	List<Tape> tapes	= null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tapes = new ArrayList<Tape>();
		tapes.add(new BinFile("testTape1.bin"));
		tapes.add(new BinFile("testTape2.bin"));
		tapes.add(new BinFile("testTape3.bin"));
		tapes.add(new BinFile("testTape4.bin"));
		
//		int length	= 10;
//		int[] seq	= new int[length];
//		Random rm = new Random();
//		
//		int seed = 0;
//		for (Tape t: tapes){
//			rm.setSeed(seed++);
//			for (int j=0; j<length; j++){
//				seq[j] = rm.nextInt(10);
//			}
//			t.writeSequence(seq);
//		}
		
	}

	@Test
	public void testInitialize() throws IOException {
		tapes.get(0).resetForWrite();
		tapes.get(0).writeSequence(new int[]{1,3,5,7,9,11});
		
		Merger m = new Merger();
		m.initialize(tapes.get(0), tapes.get(2), tapes.get(3));
		
		tapes.get(2).resetForRead();
		//int[] seq1 = tapes.get(2).readSequence(len);
		
	}

}
