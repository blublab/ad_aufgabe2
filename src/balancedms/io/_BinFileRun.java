package balancedms.io;

import static balancedms.controls.Constants.SCHLUESSELGROESSE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

import balancedms.controls.Constants;

public class _BinFileRun implements _Run {
	
	private File file					= null;
	private FileInputStream fis 		= null;
	private BufferedInputStream bis 	= null;
	private FileOutputStream fos 		= null;
	private BufferedOutputStream bos 	= null;
	private boolean isEoF				= false;
	private long read					= 0;
	private long written				= 0;
	
	public _BinFileRun() throws FileNotFoundException{
		UUID id = UUID.randomUUID();
		file = new File("./Files/" + id);
		this.fos = new FileOutputStream(file, true);
		this.bos = new BufferedOutputStream(fos);
		this.fis = new FileInputStream(file);
		this.bis = new BufferedInputStream(fis);
	}

	@Override
	public void add(int[] seq) throws IOException {
		byte[] temp = new byte[SCHLUESSELGROESSE];
		for (int i : seq) {
			temp = convertIntToByteArray(i);
			bos.write(temp);
		}
		written += seq.length;
		bos.flush(); // Fuer Effizienz entfernen??
	}
	

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				return (written - read) > 0;
			}

			//TODO GAAANZ SCHLIMM, hier muss dringend ein fetter Buffer hin
			@Override
			public Integer next() {
				int[] temp = null;
				try {
					temp = readSequence(1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return temp[0];
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				assert(false); // not implemented
			}
		};
	}
	
//	private void writeSequence(int[] seq) throws IOException {
//		byte[] temp = new byte[SCHLUESSELGROESSE];
//		for (int i : seq) {
//			temp = convertIntToByteArray(i);
//			bos.write(temp);
//		}
//		written += seq.length;
//		bos.flush(); // Fuer Effizienz entfernen??
//	}
	
	private int[] readSequence(int len) throws IOException {
		byte[] input = new byte[Constants.SCHLUESSELGROESSE];
		int[] seq = null;
		int avail = 0;

		avail = bis.available() / 4;
		if (avail < len)
			len = avail;
		seq = new int[len];

		for (int i = 0; i < len; i++) {
			bis.read(input);
			int ret = 0;
			for (byte b : input) {
				ret = ((ret << 8) & 0xffffff00);
				ret = ret | (b & 0x000000ff);
			}
			seq[i] = ret;
		}
		read += seq.length;
		return seq;
	}

	@Override
	public void print() {

	}
	
	public String toString(){
		return file.toString();
	}
	
	// Thomas Darimont @ http://www.tutorials.de/java/228129-konvertierung-von-integer-byte-array.html
	private static byte[] convertIntToByteArray(int val) {
        byte[] buffer = new byte[4];
   
        buffer[0] = (byte) (val >>> 24);
        buffer[1] = (byte) (val >>> 16);
        buffer[2] = (byte) (val >>> 8);
        buffer[3] = (byte) val;
        
        return buffer;
	}
	
}
