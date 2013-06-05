package ebms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

//import ebms.FolgenErzeuger;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File sequence = new File("./Files/sequence.dat");
		FolgenErzeuger.erzeuge(sequence, BigInteger.valueOf(63));
		Sorter sorter = new Sorter(4, sequence);
		File erg =  sorter.sort();
		FolgenErzeuger.ausgabe(sequence);
		
		System.out.println("BLA");
		FolgenErzeuger.ausgabe(erg);
	}
}