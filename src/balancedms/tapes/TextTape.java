package balancedms.tapes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextTape implements Tape {
	private File file;
	FileWriter fw = null;
	FileReader fr = null;
	BufferedReader br = null;
	BufferedWriter bw = null;

	// FileWriter fw = new FileWriter(file, true);

	public TextTape(String filename) throws IOException {
		this.file = new File(filename);
		this.file.setWritable(true);
		fw = new FileWriter(file, true);
		fr = new FileReader(file);
		br = new BufferedReader(fr);
		bw = new BufferedWriter(fw);
	}

	@Override
	public int[] readSequence(int len) throws IOException {
		String str = null;
		int count = 0;

		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			if ((str = br.readLine()) == null) {
				break;
			}
			int temp = Integer.parseInt(str);
			count++;
			ret[i] = temp;
		}
		int[] tempRet = new int[count];
		for (int i = 0; i < count; i++) {
			tempRet[i] = ret[i];
		}
		return tempRet;
	}

	@Override
	public String toString() {
		return file.toString();
	}

	@Override
	public void print() throws IOException {
		resetForRead();
		int[] zahlen = readSequence(32);
		while (zahlen.length != 0) {
			for (int n : zahlen) {
				System.out.println(n + "\t" + zahlen.length);
			}
			zahlen = readSequence(32);
		}
		resetForRead();
	}

	@Override
	public void writeSequence(int[] seq) throws IOException {
		for (int i : seq) {
			bw.write(Integer.toString(i));
			bw.write("\n");
			bw.flush();
		}
	}

	@Override
	public void resetForRead() throws IOException {
		br.close();
		fr.close();
		fr = new FileReader(file);
		br = new BufferedReader(fr);
	}

	@Override
	public void resetForWrite() throws IOException {
		bw.close();
		fw.close();
		fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		bw.write("");
	}

	@Override
	public boolean isEoF() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEoF(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}