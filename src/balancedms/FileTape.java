package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

	@Override
	public int[] readSequence(int len) throws IOException {
		if(bis.available() <= len){
			isEoF = true;
			len = bis.available();
		}
		byte[] input = new byte[SCHLUESSELGROESSE];
		int[] seq	= new int[len];
		//FileInputStream fis = new FileInputStream(file);
		//BufferedInputStream bis = new BufferedInputStream(fis);
		for (int i = 0; i<len; i++){
			if (bis.read(input, 0, SCHLUESSELGROESSE) != -1){
				int ret = 0;
				for(byte b: input){
					ret = ((ret<<8)&0xffffff00);
					ret = ret | (b&0x000000ff);
				}
				seq[i] = ret;
			} else {
				//ToDo Auf verbleibende Bytes pruefen und ggf. Rueckgabearraylaenge anpassen. o.ae.
				isEoF = true;
				int[] temp = new int[i];
				int[] temp2 = new int[1];
				temp2 = seq;
				return temp2;
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
	
	public void reset() throws IOException{
		isEoF = false;
		bos.close();
		fos.close();
		FileWriter fwTemp = new FileWriter(file);
		fwTemp.write("");
		fwTemp.close();
		fos = new FileOutputStream(file, true);
		bos = new BufferedOutputStream(fos);
	}
}