package listner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ListenerIncomeConection {
	public static void accept(JButton button, JFrame frame, String ip, boolean SVFound) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
				if(conectionBroadcast.Broadcast.getConectIP() == null || conectionBroadcast.Broadcast.getConectIP() == ip) {
					conectionBroadcast.Broadcast.setInfo(SVFound);
					conectionBroadcast.Broadcast.setConectIP(ip);
					
					if(!SVFound) {
						conectionBroadcast.Broadcast.setMessage(messages.Messages.found);
					}else {
						conectionBroadcast.Broadcast.setMessage(messages.Messages.acknowledgement);
					}
				}
				
				//conectionBroadcast.Broadcast.setConected(true);
				//networkComunication.DirectCommunication.start(ip.replace("/", ""));
			}
		});
	}
	
	public static void deny(JButton button, JFrame frame) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
	}
}
