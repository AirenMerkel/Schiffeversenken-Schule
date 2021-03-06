package view;

import java.awt.EventQueue;

public class Main {

	static Welcome windowWelcome;
	static Opponent windowOpponent;
	static BuildOwn windowOwn;
	static Conection windowConection;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					

					windowWelcome = new Welcome();
					
					
					windowOpponent = new Opponent();
					
					windowOwn = new BuildOwn();
					
					windowConection = new Conection();
					

					windowWelcome.frameWelcome.setVisible(true);
					windowOpponent.frameOpponent.setVisible(false);
					windowOwn.frameOwn.setVisible(false);
					windowConection.frameConection.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	public static void setVisible(boolean welcome, boolean opponent, boolean own, boolean conection) {
		windowWelcome.frameWelcome.setVisible(welcome);
		windowOpponent.frameOpponent.setVisible(opponent);
		windowOwn.frameOwn.setVisible(true);
		windowConection.frameConection.setVisible(conection);
	}


	
}
