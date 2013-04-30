package balancedms;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTape implements Tape {

	private String filename;
	private int offset;

	FileTape(String filename) {
		filename = filename;
	}

	@Override
	public int[] readSequence(int length) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeSequence(int[] seq) throws IOException {
		FileOutputStream fos1 = new FileOutputStream(filename);
		BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
		
		int i = 0;
		while(i<seq.length){
			bos1.write(seq[i]);
			i++;
		}
		bos1.flush();
		bos1.close();
	}
}