package networkServer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

public class Output implements Runnable {

	BufferedWriter clientBufferedWriter = null;

	private Scanner scanner;
	

	public Output(BufferedWriter clientBufferedWriter) {
		this.clientBufferedWriter = clientBufferedWriter;

	}
	
	public void run() {
	// anfragen zum server senden
			System.out.println("Sie können nun mit dem Server chatten");
			System.out.println("Mit bye oder Auf Wiedersehen konnes sie die programme beenden");

			
			scanner = new Scanner(System.in);
			
			while (true) {
				// Anfrage aufnehmen
				
				String request =  "3464536732";
				try {
					request = scanner.nextLine();
					// anfrage zum server senden
					clientBufferedWriter.write(request);
					clientBufferedWriter.newLine();
					clientBufferedWriter.flush();

				} catch (SocketException se) {
					System.out.println("Anfrage Server: " + se);
					// schließen des Programms da der Server aus ist
					break;
				} catch (IOException ioe) {
					System.out.println("Anfrage Server: " + ioe);
					break;
				}

				if (request.contains("Auf Wiedersehen") || request.contains("bye")) {
					break;
				}

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//Wait 5000 ms = 5 sek
			}

			// Streams beenden
			try {
				clientBufferedWriter.close();

			} catch (IOException ioe) {
				System.out.print("Schließen der Streams: " + ioe);
			}
	}
}
