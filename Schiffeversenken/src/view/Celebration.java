package view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Celebration {

    public JFrame celebrationFrame;
	private JTextField txtSieHabenGewonen;

	/**
	 * Create the application.
	 */
	public Celebration() {
		initialize();
	}

	/**
	 * Initialize the contents of the celebrationFrame.
	 */
	private void initialize() {
		celebrationFrame = new JFrame();
		celebrationFrame.setBounds(100, 100, 855, 773);
		celebrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		celebrationFrame.getContentPane().setLayout(null);
		
		txtSieHabenGewonen = new JTextField();
		txtSieHabenGewonen.setEditable(false);
		txtSieHabenGewonen.setFont(new Font("Tahoma", Font.PLAIN, 70));
		txtSieHabenGewonen.setText("Sie haben Gewonen");
		txtSieHabenGewonen.setBounds(89, 168, 694, 166);
		celebrationFrame.getContentPane().add(txtSieHabenGewonen);
		txtSieHabenGewonen.setColumns(10);
	}
}
