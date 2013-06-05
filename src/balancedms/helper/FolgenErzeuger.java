package balancedms.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import balancedms.io._Tape;

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

	public static void erzeuge(_Tape oldTape, int anzahl) throws FileNotFoundException, IOException{
		Random randomizer = new Random();
//		File datei = new File("./Files/" + tape);
//		FileOutputStream fos = new FileOutputStream(datei);
//		BufferedOutputStream bos = new BufferedOutputStream(fos);
		oldTape.resetForWrite();

		int[] temp = new int[anzahl];
		for (int i=0;i<anzahl;i++) {
			int zufallszahl = (lowerBound + randomizer.nextInt((upperBound) + randomizer.nextInt((upperBound))));
			//System.out.println("X: "+ zufallszahl);
			temp[i] = zufallszahl;
		}
		oldTape.writeSequence(temp);
	 }
	
	public static void ausgabe(_Tape oldTape) throws IOException{
		oldTape.resetForRead();
		int[] zahlen = oldTape.readSequence(16);
		while(zahlen.length != 0){
			for(int n: zahlen){
				System.out.println(n + "\t" + zahlen.length);
			}
			zahlen = oldTape.readSequence(16);
		}
	}
	

}
