package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static balancedms.Constants.*;

public class Start {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("----Ausgeglichenes 4-Wege MergeSort----");
		
		System.out.print("Erzeuge Tapes\t\t\t");
		Tape tape1 = new FileTape("tape1.dat");
		Tape tape2 = new FileTape("tape2.dat");
		Tape tape3 = new FileTape("tape3.dat");
		Tape tape4 = new FileTape("tape4.dat");
		System.out.println("abgeschlossen");
		
		System.out.print("Beginne Folgenerzeugung\t\t");
		FolgenErzeuger.erzeuge(tape1, Constants.FOLGENLAENGE);
		System.out.println("abgeschlossen");
		FolgenErzeuger.ausgabe(tape1);
		
		Merger m = new Merger();
		
		System.out.print("Befülle StartTapes (MemSort)\t\t");
		m.initialize(tape1, tape3, tape4);
		System.out.println("abgeschlossen");
		FolgenErzeuger.ausgabe(tape3);
		System.out.print("\n");
		FolgenErzeuger.ausgabe(tape4);
		
		System.out.print("Starte Mergen der Tapes\t\t");
		m.mergeTapes(MEMSORT_BUFFER, tape3, tape4, tape1, tape2);
		
		
		
////		Tape tape1 = new FileTape("tape1.dat");
////		//FolgenErzeuger.erzeuge(tape1, FOLGENLAENGE);
////		tape1.writeSequence(new int[]{10,13,503});
////		//FolgenErzeuger.ausgabe(tape1);
////		tape1.writeSequence(new int[]{667});
//////		FolgenErzeuger.ausgabe(tape1);
////		
//////		int[] i1 = tape1.readSequence(2);
//////		System.out.println(i1);
//////		i1 = tape1.readSequence(2);
//////		System.out.println(i1);
////		tape1.reset();
////		System.out.print("\n");
////		tape1.writeSequence(new int[]{10545465});
////		FolgenErzeuger.ausgabe(tape1);
//		
//		Tape tape1 = new FileTape("tape1.dat");
//		tape1.writeSequence(new int[]{10,13,503,1000});
//		
//		Tape tape2 = new FileTape("tape2.dat");
//		tape2.writeSequence(new int[]{9,14,303,1023});
//		
//		Tape tape3 = new FileTape("tape3.dat");
//
//		Merger<Tape> m = new Merger<Tape>();
//		m.mergeRuns(2, tape1, tape2, tape3);
//		
//
//		FolgenErzeuger.ausgabe(tape3);

		
		//Runs einlesen und sortieren
		
		//abwechselnd auf zwei Tapes zurueckschreiben
		
		//die Tapes auf die beiden anderen (abwechselnd) mergen
		
		//merken wenn die einzelnen Tapes durchsortiert sind, dann auf eins der anderen Tapes mergen
		
		//fertig
	}

}
