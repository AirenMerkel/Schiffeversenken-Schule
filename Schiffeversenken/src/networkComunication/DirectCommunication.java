package networkComunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

import conectionBroadcast.Broadcast;
import listner.ListenerOpponent;
import networkThread.Input;
import networkThread.Output;

public class DirectCommunication {
	
	private static String host;
	private static int port = 42069;
	
	public static ArrayList <Socket> clients = new ArrayList<>();
	static int i=0;
	
	
	public static void start() {
//		System.out.println(conectionBroadcast.Conect.datagramSocket.isClosed());
//		conectionBroadcast.Conect.datagramSocket.close();
//		System.out.println(conectionBroadcast.Conect.datagramSocket.isClosed());
//		
		

		view.Main.setVisible(false, true, true, false, false, false);
		DirectCommunication communicationStart = new DirectCommunication();
		if(Broadcast.isInfo()) {
			communicationStart.startDirectCommunicationClient();
		}else{
			communicationStart.startDirectCommunicationServer();
		}
	}

	public void startDirectCommunicationServer() { 
		// Server Port aufbauen
				networkComunication.Comunication.setAnswerTime(true);
				ListenerOpponent.lockButtons(false);
				
				ServerSocket server = null;
				try {
					
					server = new ServerSocket(port);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("Wait for opponent" + server.getLocalPort());

				while (true) {
					// auf aktive clients warten
					Socket client = null;
					try {
						client = server.accept();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

					if(client.getInetAddress().toString().contains(Broadcast.getConectIP())) {
						System.out.println("client verbunden");
	
						clients.add(client);
	
						threadStarten(client);
							
						
						break;
					}
				}
	}
	
	

	public void startDirectCommunicationClient() {
		networkComunication.Comunication.setAnswerTime(false);
		ListenerOpponent.lockButtons(true);
		
		host = Broadcast.getConectIP();
		System.out.println("SATRT:\t"+host);


		Socket clientConnection = null;

		try {
			// Verbindung zum server aufbauen
			clientConnection = new Socket(host, port);
			threadStarten(clientConnection);
		} catch (UnknownHostException uhe) {
			System.out.println(uhe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		/*
		BufferedWriter clientBufferedWriter = null;
		BufferedReader clientBufferedReader = null;		

		
		System.out.println("Weiter");
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
		
		InputClient Input = new InputClient(clientBufferedReader);
		Thread threadRead = new Thread(Input);
		threadRead.start();
		
		OutputClient Output = new OutputClient(clientBufferedWriter);
		Thread threadWrite = new Thread(Output);
		threadWrite.start();
*/
	}
	
	private void threadStarten(Socket client) {
		// Thread öffnen

		Input threadRead = new Input(client);
		threadRead.start();
		
		Output threadWrite = new Output(client);
		threadWrite.start();
		
		
	}
}
