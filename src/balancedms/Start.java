package balancedms;

import java.io.IOException;

import balancedms.tapes.FileTape;
import balancedms.tapes.Tape;
import balancedms.tapes.TextTape;
import static balancedms.Constants.*;

public class Start {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		Tape tape1 = new TextTape("tape1.txt");
		FolgenErzeuger.erzeuge(tape1, FOLGENLAENGE);
		
		System.out.println("==================== Folge ====================");
		//FolgenErzeuger.ausgabe(tape1);
		tape1.print();
		System.out.println("==================== Folge ====================");
		
		Tape tape2 = new TextTape("tape2.txt");
		Tape tape3 = new TextTape("tape3.txt");
		Tape tape4 = new TextTape("tape4.txt");

		MergerFinal m = new MergerFinal();
		m.initialize(tape1, tape3, tape4);
		
//		System.out.println("==================== Tape3 ====================");
//
////		FolgenErzeuger.ausgabe(tape3);
//		tape1.print();
//		
//		System.out.println("==================== Tape4 ====================");

		System.out.println(tape1);
		tape1.print();
		System.out.println(tape2);
		tape2.print();
		System.out.println(tape3);
		tape3.print();
		System.out.println(tape4);
		tape4.print();

//		FolgenErzeuger.ausgabe(tape4);
		

		
//		m.mergen(MEMSORT_BUFFER, tape3, tape4, tape1, tape2);
		

//		System.out.println("====================  ====================");
//		
//		System.out.println("====================  ====================");
//
//		
//		System.out.println("====================  ====================");
//
//		
//		System.out.println("====================  ====================");

		
		
		
		

//		System.out.println("==================== Tape1 ====================");
//
//		FolgenErzeuger.ausgabe(tape1);
//		System.out.println("==================== Tape2 ====================");
//
//		FolgenErzeuger.ausgabe(tape2);
//		System.out.println("==================== Tape3 ====================");
//
//		FolgenErzeuger.ausgabe(tape3);
//		System.out.println("==================== Tape4 ====================");
//
//		FolgenErzeuger.ausgabe(tape4);
	
//		tape1.close();
//		tape2.close();
//		tape3.close();
//		tape4.close();
		
//		tape1.getFile().delete();
//		tape2.getFile().delete();
//		tape3.getFile().delete();
//		tape4.getFile().delete();
		
	}

}
