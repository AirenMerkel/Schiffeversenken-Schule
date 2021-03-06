package networkComunication;

import java.io.BufferedReader;
import java.io.IOException;



public class Input implements Runnable {

	BufferedReader clientBufferedReader = null;

	public Input(BufferedReader clientBufferedReader) {
		this.clientBufferedReader = clientBufferedReader;

	}

	public void run() {
		while (true) {
			try {
				// ausgabe der ausgabe des
				String output = clientBufferedReader.readLine();
				System.out.println(output);
			} catch (IOException ioe) {
				System.out.print("Anfrage an den server: ");
				System.out.println(ioe);
				break;
			}
		}

		// Stream beenden
		try {
			clientBufferedReader.close();
		} catch (IOException ioe) {
			System.out.print("Schlieﬂen der Streams: ");
			System.out.println(ioe);
		}

	}

}