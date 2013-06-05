package balancedms.algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import balancedms.controls.Constants;
import balancedms.helper.BufferedWriter;
import balancedms.helper.PlainRunWriter;
import balancedms.helper.RunWriter;
import balancedms.helper.TapeIterator;
import balancedms.io.Tape;
import balancedms.io._Tape;
import balancedms.io._Run;
import balancedms.io.Tape;

public class Merger {
//	private Tape tape1 			= null;
//	private Tape tape2 			= null; 
//	private Tape tape3 			= null;
//	private Tape tape4 			= null;
	private _Tape[] targets 		= {null,null};
	private _Tape[] sources		= {null,null};
	private int runLength		= 0;
	private boolean flipped		= true;
	
	/**
	 * Reads elements of Type T from sources (alternating with runLength), merge-sorts them into 
	 * targets (alternating with 2*runLength)
	 * @throws IOException 
	 */
	
	
	public Tape mergeTapes(int runLength, Tape source1, Tape source2,
			Tape target1, Tape target2) throws IOException {
		
		while(flipped){
			flipped = false;
			Iterator<_Run> srcAit	= source1.iterator();
			Iterator<_Run> srcBit	= source2.iterator();
			
			while(srcAit.hasNext() && srcBit.hasNext()){
				_Run runA		= srcAit.next();
				_Run runB		= srcBit.next();
				Iterator<Integer> runAit	= runA.iterator();
				Iterator<Integer> runBit	= runB.iterator();
				RunWriter prw	= new PlainRunWriter(target1, target2);
				
				while(runAit.hasNext() && runBit.hasNext()){
					Integer elemA = runAit.next();
					Integer elemB = runBit.next();
					prw.merge(elemA, elemB);
				}
				//schreibe Elemente aus verbliebenem Run weg
				//Flush
				if((srcAit.hasNext() || srcBit.hasNext())){
					prw.flip();
					flipped = true;
				}
			}
			//switchSourcesTargets();
		}
		return null;
	}

	public void initialize(Tape initialSequence, int numberTapes) throws IOException {
		assert(numberTapes%2 == 0); //Es werden zwei gleich grosse Gruppen von Tapes angenommen
		
		List<Tape> group1		= new ArrayList<Tape>();
		List<Tape> group2		= new ArrayList<Tape>();
		List<List<Tape>> tapes	= new ArrayList<List<Tape>>();
		
		for (List<Tape> l : tapes) {
			for (int i = 0; i < numberTapes / 2; i++) {
				l.add(new BinTape());
			}
		}

		
		this.targets	= new _Tape[]{target1, target2};
		this.targets[0].resetForWrite();
		this.targets[1].resetForWrite();
		source.resetForRead();
		MemSort ms 		= new LibrarySort();
		int[] seq	= source.readSequence(Constants.MEMSORT_BUFFER);
		while (seq.length != 0){
			ms.sortSequence(seq, Constants.MEMSORT_BUFFER);
			targets[0].writeSequence(seq);
			flipTargets();
			seq = source.readSequence(Constants.MEMSORT_BUFFER);
		}
	}

	

	
	//while(flipped)
	//flipped->false
	  //while (!(iterator1.EOF && iterator2.EOF))
		//Iteratoren erneuern
		//while !(iterator1.ENDRUN || iterator2.ENDRUN)
			//    BufferedWriter = (iterator1 <= iterator2) iterator1 : iterator2
			//if iterator1.ENDE
			//	while(!iterator1.ENDE) {BufferedWriter = iterator1)
			//else
			//  while(!iterator2.ENDE) {BufferedWriter = iterator2)
			//
			//BufferedWriter.flush()
		//if iteratoren nicht EOF -> flipped = true
		//	flipTargets und iteratoren -> !ENDRUN
	  //flipSourcesTargets()
		
	
	
	private void flipTargets(){
		_Tape tmp 	= targets[0];
		targets[0]	= targets[1];
		targets[1]	= tmp;
	}
	
	private void flipSourcesTargets() throws IOException{
//		for(Tape t: sources){
//			t.reset();
//		}
		_Tape[] temp = targets;
		targets = sources;
		sources = temp;
		
	}
}
	
	
	
	
/*	final int MERGE_BUFFER_SIZE = 1;
	for (int i = 0; i < 2 * runLength; i++) {
		int[] src1 = source1.readSequence(MERGE_BUFFER_SIZE);
		int[] src2 = source2.readSequence(MERGE_BUFFER_SIZE);
		if (src1[0] > src2[0]) {
			while (src1[0] > src2[0]) {
				targets[0].writeSequence(src2);
				src2 = source2.readSequence(MERGE_BUFFER_SIZE);
				i++;
				if (i == runLength - 1)
					break;
			}
		} else if (src1[0] < src2[0]) {
			while (src1[0] < src2[0]) {
				targets[0].writeSequence(src1);
				src1 = source1.readSequence(MERGE_BUFFER_SIZE);
				i++;
				if (i == runLength - 1)
					break;
			}
		} else {
			targets[0].writeSequence(src1);
			targets[0].writeSequence(src2); // NICHT STABIL???
		}
	}
	
	//runlaenge verdoppeln
	runLength *= 2;
	
	//flip sources und targets
	
}*/