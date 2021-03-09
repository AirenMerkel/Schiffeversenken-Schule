package networkThread;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Output extends Thread {

	
	private Socket client;
	BufferedWriter clientBufferedWriter = null;
	

	public void run() {
		verbinden();
	}

	public Output(Socket client) {
		this.client = client;
	}
	
	
	public void verbinden() {
	// anfragen zum server senden
		
		
		try {

			// Client InputStream aufbauen
			OutputStream clientOutputStream = client.getOutputStream();
			OutputStreamWriter clientOutputStreamWriter = new OutputStreamWriter(clientOutputStream);
			clientBufferedWriter = new BufferedWriter(clientOutputStreamWriter);

		} catch (UnknownHostException uhe) {
			System.out.println(uhe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
			
			while (true) {

				
				String msg = null;
				if(networkComunication.Comunication.isAnswerTime() && networkComunication.Comunication.isSetMsg()) {
					msg = networkComunication.Comunication.getOutgoingMsg();
					
					try {
						// anfrage zum server senden
						clientBufferedWriter.write(msg);
						clientBufferedWriter.newLine();
						clientBufferedWriter.flush();
	
					} catch (SocketException se) {
						System.out.println("Anfrage Server: " + se);
						// schlieﬂen des Programms da der Server aus ist
						break;
					} catch (IOException ioe) {
						System.out.println("Anfrage Server: " + ioe);
						break;
					}

					networkComunication.Comunication.setAnswerTime(false);
					networkComunication.Comunication.setSetMsg(false);
					
					if(msg.contains(messages.Messages.lastShip)) {
						view.Main.setVisible(false, false, false, false, false, true);
						break;
					}
				}
				

				
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//Wait 5000 ms = 5 sek
			}

			// Streams beenden
			try {
				clientBufferedWriter.close();

			} catch (IOException ioe) {
				System.out.print("Schlieﬂen der Streams: " + ioe);
			}
	}
}

