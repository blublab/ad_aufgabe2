package ebms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FolgenErzeuger {
	private static int lowerBound = Integer.MIN_VALUE / 2;
	private static int upperBound = Integer.MAX_VALUE / 2;

	private FolgenErzeuger() {
	};

	public static void erzeuge(File file, BigInteger anzahl)
			throws FileNotFoundException, IOException {
		Random randomizer = new Random();
		if (file.exists()){
			file.delete();
		}

		FileOutputStream fos = new FileOutputStream(file);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		DataOutputStream dos = new DataOutputStream(bos);

		BigInteger eins = BigInteger.valueOf(1);
		for (BigInteger i = BigInteger.valueOf(0); !i.equals(anzahl); i = i
				.add(eins)) {
			int zufallszahl = (lowerBound + randomizer.nextInt((upperBound)
					+ randomizer.nextInt((upperBound))));
			dos.writeInt(zufallszahl);
		}
		fos.flush();
		dos.close();
		bos.close();
		fos.close();
	}

	public static void ausgabe(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		DataInputStream dis = new DataInputStream(bis);
		while (dis.available() > 0) {
			System.out.println(dis.readInt());
		}
	}
}
