package ebms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

//import ebms.FolgenErzeuger;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File test = new File("./Files/tape8");
		FolgenErzeuger.ausgabe(test);

//		File sequence = new File("C:/Users/abj415/Desktop/sequence");
//		FolgenErzeuger.erzeuge(sequence, BigInteger.valueOf(100000000));
//		Sorter sorter = new Sorter(8, sequence);
//		File erg =  sorter.sort();
//		
//		System.out.println("BLA");
//		FolgenErzeuger.ausgabe(erg);
	}
}