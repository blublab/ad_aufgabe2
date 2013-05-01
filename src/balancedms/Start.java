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
		FolgenErzeuger.ausgabe(tape1);
	}

}
