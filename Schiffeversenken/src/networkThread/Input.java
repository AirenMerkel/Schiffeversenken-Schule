package networkThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import listner.ListenerOpponent;
import networkComunication.Comunication;

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

			
			if(clientRequest.contains(messages.Messages.fire)) {// if the request is an incoming hit 
				
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(clientRequest);// get the coordinates
				String msg = networkComunication.Respond.getHit(coordinates[0], coordinates[1]);//get the information what the hit does
				
				//Answer the opponent
				networkComunication.Comunication.setOutgoingMsg(clientRequest.replace(messages.Messages.fire, msg));//set respond message
				networkComunication.Comunication.setAnswerTime(true);//activate writer to send respond
				networkComunication.Comunication.setSetMsg(true);//set that msg is set
				
				try {
					Thread.sleep(1500);//Wait 1.5 seconds that the respond can send the respond
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if(msg.contains(messages.Messages.misshit)) {
					networkComunication.Comunication.setAnswerTime(true);//activate writer to send msg
					ListenerOpponent.lockButtons(false);//Activate buttons
					System.out.println("h1111");
					
				}else if(msg.contains(messages.Messages.lastShip)) {
					view.Main.setVisible(false, false, false, false, false, true);//show the gameover screen because the last ship is destroyed
					System.out.println("h1101");
					break;
				}else {
					networkComunication.Comunication.setAnswerTime(false);// if there was no misshit listen along to the opponent
					ListenerOpponent.lockButtons(true);					  //and lock the buttons
					System.out.println("h1011");
				}
				
			}else if(clientRequest.contains(messages.Messages.misshit)){//if the income respond is an misshit 
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(clientRequest);// get the coordinates
				Comunication.colorButton(false, coordinates[0],coordinates[1]); // Paint the button
				networkComunication.Comunication.setAnswerTime(false);	//Don't respond and wait for more respond
				ListenerOpponent.lockButtons(true);					//and lock the buttons
				System.out.println("h1100");
				
			}else if(clientRequest.contains(messages.Messages.lastShip)){//if the last ship of the opponent is destroyed show the celebration screen
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(clientRequest);// get the coordinates
				Comunication.colorButton(true, coordinates[0],coordinates[1]); // Paint the button
				view.Main.setVisible(false, false, false, false, true, false);
				System.out.println("h1000");
				break;
				
			}else if(clientRequest != null && (clientRequest.contains(messages.Messages.hit) || clientRequest.contains(messages.Messages.shipDestroyed))){
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(clientRequest);// get the coordinates
				Comunication.colorButton(true, coordinates[0],coordinates[1]); // Paint the button
				networkComunication.Comunication.setAnswerTime(true);
				ListenerOpponent.lockButtons(false);
				System.out.println("h0000");
			}
		
		}
	}
}