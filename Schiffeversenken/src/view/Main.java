package view;

import java.awt.EventQueue;

public class Main {

	static Welcome windowWelcome;
	static Opponent windowOpponent;
	static BuildOwn windowOwn;
	static Conection windowConection;
	static Celebration windowCelebration;
	static GameOver windowGameOver;
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
					
					windowCelebration = new Celebration();
					
					windowGameOver = new GameOver();
					

					windowWelcome.frameWelcome.setVisible(true);
					windowOpponent.frameOpponent.setVisible(false);
					windowOwn.frameOwn.setVisible(false);
					windowConection.frameConection.setVisible(false);
					windowCelebration.celebrationFrame.setVisible(false);
					windowGameOver.gameOverFrame.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	//Function to set the frames to visible or hidden
	public static void setVisible(boolean welcome, boolean opponent, boolean own, boolean conection, boolean celebration, boolean gameOver) {
		windowWelcome.frameWelcome.setVisible(welcome);
		windowOpponent.frameOpponent.setVisible(opponent);
		windowOwn.frameOwn.setVisible(own);//TODO to own
		windowConection.frameConection.setVisible(conection);
		windowCelebration.celebrationFrame.setVisible(celebration);
		windowGameOver.gameOverFrame.setVisible(gameOver);
	}


	
}
