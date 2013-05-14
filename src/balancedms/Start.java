package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import balancedms.algorithm.Merger;
import balancedms.controls.Constants;
import balancedms.helper.FolgenErzeuger;
import balancedms.io.FileTape;
import balancedms.io.Tape;
import static balancedms.controls.Constants.*;

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
		FolgenErzeuger.ausgabe(tape2);
		
		System.out.print("Beginne Folgenerzeugung\t\t");
		FolgenErzeuger.erzeuge(tape1, Constants.FOLGENLAENGE);
		System.out.println("abgeschlossen");
		
		Merger m = new Merger();
		
		System.out.print("Befülle StartTapes (MemSort)\t");
		m.initialize(tape1, tape3, tape4);
		System.out.println("abgeschlossen");
		
		System.out.print("Starte Mergen der Tapes\t\t");
		m.mergeTapes(MEMSORT_BUFFER, tape3, tape4, tape1, tape2);
		
	}

}
