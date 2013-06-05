package ebms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorter {
	private List<File> group1Tapes = new ArrayList<File>();
	private List<File> group2Tapes = new ArrayList<File>();

	Sorter(int numberTapes, String fileWithSequence) throws IOException {
		initialize(numberTapes, fileWithSequence);
	}

	private void initialize(int numberTapes, String fileWithSequence)
			throws IOException {
		FileInputStream fis = new FileInputStream(fileWithSequence);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		FileOutputStream fos = new FileOutputStream(fileWithSequence);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);

		// tapes erstellen
		for (int i = 0; i < numberTapes; i++) {
			File file = new File("./Files/tape" + i);
			file.createNewFile();
			group1Tapes.add(file);
		}

		for (int i = 0; i < numberTapes; i++) {
			File file = new File("./Files/tape" + i);
			file.createNewFile();
			group2Tapes.add(file);
		}

		// vorsortieren
		List<DataOutputStream> oStreams = new ArrayList<DataOutputStream>();
		for (File f: group1Tapes){
			oStreams.add(new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f))));
		}
		
		int[] buffer = new int[Constants.MEMSORT_BUFFER_SIZE];
		int index = 0;
		while (dis.available() != 0) {
			for (int i = 0; i < Constants.MEMSORT_BUFFER_SIZE; i++) {
				buffer[i] = dis.readInt();
			}
			Arrays.sort(buffer);
			for (int i = 0; i < Constants.MEMSORT_BUFFER_SIZE; i++) {
				oStreams.get(index%numberTapes).writeInt(buffer[i]);
			}
			
		}
	}

	private void sort() {
		
	}

}
