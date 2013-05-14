package balancedms.algorithm;

import java.io.IOException;

import balancedms.controls.Constants;
import balancedms.helper.BufferedWriter;
import balancedms.helper.TapeIterator;
import balancedms.io.Tape;

public class Merger {
//	private Tape tape1 			= null;
//	private Tape tape2 			= null; 
//	private Tape tape3 			= null;
//	private Tape tape4 			= null;
	private Tape[] targets 		= {null,null};
	private Tape[] sources		= {null,null};
	private int runLength		= 0;
	private boolean flipped		= true;
	
	/**
	 * Reads elements of Type T from sources (alternating with runLength), merge-sorts them into 
	 * targets (alternating with 2*runLength)
	 * @throws IOException 
	 */
	public Tape mergeTapes(int runLength, Tape source1, Tape source2,
			Tape target1, Tape target2) throws IOException {
		assert(source1 != null);
		assert(source2 != null);
		assert(target1 != null);
		assert(target2 != null);
		assert(runLength > 0);
		
//		tape1		= source1;
//		tape2		= source2;
//		tape3		= target1;
//		tape4		= target2;
		sources 	= new Tape[]{source1, source2};
		targets		= new Tape[]{target1, target2};
		this.runLength	= Constants.MEMSORT_BUFFER;
		
		while(flipped){
			flipped = false;
			TapeIterator iterator1 	= new TapeIterator(sources[0], runLength);
			TapeIterator iterator2 	= new TapeIterator(sources[1], runLength);
			BufferedWriter bw		= new BufferedWriter(sources[0]);
			
			while (!(iterator1.isEOF && iterator2.isEOF)){
				
				//iteratoren auf neuen Run Synchronisieren (bei EOF bleibt EOR erhalten)
				iterator1.nextRun();
				iterator2.nextRun();
				
				while (!(iterator1.isEOR && iterator2.isEOR)){
					if (iterator1.isEOR)
						while(!iterator2.isEOR) {bw.add(iterator2.next());}
					else if (iterator2.isEOR)
						while(!iterator1.isEOR) {bw.add(iterator1.next());}
					else {
						int current1	= iterator1.next();
						int current2	= iterator2.next();
						if (current1 <= current2) {
							bw.add(current1);
							while (current1 <= current2 && !(iterator1.isEOR)) {
								current1	= iterator1.next();
								bw.add(current1);
							}
						} else {
							while (current2 < current1 && !(iterator2.isEOR)) {
								bw.add(current2);
								current2	= iterator2.next();
								bw.add(current2);
							}
						}
					}
				}
				System.out.println("ende innere schleife");
				System.out.flush();
				if (!(iterator1.isEOR && iterator1.isEOF && iterator2.isEOR && iterator2.isEOF)){
					flipTargets();
					bw.switchToTape(targets[0]);
					flipped = true;
				}
			}
			System.out.println("ende mittlere schleife");
			System.out.flush();
			flipSourcesTargets();
			this.runLength *= 2;
		}
		
		return targets[1];
	}
	
	public void initialize(Tape source, Tape target1, Tape target2) throws IOException {
		this.targets	= new Tape[]{target1, target2};
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
		
		// -> Fertig, Rueckgabe
		
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
		Tape tmp 	= targets[0];
		targets[0]	= targets[1];
		targets[1]	= tmp;
	}
	
	private void flipSourcesTargets() throws IOException{
//		for(Tape t: sources){
//			t.reset();
//		}
		Tape[] temp = targets;
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