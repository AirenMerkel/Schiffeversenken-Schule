package conectionBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import networkComunication.DirectCommunication;

public class SendBroadcast implements Runnable {

	private boolean hasConnection = false;
	public static final int PORT = 42069;
	private static volatile SendBroadcast communicationSender;
	
	private DatagramSocket datagramSocket;

	private SendBroadcast() {

	}
	
	public DatagramSocket getDatagramSocket() {
		return datagramSocket;
	}



	public void setDatagramSocket(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}



	//@Override
	public void run() {
		
		while(!Broadcast.isConected()) {
			// Has no active connection?
			// Send every 5 Seconds a welcome broadcast 0000 

			if (!this.hasConnection) {
				System.out.println("Sender:		"+ Broadcast.getMessage());
				this.sendWelcome();
				
			}
			try {
				Thread.sleep(5000);//Wait 5000 ms = 5 sek
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("Beendet");

	}

	public void sendWelcome() {

		byte[] hostBytes = Broadcast.getMessageBytes();
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getByName("255.255.255.255");
			DatagramPacket packet = new DatagramPacket(hostBytes, hostBytes.length, inetAddress, PORT);
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			socket.send(packet);
			socket.close();
			if(hostBytes != messages.Messages.host.getBytes()) {
				if(hostBytes == messages.Messages.acknowledgement.getBytes()) {
					Broadcast.setConected(true);
					networkComunication.DirectCommunication.start(Broadcast.getConectIP());
					DirectCommunication communicationStart = new DirectCommunication();
					communicationStart.StartDirectCommunication();
				}else if(hostBytes == messages.Messages.found.getBytes()) {
					Broadcast.setConected(true);
				}
				Broadcast.setMessage(messages.Messages.host);
				setHasConnection(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendAnswer(InetAddress inetAddress, String message, int port) {
		byte[] messageBytes = message.getBytes();
		try {
			DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length, inetAddress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static SendBroadcast getCommunicationSender() {

		if (communicationSender == null) {
			synchronized (SendBroadcast.class) {
				if (communicationSender == null) {
					communicationSender = new SendBroadcast();
				}
			}
		}

		return communicationSender;
	}

	public boolean isHasConnection() {
		return hasConnection;
	}

	public void setHasConnection(boolean hasConnection) {
		Broadcast.setConected(hasConnection);
		this.hasConnection = hasConnection;
	}
}
