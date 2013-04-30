package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTape implements Tape {

	private File file;
	private int buffersize = 1024;
	private boolean isWritable = true;
	private boolean isEoF;
	private final int zahlengroesse = 4;

	FileTape(String filename) {
		filename = "./Files/" + filename;
		File f = new File(filename);
		this.file = f;
	}

	@Override
	public int[] readSequence(int len) throws IOException {
		byte[] input = new byte[zahlengroesse];
		int[] seq	= new int[len];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		for (int i = 0; i<len; i++){
			if (bis.read(input, 0, zahlengroesse)!=-1){
				int ret = 0;
				for(byte b: input){
					ret = ((ret<<8)&0xffffff00);
					ret = ret | (b&0x000000ff);
				}
				seq[i] = ret;
				} else {
					//ToDo Auf verbleibende Bytes pruefen und ggf. Rueckgabearraylaenge anpassen. o.ae.
					fis.close();
					isEoF = true;
					int[] temp = new int[i];
					int[] temp2 = new int[1];
					temp2 = seq;
					return temp2;
				}
		}
		fis.close();
		return seq;
	}

	@Override
	public void writeSequence(int[] seq) throws IOException {
		if (isWritable) {
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] temp = new byte[zahlengroesse];
			for (int i : seq) {
				temp = convertIntToByteArray(i);
				bos.write(temp);
			}
			bos.close();
		}
	}

	@Override
	public boolean isWritable() {
		return isWritable;
	}
	
	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}



	@Override
	public boolean isEoF() {
		file.length();
		return isEoF;
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