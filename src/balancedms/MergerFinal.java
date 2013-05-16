package balancedms;

import java.io.IOException;

import balancedms.buffer.*;
import balancedms.tapes.Tape;

public class MergerFinal {
	
	private boolean ende = false;
	private TapeReader src1 = null;
	private TapeReader src2 = null;
	private TapeWriter t = null;

	public void initialize(Tape source, Tape target1, Tape target2) throws IOException {
		MemSort ms = new LibrarySort();
		Tape[] targets = new Tape[] { target1, target2 };
		source.resetForRead();
		int[] seq = source.readSequence(Constants.MEMSORT_BUFFER);
		while (seq.length != 0) {
			ms.sortSequence(seq);
			targets[0].writeSequence(seq);
			targets = flipTargets(targets);
			seq = source.readSequence(Constants.MEMSORT_BUFFER);
		}
		source.resetForWrite();
	}
	
	
	public void mergen(int runSize, Tape tape1, Tape tape2, Tape tape3, Tape tape4) throws IOException{
		tape1.resetForRead();
		tape2.resetForRead();
		Tape[] sources = new Tape[] { tape1, tape2 };
		Tape[] targets = new Tape[] { tape3, tape4 };
		src1 = new BufferedTapeReader(sources[0], runSize);
		src2 = new BufferedTapeReader(sources[1], runSize);
		t = new BufferedTapeWriter(targets[0]);
		
		while(!ende){
			mergeRun(runSize);
			if(src1.isEoF() && src2.isEoF()){
				
				System.out.println("\n==================== Runsize: "+ runSize +" ====================\n");





				System.out.println("==================== "+sources[0]+" ====================");

				FolgenErzeuger.ausgabe(sources[0]);
				System.out.println("==================== "+sources[1]+" ====================");

				FolgenErzeuger.ausgabe(sources[1]);
				System.out.println("==================== "+targets[0]+" ====================");

				FolgenErzeuger.ausgabe(targets[0]);
				System.out.println("==================== "+targets[1]+" ====================");

				FolgenErzeuger.ausgabe(targets[1]);
				
				
				
				Tape[] temp = sources;
				sources = flipT2S(targets);
				targets = flipS2T(temp);
				src1.replaceTape(sources[0]);
				src2.replaceTape(sources[1]);
				runSize *= 2;
				if(src1.isEoF() || src2.isEoF()) ende = true;
			} else {
				targets = flipTargets(targets);
				src1.nextRun();
				src2.nextRun();
				t.replaceTape(targets[0]);
			}
		}
	}
	
	
	private void mergeRun(int runSize) throws IOException {

		boolean src1Lost = false;

		int src1Int = src1.read();
		int src2Int = src2.read();
		if (src1Int <= src2Int) {
			t.add(src1Int);
		} else {
			src1Lost = true;
			t.add(src2Int);
		}

		while (!src1.isEoR() && !src2.isEoR()) {

			if (src1Lost) {
				src2Int = src2.read();
				if (src1Int <= src2Int) {
					src1Lost = false;
					t.add(src1Int);
				} else {
					t.add(src2Int);
				}
			} else {
				src1Int = src1.read();
				if (src1Int <= src2Int) {
					t.add(src1Int);
				} else {
					src1Lost = true;
					t.add(src2Int);
				}
			}
			
			if(src1Lost)
				t.add(src2Int);
			else
				t.add(src1Int);
			
			while ((src1.isEoR()) && (!src2.isEoR())) {
				t.add(src2.read());
			}

			while (!src1.isEoR() && src2.isEoR()) {
				t.add(src1.read());
			}

		}
	}
	
	private Tape[] flipTargets(Tape[] targets){
		Tape[] ret = new Tape[2];
		ret[0] = targets[1];
		ret[1] = targets[0];
		return ret;
	}
	
	private Tape[] flipS2T(Tape[] sources) throws IOException{
		for(Tape t: sources){
			t.resetForWrite();
		}
		return sources;
	}
	
	private Tape[] flipT2S(Tape[] targets)throws IOException{
		for(Tape t: targets){
			t.resetForRead();
		}
		return targets;
	}
}
