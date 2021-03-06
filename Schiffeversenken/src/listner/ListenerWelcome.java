package listner;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerWelcome {
	public static void placeShipListener(Button button) {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.Main.setVisible(false, false, true, false);
			}
		});
	}

}
