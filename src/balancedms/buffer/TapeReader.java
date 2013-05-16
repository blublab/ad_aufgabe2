package balancedms.buffer;

import java.io.IOException;

import balancedms.tapes.Tape;

public interface TapeReader {
	
	/**
	 * Kommentar
	 * 
	 * @return Ein Element aus dem Buffer
	 * @throws IOException
	 */
	public int read() throws IOException;
	
	/**
	 * Ersetzt aktuell beschrieben werdende Tape durch das uebergebene
	 * Vorher: Buffer wegschreiben!
	 * 
	 * @param t
	 * @throws IOException
	 */
	public void replaceTape(Tape t) throws IOException;
	
	/**
	 * Gibt an, ob das darunterliegende Tape vollständig ausgelesen wurde
	 * @return
	 */
	public boolean isEoF();
	
	/**
	 * Setzt den TapeReader auf den nächsten Run
	 * @throws IOException
	 */
	void nextRun() throws IOException;
	/**
	 * Blub
	 * 
	 * @return
	 */
	public boolean isTapeEoF();

	public boolean isEoR();

}
