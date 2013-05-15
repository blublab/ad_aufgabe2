package balancedms;

import java.io.IOException;

public class Merger2 {
	
	private int writeBufInd = 0;
	private int[] writeBuf = new int[Constants.WRITE_BUFFER];
	private Tape source1 = null;
	private int src1Ind = 0;
	private int runInd1 = 0;
	private int runInd2 = 0;
	private Tape source2 = null;
	private int src2Ind = 0;
	private Tape target = null;
	boolean EoR = false;
	
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
		Tape[] sources = new Tape[] { tape1, tape2 };
		Tape[] targets = new Tape[] { tape3, tape4 };
		
		while(!(runSize > Constants.FOLGENLAENGE)){
			mergeRun(runSize, sources[0], sources[1], targets[0]);
			writeBuf = new int[Constants.WRITE_BUFFER];
			writeBufInd = 0;
			runInd1 =0;
			runInd2 =0;
			EoR = false;
			src1Ind = 0;
			src2Ind = 0;
			targets = flipTargets(targets);
			if(sources[0].isEoF() && sources[1].isEoF()){
				
				
				
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
				runSize *= 2; // Prüfen, ob runSize > folgenlaenge
			}
		}
	}
	
	private void mergeRun(int runSize, Tape source1, Tape source2, Tape target) throws IOException{
		this.source1 = source1;
		this.source2 = source2;
		this.target = target;

		int[] src1Buf = source1.readSequence(Constants.READ_BUFFER);
		int[] src2Buf = source2.readSequence(Constants.READ_BUFFER);
		int runInd = 0;
		while((runInd < (2*runSize)) && (!(source1.isEoF()) || !(source2.isEoF()))){ // 
			if((runInd1 >= runSize) && !(runInd2 >= runSize)){
				writeBuf = new int[(2*runSize)-runInd];
				writeBufInd = 0;
				while(!(runInd2 >= runSize)){
					src2Buf = source2Won(src2Buf, runSize);
					runInd++;
				}
			}
			if((runInd2 >= runSize) && !(runInd1 >= runSize)){
				writeBuf = new int[(2*runSize)-runInd];
				writeBufInd = 0;
				while(!(runInd1 >= runSize)){
					src1Buf = source1Won(src1Buf, runSize);
					runInd++;
				}
			}
			if(source1.isEoF()){
				writeBuf = new int[(2*runSize)-runInd];
				writeBufInd = 0;
				while((runInd < runSize*2) && !(source2.isEoF()) && !EoR){
					src2Buf = source2Won(src2Buf, runSize);
					runInd++;
				}
				runInd++;
				source2.setEoF(true);
			}
			if(source2.isEoF()){
				writeBuf = new int[(2*runSize)-runInd];
				writeBufInd = 0;
				while((runInd < runSize*2) && !(source1.isEoF()) && !EoR){
					src1Buf = source1Won(src1Buf, runSize);
					runInd++;
				}
				runInd++;
				source1.setEoF(true);
			}
			if((runInd < (2*runSize)&&!EoR&& !source1.isEoF() && !source2.isEoF())){
				if(src1Buf[src1Ind] <= src2Buf[src2Ind]){
					src1Buf = source1Won(src1Buf, runSize);
				} else {
					src2Buf = source2Won(src2Buf, runSize);
				}
			}
			runInd++;
		}
	}
	
	/* TAPE 1 gewinnt */
	
	private int[] source1Won(int[] buffer, int runSize) throws IOException{
		writeBuf[writeBufInd] = buffer[src1Ind];
		writeBufInd++;
		checkWriteBuf();
		src1Ind++;
		runInd1++;
		if (runInd1 >= runSize){
			EoR = true;
			return new int[0];
		}
		return checkReadBuf1(buffer);
	}
	
	private int[] checkReadBuf1(int[] buffer) throws IOException{
		if (src1Ind >= Constants.READ_BUFFER ) {
			src1Ind = 0;
			return source1.readSequence(Constants.READ_BUFFER);
		}
		return buffer;
	}
	
	/* TAPE 2 gewinnt */
	
	private int[] source2Won(int[] buffer, int runSize) throws IOException{
		writeBuf[writeBufInd] = buffer[src2Ind];
		writeBufInd++;
		checkWriteBuf();
		src2Ind++;
		runInd2++;
		if (runInd2 >= runSize){
			EoR = true;
			return new int[0];
		}
		return checkReadBuf2(buffer);
	}
	
	private int[] checkReadBuf2(int[] buffer) throws IOException{
		if (src2Ind >= Constants.READ_BUFFER) {
			src2Ind = 0;
			return source2.readSequence(Constants.READ_BUFFER);
		}
		return buffer;
	}
	
	private void checkWriteBuf() throws IOException{
		if (writeBufInd >= writeBuf.length) {
			target.writeSequence(writeBuf);
			writeBuf = new int[Constants.WRITE_BUFFER];
			writeBufInd = 0;
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
