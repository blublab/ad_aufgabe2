package ebms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorter {
	private List<File> group1Tapes = new ArrayList<File>();
	private List<File> group2Tapes = new ArrayList<File>();
	private int numberTapes	= 0;
	private int runLength	= 0;
	private final long sequenceLength;

	Sorter(int numberTapes, File fileWithSequence) throws IOException {
		runLength = Constants.MEMSORT_BUFFER_SIZE;
		RandomAccessFile sequence = new RandomAccessFile(fileWithSequence, "r");
		this.numberTapes = numberTapes;
		sequenceLength = sequence.length() / 4;
		initialize(fileWithSequence);
	}

	private void initialize(File fileWithSequence)
			throws IOException {
		FileInputStream fis = new FileInputStream(fileWithSequence);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);

		// tapes erstellen
		for (int i = 0; i < numberTapes; i++) {
			File file = new File("./Files/tape" + i);
			file.createNewFile();
			group1Tapes.add(file);
		}

		for (int i = 0; i < numberTapes; i++) {
			File file = new File("./Files/tape" + (i + numberTapes));
			file.createNewFile();
			group2Tapes.add(file);
		}

		// vorsortieren
		List<DataOutputStream> oStreams = new ArrayList<DataOutputStream>();
		for (File f: group1Tapes){
			oStreams.add(new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f))));
		}
		
		int[] buffer = new int[Constants.MEMSORT_BUFFER_SIZE];
		long index = 0;
		int tapeIndex = 0;
		int lastCount = 0;
		while (index < sequenceLength) {
			for (int i = 0; i < Constants.MEMSORT_BUFFER_SIZE; i++) {
				if (index < sequenceLength) {
					buffer[i] = dis.readInt();
					index++;
					lastCount++;
				}
			}
			Arrays.sort(buffer);
			for (int i = 0; i < lastCount; i++) {
				oStreams.get(tapeIndex % numberTapes).writeInt(buffer[i]);
			}
			tapeIndex++;
			lastCount = 0;
		}
		for (DataOutputStream dos: oStreams){
			dos.close();
		}
//		System.out.println("Tape0");
//		FolgenErzeuger.ausgabe(group1Tapes.get(0));
//		System.out.println("Tape1");
//		FolgenErzeuger.ausgabe(group1Tapes.get(1));
//		System.out.println("Tape2");
//		FolgenErzeuger.ausgabe(group1Tapes.get(2));
//		System.out.println("Tape3");
//		FolgenErzeuger.ausgabe(group1Tapes.get(3));
//		System.out.println("------------------------");
	}

	public File sort() throws IOException {
		List<File> sources = group1Tapes;
		List<File> targets = group2Tapes;
		long tupel = sources.get(0).length() / runLength /4;
		if (sources.get(0).length() % runLength != 0)
			tupel++;
		
		long tupelGesamt = sequenceLength / numberTapes /runLength;
		if(tupelGesamt % numberTapes != 0)
			tupelGesamt++;
		for(int j = 0; j <= tupelGesamt; j++){
			for (File f: targets){
				f.delete();
				f.createNewFile();
			}
			for (int k = 0; k < tupel; k++) {
				List<TapeIterator> runIterators = new ArrayList<TapeIterator>();

				// initialisiere BufferedReader mit passendem Offset und
				// Runlänge

				TapeBufferedWriter bw = new TapeBufferedWriter(targets.get(k
						% targets.size()), (k / numberTapes)
						* (numberTapes * runLength), runLength);

				// initialisiere BufferedReader
				for (File f : sources) {
					TapeBufferedReader br = new TapeBufferedReader(f, k
							* runLength, runLength);
					if (br.isAvailable())
						runIterators.add(br.iterator());
				}

				// finde kleinstes Element
				int minValue = Integer.MAX_VALUE;
				int indexOfMinValue = 0;

				for (TapeIterator it : runIterators) {
					it.next();
				}

				while (runIterators.size() != 0) {
					minValue = Integer.MAX_VALUE;

					for (int i = 0; i < runIterators.size(); i++) {
						if (runIterators.get(i).peek() <= minValue) {
							minValue = runIterators.get(i).peek();
							indexOfMinValue = i;
						}
					}
					// schreibe weg
					bw.write(minValue);
					//System.out.println("Schreibe " + minValue);

					if (runIterators.get(indexOfMinValue).hasNext()) {
						runIterators.get(indexOfMinValue).next();
					} else {
						runIterators.remove(indexOfMinValue);
					}
				}
				bw.flush();
				bw.close();
			}

			// neue runLength
			runLength *= numberTapes;

			// Swap sources/targets
			List<File> temp = new ArrayList<File>();
			temp = sources;
			sources = targets;
			targets = temp;

			// neue Anzahl Runs
			tupel = (tupel % numberTapes == 0) ? tupel / numberTapes : (tupel
					/ numberTapes) + 1;
			
			tupelGesamt = (tupelGesamt % numberTapes == 0) ? tupelGesamt / numberTapes : (tupelGesamt
					/ numberTapes) + 1;
		}
//		System.out.println("Tape0");
//		FolgenErzeuger.ausgabe(group1Tapes.get(0));
//		System.out.println("Tape1");
//		FolgenErzeuger.ausgabe(group1Tapes.get(1));
//		System.out.println("Tape4");
//		FolgenErzeuger.ausgabe(group2Tapes.get(0));
//		System.out.println("-------------------");
		return sources.get(0);
	}
}
