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
		m.initialize(tape1, tape2, tape3);
		
		System.out.println("==================== Teil1 ====================");

		FolgenErzeuger.ausgabe(tape2);
		
		System.out.println("==================== Teil2 ====================");

		FolgenErzeuger.ausgabe(tape3);
		


		
		m.mergen(4, tape2, tape3, tape4, tape1);
		
	}

}
