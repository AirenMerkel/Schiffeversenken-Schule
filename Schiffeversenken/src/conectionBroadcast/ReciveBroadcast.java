package conectionBroadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ReciveBroadcast implements Runnable {

	private ArrayList<String> IPAdress = new ArrayList<>();
	private DatagramSocket datagramSocket;
	//private boolean notConected = true;
	
	public ReciveBroadcast(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}
	
	//@Override
	public void run() {
		boolean notConected = true;
		while(notConected) {
			
			DatagramPacket datagramPacket = new DatagramPacket(new byte[512], 512);
			try {
				this.datagramSocket.receive(datagramPacket);
				String ownAdress = InetAddress.getLocalHost().getHostAddress();
				
				if(!datagramPacket.getAddress().toString().contains(ownAdress) && !Broadcast.isConected()) {
					readRecive(datagramPacket);
				}
			} catch (Exception e) {
				System.out.println(e);
				// Do Nothing wait for income request
			}
			notConected = !Broadcast.isConected();
			

		}

		System.out.println("ENDE");

		networkComunication.DirectCommunication.start();
		
		
	}
	
	private void readRecive(DatagramPacket datagramPacket) {
		//Who has send this package?
		InetAddress senderAdress = datagramPacket.getAddress();
		//int senderPort = datagramPacket.getPort();
		String message = new String(datagramPacket.getData());
		String senderAdressString = senderAdress.toString();
		senderAdressString = senderAdressString.replace("/", "");
		
		String conectIP = Broadcast.getConectIP();
		
		boolean info = Broadcast.isInfo();// if SVFound(true) or SVSearch(False)
		
		if (message.contains(messages.Messages.host) && !IPAdress.contains(senderAdressString)) {
			IPAdress.add(senderAdressString);
			view.IncomeConection.SVFound = false;
			view.IncomeConection.ipAdress = senderAdressString;
			view.IncomeConection.Start();
			
			//SendBroadcast.getCommunicationSender().setHasConnection(true);
			//SendBroadcast.getCommunicationSender().sendAnswer(senderAdress, messages.Messages.found, senderPort);
		}
		
		//answer message (sender recognize a new connection)
		if (message.contains(messages.Messages.found ) && info) { //(!IPAdress.contains(senderAdressString) || IPAdress.contains(conectIP))
			IPAdress.add(senderAdressString);
			view.IncomeConection.SVFound = true;
			view.IncomeConection.ipAdress = senderAdressString;
			view.IncomeConection.Start();
			
		}
		
		if (message.contains(messages.Messages.acknowledgement) && senderAdressString.contains(conectIP) && !info) {
			System.out.println("ACK");
			Broadcast.setConected(true);
		}
	}
	
	
}
