package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static balancedms.Constants.*;

/** Repraesentation eines Tapes in Form einer Datei
 * 
 * @author m215025
 *
 */
public class FileTape implements Tape {

	private File file;
	private boolean isWritable = true;
	private boolean isEoF;
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;
	private int offset = 0;
	//FileWriter fw = null;
	//BufferedWriter bw = null;
	
	FileTape(String filename) throws IOException {
		filename = "./Files/" + filename;
		File f = new File(filename);
		this.file = f;
		//fw = new FileWriter(file, true);
		//bw = new BufferedWriter(fw);
		this.fos = new FileOutputStream(file, true);
		this.bos = new BufferedOutputStream(fos);
		this.fis = new FileInputStream(file);
		this.bis = new BufferedInputStream(fis);




	}

	
//	@Override
//	public int[] readSequence(int len) throws IOException {
//		byte[] input = new byte[SCHLUESSELGROESSE];
//		int[] seq	= new int[len];
//		for (int i = 0; i<len; i++){
//			if (bis.read(input) != -1){
//				int ret = 0;
//				for(byte b: input){
//					ret = ((ret<<8)&0xffffff00);
//					ret = ret | (b&0x000000ff);
//				}
//				seq[i] = ret;
//				} else {
//					//ToDo Auf verbleibende Bytes pruefen und ggf. Rueckgabearraylaenge anpassen. o.ae.
//					isEoF = true;
////					int[] temp = new int[i];
////					int[] temp2 = new int[1];
////					temp2 = seq;
//					return new int[0];
//				}
//		}
//		return seq;
//	}
	
	
	@Override
	public int[] readSequence(int len) throws IOException {
		byte[] input = new byte[Constants.SCHLUESSELGROESSE];
		int[] seq	= null;
		
		int avail = 0;
		
		avail = bis.available()/4;
		
		if (avail < len) len = avail;
		
		seq = new int[len];
		
		for (int i = 0; i<len; i++){
			if (bis.read(input) != -1){
				int ret = 0;
				for(byte b: input){
					ret = ((ret<<8)&0xffffff00);
					ret = ret | (b&0x000000ff);
				}
				seq[i] = ret;
				} else {
					isEoF = true;
				}
		}
		return seq;
	}

	@Override
	public void writeSequence(int[] seq) throws IOException {
		if (isWritable) {
			//FileOutputStream fos = new FileOutputStream(file);
			//BufferedOutputStream bos = new BufferedOutputStream(fos);
			byte[] temp = new byte[SCHLUESSELGROESSE];
			for (int i : seq) {
				temp = convertIntToByteArray(i);
				bos.write(temp);
				//FileWriter fw = new FileWriter(file, true);
				//BufferedWriter bw = new BufferedWriter(fw);
				//bos.write
			}
			bos.flush();
		}
	}

	public boolean isWritable() {
		return isWritable;
	}
	
	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}


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
	
	public void resetForWrite() throws IOException{
		bos.close();
		fos.close();
		FileWriter fwTemp = new FileWriter(file);
		fwTemp.write("");
		fwTemp.close();
		fos = new FileOutputStream(file, true);
		bos = new BufferedOutputStream(fos);
	}
	
	public void resetForRead() throws IOException{
		bis.close();
		fis.close();
		offset = 0;
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
	}
}