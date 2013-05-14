package balancedms;

import java.io.IOException;

public class Merger2 {
	
	private int writeBufInd = 0;
	private int[] writeBuf = new int[Constants.WRITE_BUFFER];
	private Tape source1 = null;
	private int src1Ind = 0;
	private Tape source2 = null;
	private int src2Ind = 0;
	private Tape target = null;

	
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
	}
	
	public void mergen(int runSize, Tape tape1, Tape tape2, Tape tape3, Tape tape4) throws IOException{
		Tape[] sources = new Tape[] { tape1, tape2 };
		Tape[] targets = new Tape[] { tape3, tape4 };
		for(Tape t: targets){
			t.setWritable(true);
		}
		
		while(runSize <= Constants.FOLGENLAENGE){
			runSize *= 2;
			mergeRun(runSize, sources[0], sources[1], targets[0]);
			targets = flipTargets(targets);
			if(sources[0].isEoF() && sources[1].isEoF()){
				Tape[] temp = sources;
				sources = flipTapes(targets);
				targets = flipTapes(temp);
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
		while(runInd <= runSize){
			System.out.println(src1Ind);
			System.out.println(src2Ind);
			if (src1Buf[src1Ind] <= src2Buf[src2Ind]) {
				src1Buf = source1Won(src1Buf);
			} else {
				src2Buf = source2Won(src2Buf);
			}
			if(source1.isEoF()){
				while((runInd <= runSize) && !(source2.isEoF())){
					src2Buf = source2Won(src2Buf);
					runInd++;
				}
			}
			if(source2.isEoF()){
				while((runInd <= runSize) && !(source1.isEoF())){
					src1Buf = source1Won(src1Buf);
					runInd++;
				}
			}
			runInd++;
		}
		target.writeSequence(writeBuf);
	}
	
	/* TAPE 1 gewinnt */
	
	private int[] source1Won(int[] buffer) throws IOException{
		checkWriteBuf();
		writeBuf[writeBufInd] = buffer[src1Ind];
		writeBufInd++;
		src1Ind++;
		return checkReadBuf1(buffer);
	}
	
	private int[] checkReadBuf1(int[] buffer) throws IOException{
		if (src1Ind == Constants.READ_BUFFER) {
			src1Ind = 0;
			return source1.readSequence(Constants.READ_BUFFER);
		}
		return buffer;
	}
	
	/* TAPE 2 gewinnt */
	
	private int[] source2Won(int[] buffer) throws IOException{
		checkWriteBuf();
		writeBuf[writeBufInd] = buffer[src2Ind];
		writeBufInd++;
		src2Ind++;
		return checkReadBuf2(buffer);
	}
	
	private int[] checkReadBuf2(int[] buffer) throws IOException{
		if (src2Ind == Constants.READ_BUFFER) {
			src2Ind = 0;
			return source2.readSequence(Constants.READ_BUFFER);
		}
		return buffer;
	}
	
	private void checkWriteBuf() throws IOException{
		if (writeBufInd >=Constants.WRITE_BUFFER) {
			target.writeSequence(writeBuf);
			writeBuf = new int[Constants.READ_BUFFER];
			writeBufInd = 0;
		}
	}
	
	private Tape[] flipTargets(Tape[] targets){
		Tape[] ret = new Tape[2];
		ret[0] = targets[1];
		ret[1] = targets[0];
		return ret;
	}
	
	private Tape[] flipTapes(Tape[] tapes) throws IOException{
		Tape[] ret = new Tape[tapes.length];
		int i = 0;
		for(Tape t: tapes){
//			t.setWritable(!(t.isWritable()));
			t.reset();
			ret[i] = t;
			i++;
		}
		return ret;
	}

}
