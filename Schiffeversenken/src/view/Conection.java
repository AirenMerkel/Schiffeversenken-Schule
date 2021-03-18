package view;

import java.awt.TextArea;

import javax.swing.JFrame;


public class Conection {

	public JFrame frameConection;

	private String text = "Warten auf Gegner..."
			+ "Es taucht entweder eine Suchanfrage oder eine Besätigung auf, \n"
			+ "\tsobald ein Gegner gefunden wurde\n\n"
			+ "Sie müssen entscheiden ob sie diese annehmen oder ablehnen\n\n"
			+ "WICHTIG: Bestätigungen, von einer gleichen ip adresse wie eine Suchanfrage,\n"
			+ "\tmüssen priorisiert werden";
	/**
	 * Create the application.
	 */
	public Conection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameConection = new JFrame();
		frameConection.setTitle("Verbinden...");
		frameConection.setBounds(650, 100, 600, 600);
		frameConection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameConection.getContentPane().setLayout(null);
		
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 584, 435);
		textArea.setText(text);
		frameConection.getContentPane().add(textArea);
		

	}
}
