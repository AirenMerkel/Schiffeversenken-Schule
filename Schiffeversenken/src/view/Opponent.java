package view;

import java.awt.Button;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;

import listner.ListenerOpponent;

public class Opponent {

	private JTextField textFieldX[];
	private JTextField textFieldY[];
	public JFrame frameOpponent;
	public static Button buttonXY[][]; // [X][Y]
	public static TextField infoBox = new TextField();
	public static TextField infoBox2 = new TextField();
	/**
	 * Create the application.
	 */
	public Opponent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frameOpponent = new JFrame();
		frameOpponent.setTitle("Generisches Spielfeld");
		frameOpponent.setBounds(50, 100, 600, 600);
		frameOpponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameOpponent.getContentPane().setLayout(null);

		String[][] coordinates = {{"0","1","2","3","4","5","6","7","8","9"},
								  {"a","b","c","d","e","f","g","h","i","j"}};

		
		textFieldX = new JTextField[10];
		textFieldY = new JTextField[10];
		
		for(int textField = 0; textField <= 9; textField++) {
			textFieldX[textField] = new JTextField();
			textFieldY[textField] = new JTextField();
			textFieldX[textField].setBounds(10+((textField+1)*30), 10, 30, 30);
			textFieldY[textField].setBounds(10, 10+((textField+1)*30), 30, 30);
			textFieldX[textField].setText(coordinates[1][textField].toUpperCase());
			textFieldY[textField].setText(coordinates[0][textField]);
			textFieldX[textField].setHorizontalAlignment(JTextField.CENTER);
			textFieldY[textField].setHorizontalAlignment(JTextField.CENTER);
			textFieldX[textField].setEditable(false);
			textFieldY[textField].setEditable(false);
			frameOpponent.getContentPane().add(textFieldX[textField]);
			frameOpponent.getContentPane().add(textFieldY[textField]);
		}
		
		//generate buttons
		buttonXY = new Button[10][10];
		
		for(int x = 0; x <= 9; x++) {
			for(int y = 0; y <= 9; y++) {
				String coordinatString = coordinates[1][y].toUpperCase() + coordinates[0][x];
				buttonXY[x][y] = new Button(coordinatString);
				buttonXY[x][y].setBounds(40+((y)*30), 40+((x)*30), 30, 30);
				frameOpponent.getContentPane().add(buttonXY[x][y]);
				
				
				ListenerOpponent.getListner(buttonXY[x][y], ",[" + coordinates[1][y].toUpperCase() + "],[" + coordinates[0][x] + "]");// get the listener for each button
			}
		}
		infoBox.setBounds(20, 420, 341, 22);
		infoBox.setEditable(false);
		frameOpponent.getContentPane().add(infoBox);
		infoBox2.setBounds(20, 442, 341, 22);
		infoBox2.setEditable(false);
		frameOpponent.getContentPane().add(infoBox2);
	}
	
}