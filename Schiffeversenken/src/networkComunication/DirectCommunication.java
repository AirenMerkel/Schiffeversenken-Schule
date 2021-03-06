package networkComunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class DirectCommunication {
	
	private static String host;
	private static int port = 42069;
	
	public static void start(String getHost) {
		host = getHost;
	}
	
	public void StartDirectCommunication() {
		
		System.out.println("Start");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedWriter clientBufferedWriter = null;
		BufferedReader clientBufferedReader = null;
		Socket clientConnection = null;

		System.out.println("Witer");
		System.out.println(host);
		try {
			// Verbindung zum server aufbauen
			clientConnection = new Socket(host, port);
		} catch (UnknownHostException uhe) {

			System.out.println("1");
			System.out.println(uhe);
		} catch (IOException ioe) {
			System.out.println("2");
			System.out.println(ioe);
		}

		try {
			// Out / Inputstream aufbauen
			OutputStream clientOutputStream = clientConnection.getOutputStream();
			OutputStreamWriter clientOutputStreamWriter = new OutputStreamWriter(clientOutputStream);
			clientBufferedWriter = new BufferedWriter(clientOutputStreamWriter);

			InputStream clientInputStream = clientConnection.getInputStream();
			InputStreamReader clientInputStreamReader = new InputStreamReader(clientInputStream);
			clientBufferedReader = new BufferedReader(clientInputStreamReader);

			System.out.println("3");
		} catch (IOException ioe) {
			System.out.print("Aufbau der Streams");
			System.out.println(ioe);

		}
		// Thread zum lesen öffnen
		
		Input Input = new Input(clientBufferedReader);
		Thread threadRead = new Thread(Input);
		threadRead.start();
		
		Output Output = new Output(clientBufferedWriter);
		Thread threadWrite = new Thread(Output);
		threadWrite.start();
	}
}
