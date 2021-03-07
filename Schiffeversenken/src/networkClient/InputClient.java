package networkClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class InputClient extends Thread {
	private Socket client;
	private int id;

	public void run() {
		verbinden();
	}

	public InputClient(Socket client, int id) {
		this.client = client;
		this.id = id;
	}

	boolean turnOff = false;

	BufferedReader serverBufferedReader = null;

	public void verbinden() {

		try {

			// Client InputStream aufbauen
			InputStream serverInputStream = client.getInputStream();
			InputStreamReader serverInputStreamReader = new InputStreamReader(serverInputStream);
			serverBufferedReader = new BufferedReader(serverInputStreamReader);

		} catch (UnknownHostException uhe) {
			System.out.println(uhe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}

		while (true) {
			String clientRequest = null;

			try {
				// Anfrage erfassen
				clientRequest = serverBufferedReader.readLine();
				System.out.println(id + ": " + clientRequest);

				if (clientRequest == null) {
					break;
				}

			} catch (IOException ioe) {
				System.out.println(ioe);
				break;
			}

			// Anfrage auf jeden client ausgeben
			try {
				for (int i = 0; i <= networkComunication.DirectCommunication.clients.size() - 1; i++) {
					if (i != id - 1) {
						try {
							BufferedWriter serverBufferedWriter = null;
							
							// Client OutputStream definieren
							OutputStream serverOutputStream = networkComunication.DirectCommunication.clients.get(i).getOutputStream();
							OutputStreamWriter serverOutputStreamWriter = new OutputStreamWriter(serverOutputStream);
							serverBufferedWriter = new BufferedWriter(serverOutputStreamWriter);

							serverBufferedWriter.write(id + ": " + clientRequest);
							serverBufferedWriter.newLine();
							serverBufferedWriter.flush();
							
						} catch (SocketException se) {}
					}
				}

			} catch (NullPointerException npe) {
				System.out.println(id + "Outputstrem" + npe);
				break;
			} catch (IOException ioe) {
				System.out.println(id + "Outputstrem" + ioe);
				break;
			}
		}
	}
}