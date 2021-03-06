package conectionBroadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
			try {
				Thread.sleep(2000);//Wait 5000 ms = 5 sek
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (!this.hasConnection) {
				this.sendWelcome();
				
			}
			
		}

		System.out.println("Beendet");
		

	}

	public void sendWelcome() {

		byte[] hostBytes = Broadcast.getMessageBytes();
		String hostString = Broadcast.getMessage();
		InetAddress inetAddress;
		try {
			inetAddress = InetAddress.getByName("255.255.255.255");
			DatagramPacket packet = new DatagramPacket(hostBytes, hostBytes.length, inetAddress, PORT);
			DatagramSocket socket = new DatagramSocket();
			socket.setBroadcast(true);
			socket.send(packet);
			socket.close();
			
			if(!hostString.contains(messages.Messages.host)) {
				System.out.println("1:"+hostString);
				if(hostString.contains(messages.Messages.acknowledgement)) {
					Broadcast.setConected(true);
					setHasConnection(true);
					Conect.datagramSocket.close();
					
				}else if(hostString.contains(messages.Messages.found)) {
					setHasConnection(true);
					
				}
//				Broadcast.setMessage(messages.Messages.host);
//				setHasConnection(true);
			}
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
		//Broadcast.setConected(hasConnection);
		this.hasConnection = hasConnection;
	}
}
