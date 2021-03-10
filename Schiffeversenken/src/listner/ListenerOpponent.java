package listner;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerOpponent {
	
	public static void getListner(Button button, String coordinates) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(coordinates);
				
//Fire,[x1],[x2]
				networkComunication.Comunication.setOutgoingMsg(messages.Messages.fire + coordinates);
				networkComunication.Comunication.setSetMsg(true);
				lockButtons(true);
				//changeColor(button, "hit");
				//networkComunication.Respond.getCoordinates(coordinates);
			}
		});
	}
	
	
	public static void lockButtons(boolean lock) {
		for(int x = 0; x <= 9; x++) {
			for(int y = 0; y <= 9; y++) {
				view.Opponent.buttonXY[y][x].setEnabled(!lock);
			}
		}
	}
	
	public static void changeColor(Button button, String action) {
		switch (action) {
		case "hit":
			button.setBackground(Color.RED);
			break;

		case "mishit":
			button.setBackground(Color.YELLOW);
			break;
		
		case "notPressed":
			button.setBackground(Color.BLUE);
			break;
			
		case "ship":
			button.setBackground(Color.LIGHT_GRAY);
			break;

		case "selected":
			button.setBackground(Color.PINK);
			break;
		case "normal":
			button.setBackground(Color.decode("#f0f0f0"));
			break;
		default:
			break;
		}
	}
}
