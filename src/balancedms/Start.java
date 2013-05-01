package balancedms;

import java.io.IOException;

public class Start {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FolgenErzeuger folge = new FolgenErzeuger();
		Tape tape1 = new FileTape("tape1.dat");
		folge.zufallsFolge(tape1, Constants.FOLGENLAENGE);
		folge.ausgabe(tape1);
		
	}

}
