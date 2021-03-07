package networkComunication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

import conectionBroadcast.Broadcast;
import networkClient.InputClient;
import networkServer.Input;
import networkServer.Output;

public class DirectCommunication {
	
	private static String host;
	private static int port = 5631;
	
	public static ArrayList <Socket> clients = new ArrayList<>();
	static int i=0;
	
	
	public static void start() {
//		System.out.println(conectionBroadcast.Conect.datagramSocket.isClosed());
//		conectionBroadcast.Conect.datagramSocket.close();
//		System.out.println(conectionBroadcast.Conect.datagramSocket.isClosed());
//		
		
		
		DirectCommunication communicationStart = new DirectCommunication();
		if(Broadcast.isInfo()) {
			communicationStart.startDirectCommunicationClient();
		}else{
			communicationStart.startDirectCommunicationServer();
		}
	}

	@SuppressWarnings("resource")
	public void startDirectCommunicationServer() { 
		// Server Port aufbauen
				ServerSocket server = null;
				try {
					
					server = new ServerSocket(5631);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Warten auf einen Client am Port " + server.getLocalPort());

				int i = 1;

				while (true) {
					// auf aktive clients warten
					Socket client = null;
					try {
						client = server.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("client verbunden");

					clients.add(client);

					// Thread öffnen
					InputClient threadWrite = new InputClient(client, i);
					System.out.println(threadWrite.getId() + ": " + i);
					threadWrite.start();
					i++;
					if(i > 1){
						System.out.println(i);
						break;
						}
					}
	}
	
	
	public void startDirectCommunicationClient() {
		host = "192.168.10.101"; //Broadcast.getConectIP();
		System.out.println("SATRT:\t"+host);
		System.out.println("Start");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedWriter clientBufferedWriter = null;
		BufferedReader clientBufferedReader = null;
		Socket clientConnection = null;

		System.out.println("Weiter");
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
