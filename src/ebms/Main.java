package ebms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

//import ebms.FolgenErzeuger;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
//		File test = new File("./Files/tape8");
//		FolgenErzeuger.ausgabe(test);
		
		int folgenLaenge = 8192 * (1000000 / 4);
		if (args.length > 0) {
		    try {
		        folgenLaenge = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + " must be an integer");
		        System.exit(1);
		    }
		}

		File sequence = new File(Constants.fileRoot + "sequence");
		System.out.println("Erzeuge Sequenz der Laenge " + folgenLaenge);
		FolgenErzeuger.erzeuge(sequence, BigInteger.valueOf(folgenLaenge));
		System.out.println("Beginne Sortierung");
		long startTime = System.currentTimeMillis();
		Sorter sorter = new Sorter(4, sequence);
		File erg =  sorter.sort();
		long endTime = System.currentTimeMillis();
		System.out.println("abgeschlossen nach " + (endTime - startTime) + "ms");
		//FolgenErzeuger.ausgabe(erg);
	}
}