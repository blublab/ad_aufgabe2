package ebms;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TapeBufferedWriter {
	private	RandomAccessFile file;
	private long runOffset;
	private long runSize;
	private FileOutputStream fos		= null;
	private BufferedOutputStream bos	= null;
	private DataOutputStream dos		= null;
	
	private int[] buffer				= new int[Constants.WRITE_BUFFER_SIZE];
	private int currentChunkOffset	= 0;
	
	TapeBufferedWriter(File f, long offset) throws IOException{
		file.seek(offset);
		fos	= new FileOutputStream(f);
				
		bos	= new BufferedOutputStream(fos);
		dos	= new DataOutputStream(bos);
		runOffset	= offset;
	}
	
	public void write(int number) throws IOException{
		dos.writeInt(number);
	}
}
