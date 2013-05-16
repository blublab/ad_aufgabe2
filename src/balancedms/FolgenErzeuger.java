package balancedms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import balancedms.tapes.Tape;

/**
 * Erzeugt eine Zufallsfolge und schreibt diese in die Datei "z:/folge.dat"
 * 
 * Nicht instanziierbar.
 * 
 * @param lowerBound untere Schranke des Intervalls
 * @param upperBound obere Schranke des Intervalls
 * @param anzahl Anzahl der Elemente
 * @throws FileNotFoundException
 * @throws IOException
*/

public class FolgenErzeuger {
	
	private static int lowerBound 	= Integer.MIN_VALUE/2;
	private static int upperBound 	= Integer.MAX_VALUE/2;
	
	private FolgenErzeuger(){};

	public static void erzeuge(Tape tape, BigInteger anzahl) throws FileNotFoundException, IOException{
		Random randomizer = new Random();
		tape.resetForWrite();

		List<Integer> temp = new ArrayList<Integer>();
		
		BigInteger eins = BigInteger.valueOf(1);
		for (BigInteger i = BigInteger.valueOf(0); !i.equals(anzahl);i =  i.add(eins)) {
			int zufallszahl = (lowerBound + randomizer.nextInt((upperBound) + randomizer.nextInt((upperBound))));
			temp.add(zufallszahl);
		}
		int bufferSize = 10;
		int [] ret = new int[bufferSize];
		int n = 0;
		for (int i = 0; i< temp.size()+1; i++){
			if(n < bufferSize){
				ret[i] = temp.get(i);
				n++;
			} else {
				tape.writeSequence(ret);
				ret = new int[bufferSize];
				n = 0;
			}
		}
	 }

	public static void ausgabe(Tape tape) throws IOException {
		tape.resetForRead();
		int[] zahlen = tape.readSequence(1000000000);
		while (zahlen.length != 0) {
			for (int n : zahlen) {
				System.out.println(n + "\t" + zahlen.length);
			}
			zahlen = tape.readSequence(1000000000);
		}
		tape.resetForRead();
	}
	

}
