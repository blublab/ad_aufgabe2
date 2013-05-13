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
//		Tape tape1 = new FileTape("tape1.dat");
//		//FolgenErzeuger.erzeuge(tape1, FOLGENLAENGE);
//		tape1.writeSequence(new int[]{10,13,503});
//		//FolgenErzeuger.ausgabe(tape1);
//		tape1.writeSequence(new int[]{667});
////		FolgenErzeuger.ausgabe(tape1);
//		
////		int[] i1 = tape1.readSequence(2);
////		System.out.println(i1);
////		i1 = tape1.readSequence(2);
////		System.out.println(i1);
//		tape1.reset();
//		System.out.print("\n");
//		tape1.writeSequence(new int[]{10545465});
//		FolgenErzeuger.ausgabe(tape1);
		
		Tape tape1 = new FileTape("tape1.dat");
//		tape1.writeSequence(new int[]{10,13,503,1000});
		FolgenErzeuger.erzeuge(tape1, FOLGENLAENGE/2);
		
		Tape tape2 = new FileTape("tape2.dat");
//		tape2.writeSequence(new int[]{2,23,700,900});		
		FolgenErzeuger.erzeuge(tape2, FOLGENLAENGE/2);

		
		Tape tape3 = new FileTape("tape3.dat");
		Tape tape4 = new FileTape("tape4.dat");

		

		Merger<Tape> m = new Merger<Tape>();
		m.mergeTapes(2, tape1, tape2, tape3, tape4);
		

		FolgenErzeuger.ausgabe(tape3);

		
		//Runs einlesen und sortieren
		
		//abwechselnd auf zwei Tapes zurueckschreiben
		
		//die Tapes auf die beiden anderen (abwechselnd) mergen
		
		//merken wenn die einzelnen Tapes durchsortiert sind, dann auf eins der anderen Tapes mergen
		
		//fertig
	}

}
