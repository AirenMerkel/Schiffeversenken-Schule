package view;

import java.awt.Button;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JTextField;

import listner.ListenerOwn;

public class BuildOwn {

	private JTextField textFieldX[];
	private JTextField textFieldY[];
	public JFrame frameOwn;
	public static Button buttonXY[][];
	public static Button ships[];
	private static Button seachOpponent;
	public static TextField errorHandling = new TextField();
	public static TextField errorHandling2 = new TextField();
	public static TextField numberShips[];
	public static Integer numbersOfShips[] = {4,3,2,1};

	public static String[] ship_name = {"U-Boote", "Zerstörer", "Kreuzer", "Schlachtschiff"};


	/**
	 * Create the application.
	 */
	public BuildOwn() {
		initializeOwn();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeOwn() {
		frameOwn = new JFrame();
		frameOwn.setTitle("Eigene Spielfeld");
		frameOwn.setBounds(650, 100, 600, 600);
		frameOwn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameOwn.getContentPane().setLayout(null);
		

		listner.ListenerOwn.clearMap();
		
		String[][] coordinates = {{"0","1","2","3","4","5","6","7","8","9"},
								  {"a","b","c","d","e","f","g","h","i","j"}};

		
		textFieldX = new JTextField[10];
		textFieldY = new JTextField[10];
		
		for(int textField = 0; textField <= 9; textField++) {
			textFieldX[textField] = new JTextField();
			textFieldY[textField] = new JTextField();
			textFieldX[textField].setBounds(10+((textField+1)*30), 10, 30, 30);
			textFieldY[textField].setBounds(10, 10+((textField+1)*30), 30, 30);
			textFieldX[textField].setText(coordinates[0][textField].toUpperCase());
			textFieldY[textField].setText(coordinates[1][textField]);
			textFieldX[textField].setHorizontalAlignment(JTextField.CENTER);
			textFieldY[textField].setHorizontalAlignment(JTextField.CENTER);
			textFieldX[textField].setEditable(false);
			textFieldY[textField].setEditable(false);
			frameOwn.getContentPane().add(textFieldX[textField]);
			frameOwn.getContentPane().add(textFieldY[textField]);
		}

		
		buttonXY = new Button[10][10];
		
		for(int y = 0; y <= 9; y++) {
			for(int x = 0; x <= 9; x++) {
				String koordinatenString = coordinates[1][y].toUpperCase() + coordinates[0][x];
				buttonXY[y][x] = new Button(koordinatenString);
				buttonXY[y][x].setBounds(40+((x)*30), 40+((y)*30), 30, 30);
				frameOwn.getContentPane().add(buttonXY[y][x]);
				ListenerOwn.setShip(buttonXY[y][x], koordinatenString, x, y);
			}
		}
		
		
		numberShips = new TextField[4];
		for(int countShip = 0; countShip <= 3; countShip++) {
			numberShips[countShip] = new TextField();
			numberShips[countShip].setText(numbersOfShips[countShip].toString());
			numberShips[countShip].setEditable(false);
			numberShips[countShip].setBounds(365, 40+((countShip+1)*40), 30, 30);
			frameOwn.getContentPane().add(numberShips[countShip]);
		}
		
		ships = new Button[4];
		for(int ship = 0; ship <= 3; ship++) {
			ships[ship] = new Button(ship_name[ship]);
			
			ships[ship].setBounds(400, 40+((ship+1)*40),60+((ship+1)*30),30);
			frameOwn.getContentPane().add(ships[ship]);
			
			ListenerOwn.getShipListner(ships[ship], ship);
		}
		

		// Save Button to go to find opponent
		seachOpponent = new Button("Gegner suche");
		seachOpponent.setBounds(450, 300, 90, 30);
		ListenerOwn.seachOpponent(seachOpponent);
		frameOwn.getContentPane().add(seachOpponent);
		
		//Text Field ErrorHandling
		errorHandling.setBounds(20, 420, 341, 22);
		errorHandling.setEditable(false);
		frameOwn.getContentPane().add(errorHandling);
		errorHandling2.setBounds(20, 442, 341, 22);
		errorHandling2.setEditable(false);
		frameOwn.getContentPane().add(errorHandling2);


//		Button button = new Button("New button");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		button.setBounds(467, 76, 60, 30);
//		frameOwn.getContentPane().add(button);


	}
}
