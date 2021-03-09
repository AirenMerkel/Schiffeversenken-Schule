package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class GameOver {

	public JFrame gameOverFrame;
	private JTextField txtSieHabenVerloren;

	/**
	 * Create the application.
	 */
	public GameOver() {
		initialize();
	}

	/**
	 * Initialize the contents of the gameOverFrame.
	 */
	private void initialize() {
		gameOverFrame = new JFrame();
		gameOverFrame.setBounds(100, 100, 855, 773);
		gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOverFrame.getContentPane().setLayout(null);
		
		txtSieHabenVerloren = new JTextField();
		txtSieHabenVerloren.setEditable(false);
		txtSieHabenVerloren.setFont(new Font("Tahoma", Font.PLAIN, 70));
		txtSieHabenVerloren.setText("Sie haben Verloren");
		txtSieHabenVerloren.setBounds(89, 168, 694, 166);
		gameOverFrame.getContentPane().add(txtSieHabenVerloren);
		txtSieHabenVerloren.setColumns(10);
	}

}
