package ebms;
import java.awt.Container;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TapeBufferedReader {
	private	RandomAccessFile file;
	private long runOffset;
	private long actualRunSize;
	private FileInputStream fis		= null;
	private BufferedInputStream bis	= null;
	private DataInputStream	dis		= null;
	
	private int[] buffer				= new int[Constants.READ_BUFFER_SIZE];
	private int currentChunkOffset	= 0;
	
	TapeBufferedReader(File f, long offset, long runSize) throws IOException {
		this.file		= new RandomAccessFile(f, "r");
		this.runOffset	= offset;
		
		//seek to the actual start of run -> check if correct
		//file.seek(offset);
		this.fis		= new FileInputStream(f);
		this.bis		= new BufferedInputStream(fis);
		this.dis		= new DataInputStream(bis);
		dis.skipBytes((int) (offset*4));
		actualRunSize = Math.min(dis.available()/4, runSize);

	}
	
	public boolean isAvailable() throws IOException{
		//return file.length() - runOffset > 0;
		return actualRunSize > 0;
	}
	
//	/**
//	 * Stelle sicher, dass die gewuenschte Position im Buffer ist 
//	 * @throws IOException 
//	 */
//	private void provideChunkAt(int pos) throws IOException{
//		if (!(pos < currentChunkOffset || pos >= currentChunkOffset + buffer.length)){
//			return;
//		}
//		
//		currentChunkOffset = (pos / Constants.READ_BUFFER_SIZE) * Constants.READ_BUFFER_SIZE;
//		
//		for (int i = 0; i < Constants.READ_BUFFER_SIZE; ++i){
//			buffer[i]	= dis.readInt();
//		}
//	}

	/**
	 * Liefert Iterator ueber den Run des Buffers
	 * @return
	 */
	TapeIterator iterator(){
		
		return new TapeIterator(){
			private int number;
			private int pos	= 0;

			@Override
			public int next() throws IOException {
				number = dis.readInt();
				assert(pos<=actualRunSize);
				pos++;
				return number;
			}

			@Override
			public boolean hasNext() {
				return pos < actualRunSize;
			}

			@Override
			public int peek() {
				return number;
			}	
		};
	}
}