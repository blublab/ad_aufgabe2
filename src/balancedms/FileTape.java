package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
	private boolean isEoF;
	private FileInputStream fis = null;
	private BufferedInputStream bis = null;
	private FileOutputStream fos = null;
	private BufferedOutputStream bos = null;

	
	FileTape(String filename) throws IOException {
		filename = "./Files/" + filename;
		File f = new File(filename);
		this.file = f;
		this.fos = new FileOutputStream(file, true);
		this.bos = new BufferedOutputStream(fos);
		this.fis = new FileInputStream(file);
		this.bis = new BufferedInputStream(fis);




	}

//	@Override
//	public int[] readSequence(int len) throws IOException {
//		if(bis.available() <= len){
//			isEoF = true;
//			len = bis.available();
//		}
//		byte[] input = new byte[SCHLUESSELGROESSE];
//		int[] seq	= new int[len];
//		//FileInputStream fis = new FileInputStream(file);
//		//BufferedInputStream bis = new BufferedInputStream(fis);
//		for (int i = 0; i<len; i++){
//			bis.read(input, 0, SCHLUESSELGROESSE);
//			int ret = 0;
//			for(byte b: input){
//				ret = ((ret<<8)&0xffffff00);
//				ret = ret | (b&0x000000ff);
//			}
//			seq[i] = ret;
//		}
//		return seq;
//	}

	@Override
	public int[] readSequence(int len) throws IOException {
		byte[] input = new byte[Constants.SCHLUESSELGROESSE];
		int[] seq = null;

		int avail = 0;

		avail = bis.available() / 4;

		if (avail < len){
			isEoF = true;
			len = avail;
		}
		seq = new int[len];

		for (int i = 0; i < len; i++) {
			if (bis.read(input) != -1) {
				int ret = 0;
				for (byte b : input) {
					ret = ((ret << 8) & 0xffffff00);
					ret = ret | (b & 0x000000ff);
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
		byte[] temp = new byte[SCHLUESSELGROESSE];
		for (int i : seq) {
			temp = convertIntToByteArray(i);
			bos.write(temp);
		}
		bos.flush();
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
	
	public void resetForWrite() throws IOException {
		bos.close();
		fos.close();
		FileWriter fwTemp = new FileWriter(file);
		fwTemp.write("");
		fwTemp.close();
		fos = new FileOutputStream(file, true);
		bos = new BufferedOutputStream(fos);
	}
	
	public void resetForRead() throws IOException {
		bis.close();
		fis.close();
		fis = new FileInputStream(file);
		bis = new BufferedInputStream(fis);
		isEoF = true;
		if(bis.available() > 0){ 		//Optimieren?????
			isEoF = false;
		}
	}
	
	public String toString(){
		return file.toString();
	}
	
	public void setEoF(boolean b){
		isEoF = b;
	}
	
	public File getFile(){
		return file;
	}
	
	public void close() throws IOException{
		bos.close();
		fos.close();
		bis.close();
		fos.close();
	}
}