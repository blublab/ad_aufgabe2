package balancedms;

import java.io.IOException;
import static balancedms.Constants.*;

public class Start {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Tape tape1 = new FileTape("tape1.dat");
		FolgenErzeuger.erzeuge(tape1, FOLGENLAENGE);
		
		System.out.println("==================== Folge ====================");
		FolgenErzeuger.ausgabe(tape1);
		

		Tape tape2 = new FileTape("tape2.dat");
		Tape tape3 = new FileTape("tape3.dat");
		Tape tape4 = new FileTape("tape4.dat");

		Merger2 m = new Merger2();
		m.initialize(tape1, tape3, tape4);
		
		System.out.println("==================== Tape3 ====================");

		FolgenErzeuger.ausgabe(tape3);
		
		System.out.println("==================== Tape4 ====================");

		FolgenErzeuger.ausgabe(tape4);
		

		
		m.mergen(MEMSORT_BUFFER, tape3, tape4, tape1, tape2);
		

		System.out.println("====================  ====================");
		
		System.out.println("====================  ====================");

		
		System.out.println("====================  ====================");

		
		System.out.println("====================  ====================");

		
		
		
		

		System.out.println("==================== Tape1 ====================");

		FolgenErzeuger.ausgabe(tape1);
		System.out.println("==================== Tape2 ====================");

		FolgenErzeuger.ausgabe(tape2);
		System.out.println("==================== Tape3 ====================");

		FolgenErzeuger.ausgabe(tape3);
		System.out.println("==================== Tape4 ====================");

		FolgenErzeuger.ausgabe(tape4);
	
		
	}

}
