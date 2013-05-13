package balancedms;

import java.io.IOException;

public class Merger<T> {
	private Tape tape1 			= null;
	private Tape tape2 			= null; 
	private Tape tape3 			= null;
	private Tape tape4 			= null;
	private Tape[] targets 	= {null,null};
	private Tape[] sources	= {null,null};
	int runLength			= 0;
	boolean flipped			= true;
	
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
		
		tape1		= source1;
		tape2		= source2;
		tape3		= target1;
		tape4		= target2;
		sources 	= new Tape[]{source1, source2};
		targets		= new Tape[]{target1, target2};
		this.runLength	= runLength;
		
		while (runLength <= Constants.FOLGENLAENGE) {
			while(!(sources[0].isEoF() && sources[1].isEoF())){
				mergeRuns(runLength, sources[0], sources[1], targets[0]);
				flipTargets();
			}
			flipSourcesTargets();
			runLength *=2;
		}
		return targets[1];
	}
	
	/* privat -> public */
	public void mergeRuns(int runLength, Tape source1, Tape source2, Tape target) throws IOException {
		while(flipped){
			flipped = false;
			while (!(iterator1.isEOF && iterator2.isEOF){
				//iteratoren erneuern
				while (!(iterator1.isEOR || iterator2.isEOR)){
					
					BufferedWriter = (iterator1.curr <= iterator2.curr) iterator1.curr : iterator2.curr;
					if (iterator1.isEOR){
						while(!iterator2.EOR) {BufferedWriter = iterator2.next());
					} else {
						while(!iterator1.EOR) {BufferedWriter = iterator1.next());
					}
							
				}
			}
				
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
		
	}
	
	private void flipTargets(){
		Tape tmp 	= targets[0];
		targets[0]	= targets[1];
		targets[1]	= tmp;
	}
	
	private void flipSourcesTargets() throws IOException{
		for(Tape t: sources){
			t.reset();
		}
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