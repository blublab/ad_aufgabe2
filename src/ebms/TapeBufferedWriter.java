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
	private FileOutputStream fos		= null;
	private BufferedOutputStream bos	= null;
	private DataOutputStream dos		= null;
	
	
	TapeBufferedWriter(File f, long offset, long runLength) throws IOException{
		
		file = new RandomAccessFile(f, "rw");
		file.seek(offset);
		fos	= new FileOutputStream(f, true);
				
		bos	= new BufferedOutputStream(fos);
		dos	= new DataOutputStream(bos);
	}
	
	public void flush() throws IOException{
		dos.flush();
	}
	
	public void close() throws IOException{
		dos.close();
		bos.close();
		fos.close();
		file.close();
	}
	
	public void write(int number) throws IOException{
		dos.writeInt(number);
	}
}
