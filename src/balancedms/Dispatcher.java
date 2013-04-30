package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dispatcher {

	public static final int RUN_LENGTH = 1024;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		
		FileOutputStream fos1 = new FileOutputStream("./RawTapes/tape1");
		BufferedOutputStream bos1 = new BufferedOutputStream(fos1);
		FileOutputStream fos2 = new FileOutputStream("./RawTapes/tape2");
		FileOutputStream fos3 = new FileOutputStream("./RawTapes/tape3");
		FileOutputStream fos4 = new FileOutputStream("./RawTapes/tape4");

		int data = 123;

		bos1.write(data);
		bos1.flush();
		bos1.close();

		FileInputStream fis1 = new FileInputStream("./RawTapes/tape1");
		BufferedInputStream bis1 = new BufferedInputStream(fis1);
//		FileInputStream fis2 = new FileInputStream("./RawTapes/tape2");
//		FileInputStream fis3 = new FileInputStream("./RawTapes/tape3");
//		FileInputStream fis4 = new FileInputStream("./RawTapes/tape4");

		data = bis1.read();
		System.out.println(data);
		bis1.close();
		
		//erzeuge file blalba
		
		//lese und MemSorte  und schreibe RL Ts, alterniere Output
		
		//MergeSorte tapes alternierend
		fos1.close();
		fos2.close();
		fos3.close();
		fos4.close();
	}

}
