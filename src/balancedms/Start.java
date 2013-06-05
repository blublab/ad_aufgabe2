package balancedms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import balancedms.algorithm.Merger;
import balancedms.controls.Constants;
import balancedms.helper.FolgenErzeuger;
import balancedms.io._BinFile;
import balancedms.io._Tape;
import balancedms.io._TextTape;
import static balancedms.controls.Constants.*;

public class Start {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("----Ausgeglichenes 4-Wege MergeSort----");
		
		System.out.print("Erzeuge Tapes\t\t\t");
		_Tape tape1 = new _TextTape("Files/tape1.txt");
		_Tape tape2 = new _TextTape("Files/tape2.txt");
		_Tape tape3 = new _TextTape("Files/tape3.txt");
		_Tape tape4 = new _TextTape("Files/tape4.txt");
		System.out.println("abgeschlossen");
		

		
		System.out.print("Beginne Folgenerzeugung\t\t");
		FolgenErzeuger.erzeuge(tape1, Constants.FOLGENLAENGE);
		System.out.println("abgeschlossen");
		
		Merger m = new Merger();
		
		System.out.print("Bef�lle StartTapes (MemSort)\t");
		m.initialize(tape1, tape3, tape4);
		System.out.println("abgeschlossen");
		
		System.out.print("Starte Mergen der Tapes\t\t");
		//m.mergeTapes(MEMSORT_BUFFER, tape3, tape4, tape1, tape2);
		
	}

}
