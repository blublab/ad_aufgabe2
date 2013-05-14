package balancedms;

import java.io.IOException;

public class Merger<T> {
	private Tape tape1 = null;
	private Tape tape2 = null;
	private Tape tape3 = null;
	private Tape tape4 = null;
	private Tape[] targets = { null, null };
	private Tape[] sources = { null, null };

	/**
	 * Reads elements of Type T from sources (alternating with runLength),
	 * merge-sorts them into targets (alternating with 2*runLength)
	 * 
	 * @throws IOException
	 */
	public Tape mergeTapes(int runLength, Tape source1, Tape source2,
			Tape target1, Tape target2) throws IOException {
		assert (source1 != null);
		assert (source2 != null);
		assert (target1 != null);
		assert (target2 != null);
		assert (runLength > 0);

		tape1 = source1;
		tape2 = source2;
		tape3 = target1;
		tape4 = target2;
		sources = new Tape[] { source1, source2 };
		targets = new Tape[] { target1, target2 };

		while (runLength <= Constants.FOLGENLAENGE) {
			while (!(sources[0].isEoF()) && (!(sources[1].isEoF()))) {
				mergeRuns(runLength, sources[0], sources[1], targets[0]);
				flipTargets();
			}
			flipSourcesTargets();
			runLength *= 2;
		}
		return targets[1];
	}

	/* privat -> public */
	private void mergeRuns(int runLength, Tape source1, Tape source2,
			Tape target) throws IOException {
		int i = 0;
		int src1Ind = 0;
		int src2Ind = 0;
		int writeBufInd = 0;
		int[] writeBuf = new int[Constants.WRITE_BUFFER];
		int[] src1Buf = source1.readSequence(Constants.READ_BUFFER);
		int[] src2Buf = source2.readSequence(Constants.READ_BUFFER);
		while ((i < runLength * 2)
				&& (!(source1.isEoF()) && (!(source2.isEoF())))) {
			i++;

			if (src1Buf[src1Ind] < src2Buf[src2Ind]) {

				if (writeBufInd >=Constants.WRITE_BUFFER) {
					target.writeSequence(writeBuf);
					writeBuf = new int[Constants.READ_BUFFER];
					writeBufInd = 0;
					writeBuf[writeBufInd] = src1Buf[src1Ind];
				}
				writeBuf[writeBufInd] = src1Buf[src1Ind];
				writeBufInd++;
				src1Ind += 1;
				if (src1Ind == Constants.READ_BUFFER) {
					src1Buf = source1.readSequence(Constants.READ_BUFFER);
					src1Ind = 0;
				}

			} else {

				if (writeBufInd >= Constants.TAPE_BUFFER * 2) {
					target.writeSequence(writeBuf);
					writeBuf = new int[Constants.TAPE_BUFFER * 2];
					writeBufInd = 0;
					writeBuf[writeBufInd] = src2Buf[src2Ind];
				}
				writeBuf[writeBufInd] = src2Buf[src2Ind];
				writeBufInd++;
				src2Ind += 1;
				if (src2Ind == Constants.TAPE_BUFFER) {
					src2Buf = source2.readSequence(Constants.TAPE_BUFFER);
					src2Ind = 0;
				}
			}
		}
		if (source1.isEoF()) {
			while ((i < runLength * 2)
					&& !(source2.isEoF())) {
				i++;
				if (writeBufInd >= Constants.TAPE_BUFFER * 2) {
					target.writeSequence(writeBuf);
					writeBuf = new int[Constants.TAPE_BUFFER * 2];
					writeBufInd = 0;
					writeBuf[writeBufInd] = src2Buf[src2Ind];
				}
				writeBuf[writeBufInd] = src2Buf[src2Ind];
				writeBufInd++;
				src2Ind += 1;
				if (src2Ind == Constants.TAPE_BUFFER) {
					src2Buf = source2.readSequence(Constants.TAPE_BUFFER);
					src2Ind = 0;
				}
				target.writeSequence(writeBuf);
			}
		} else {
			while ((i < runLength * 2)
					&&!(source1.isEoF())) {
				i++;
				if (writeBufInd >= Constants.TAPE_BUFFER * 2) {
					target.writeSequence(writeBuf);
					writeBuf = new int[Constants.TAPE_BUFFER * 2];
					writeBufInd = 0;
					writeBuf[writeBufInd] = src1Buf[src1Ind];
				}
				writeBuf[writeBufInd] = src1Buf[src1Ind];
				writeBufInd++;
				src1Ind += 1;
				if (src1Ind == Constants.TAPE_BUFFER) {
					src1Buf = source1.readSequence(Constants.TAPE_BUFFER);
					src1Ind = 0;
				}
				target.writeSequence(writeBuf);
			}
		}
	}

	private void flipTargets() {
		Tape tmp = targets[0];
		targets[0] = targets[1];
		targets[1] = tmp;
	}

	private void flipSourcesTargets() throws IOException {
		for (Tape t : sources) {
			t.reset();
		}
		Tape[] temp = targets;
		targets = sources;
		sources = temp;
	}
}

/*
 * final int MERGE_BUFFER_SIZE = 1; for (int i = 0; i < 2 * runLength; i++) {
 * int[] src1 = source1.readSequence(MERGE_BUFFER_SIZE); int[] src2 =
 * source2.readSequence(MERGE_BUFFER_SIZE); if (src1[0] > src2[0]) { while
 * (src1[0] > src2[0]) { targets[0].writeSequence(src2); src2 =
 * source2.readSequence(MERGE_BUFFER_SIZE); i++; if (i == runLength - 1) break;
 * } } else if (src1[0] < src2[0]) { while (src1[0] < src2[0]) {
 * targets[0].writeSequence(src1); src1 =
 * source1.readSequence(MERGE_BUFFER_SIZE); i++; if (i == runLength - 1) break;
 * } } else { targets[0].writeSequence(src1); targets[0].writeSequence(src2); //
 * NICHT STABIL??? } }
 * 
 * //runlaenge verdoppeln runLength *= 2;
 * 
 * //flip sources und targets
 * 
 * }
 */