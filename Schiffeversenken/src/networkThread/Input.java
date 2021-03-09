package networkThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import listner.ListenerOpponent;

public class Input extends Thread {
	private Socket client;

	public void run() {
		verbinden();
	}

	public Input(Socket client) {
		this.client = client;
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
				// Income Request
				clientRequest = serverBufferedReader.readLine();
				System.out.println("Request: 	" + clientRequest);
				



			} catch (IOException ioe) {
				System.out.println(ioe);
				break;
			}

			
			if(clientRequest.contains(messages.Messages.fire)) {
				
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(clientRequest);
				String msg = networkComunication.Respond.getHit(coordinates[0], coordinates[1]);
				
				//Answer the opponent
				networkComunication.Comunication.setOutgoingMsg(msg + clientRequest.replace(messages.Messages.fire, ""));
				networkComunication.Comunication.setAnswerTime(true);
				networkComunication.Comunication.setSetMsg(true);
				
				try {
					Thread.sleep(1500);//Wait 1.5 seconds
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(msg.contains(messages.Messages.misshit)) {
					networkComunication.Comunication.setAnswerTime(true);
					ListenerOpponent.lockButtons(false);
				}else {
					networkComunication.Comunication.setAnswerTime(false);
					ListenerOpponent.lockButtons(true);
				}
			}else if(clientRequest.contains(messages.Messages.misshit)){
				networkComunication.Comunication.setAnswerTime(false);
				ListenerOpponent.lockButtons(false);
			}else if(clientRequest.contains(messages.Messages.lastShip)){
				view.Main.setVisible(false, false, false, false, true, false);
				break;
			}else if(clientRequest != null){
				networkComunication.Comunication.setAnswerTime(false);
				ListenerOpponent.lockButtons(true);
			}
		
		}
	}
}