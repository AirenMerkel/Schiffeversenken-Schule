package conectionBroadcast;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Conect {

	private DatagramSocket datagramSocket;
	private Thread communicationReceiverThread;
	private Thread communicationSenderThread;
	//public static String host = null;
	//public static int port = 42069;
	
	public Conect() {
		try {
			this.datagramSocket = new DatagramSocket(SendBroadcast.PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	public static void start() {
		Conect communicationStart = new Conect();
		communicationStart.startComunication();
	}
	public void startComunication() {
		//Set the socket before the execution of the run/start method
		SendBroadcast.getCommunicationSender().setDatagramSocket(this.datagramSocket);
		
		communicationReceiverThread = new Thread(new ReciveBroadcast(this.datagramSocket));
		communicationReceiverThread.start();
		
		// Starte einen Kommunikationsthread
		communicationSenderThread = new Thread(SendBroadcast.getCommunicationSender());
		communicationSenderThread.start(); // Wartet nun auf eine Verbindung


		
	}
	
		
	
	public static void refresh() {

		
	}
	
}
