package conectionBroadcast;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Conect {

	public static DatagramSocket datagramSocket;
	private Thread communicationReceiverThread;
	private Thread communicationSenderThread;
	//public static String host = null;
	//public static int port = 42069;
	
	public Conect() {
		
		try {
			Conect.datagramSocket = new DatagramSocket(SendBroadcast.PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	public static void start() {
		Conect communicationStart = new Conect();
		communicationStart.startComunication();
		
//		while(true) { //wait until other player found and threads are closed
//			try {
//				Thread.sleep(1500);//Wait
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if(Broadcast.isConected()) {
//				break;
//			}
//		}
//		networkComunication.DirectCommunication.start();
	}
	public void startComunication() {
		//Set the socket before the execution of the run/start method
		SendBroadcast.getCommunicationSender().setDatagramSocket(Conect.datagramSocket);
		
		communicationReceiverThread = new Thread(new ReciveBroadcast(Conect.datagramSocket));
		communicationReceiverThread.start();
		
		// Starte einen Kommunikationsthread
		communicationSenderThread = new Thread(SendBroadcast.getCommunicationSender());
		communicationSenderThread.start(); // Wartet nun auf eine Verbindung
		
	}
	
	
}
