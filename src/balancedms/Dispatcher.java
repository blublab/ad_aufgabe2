package balancedms;

import java.io.IOException;

public class Dispatcher {

	public static final int RUN_LENGTH = 1024;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FolgenErzeuger folge = new FolgenErzeuger();
		Tape tape1 = new FileTape("tape1.dat");
		folge.zufallsFolge(tape1, 4);
		folge.ausgabe(tape1);
		
	}

}
