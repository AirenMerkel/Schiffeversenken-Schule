package view;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextArea;

import javax.swing.JFrame;

import listner.ListenerWelcome;

public class Welcome {
	private String text = 
			  "Spiel: Schiffe versenken \n"
			+ "\n"
			+ "Dauer: etwa 15 bis 25 Minuten\n"
			+ "\n"
			+ "Betriebsanleitung Zusammenfassung:\n"
			+ "\t\t Zum platzieren der schiffe müssen Sie:\n"
			+ "\t\t\t 1. Ein Schiff auf der Rechten seite auswählen\n"
			+ "\t\t\t 2. Das Schiff kann horizontal und vertikal platziert werden:\n"
			+ "\t\t\t\t a. Links klick: Horizontal\n"
			+ "\t\t\t\t b. Rechts klick: Vertikal\n"
			+ "\t\t Zum Löschen eines falsch platzieren Schiffes müssen Sie:\n"
			+ "\t\t\t 1. Mit dem Mausrad auf das Schiff klicken\n"
			+ "\n"
			+ "Spielregeln:\n"
			+ "\t10x10 Spielfeld\n"
			+ "\tSchiffe dürfen nicht aneinander angrenzen, auch nicht über Ecken. \n"
			+ "\tEs muss ein Kästchen in jede Richtung frei sein, auch diagonal.\n"
			+ "\tJede Runde 1 Schuss\n"
			+ "\tWenn von einem Spieler alle Schiffe zerstört sind, ist das Spiel zu ende\n"
			+ "\t(Eigentlich kann man nichts Falsch machen, weil das Progrmm jegliche Fehler unterbindet)\n"
			+ "\n\n"
			+ "Der \"Test Modus\" ist dafür da, dass man nicht alle Schiffe setzen muss";
	public JFrame frameWelcome;
	public static Checkbox chckbxTestModus;
	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameWelcome = new JFrame();
		frameWelcome.setTitle("Wilkommen");
		frameWelcome.setBounds(650, 100, 600, 600);
		frameWelcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameWelcome.setResizable(false);
		frameWelcome.getContentPane().setLayout(null);
		
		chckbxTestModus = new Checkbox("Test Modus");
		chckbxTestModus.setBounds(468, 391, 97, 23);
		frameWelcome.getContentPane().add(chckbxTestModus);
		
		TextArea welcomeTextArea = new TextArea();
		welcomeTextArea.setEditable(false);
		welcomeTextArea.setBounds(0, 0, 584, 435);
		welcomeTextArea.setText(text);
		frameWelcome.getContentPane().add(welcomeTextArea);
		
		Button buttonPlaceShips = new Button("Schiffe Setzen");
		buttonPlaceShips.setBounds(0, 430, 594, 141);
		ListenerWelcome.placeShipListener(buttonPlaceShips);
		frameWelcome.getContentPane().add(buttonPlaceShips);
		
		

		
	}
}
