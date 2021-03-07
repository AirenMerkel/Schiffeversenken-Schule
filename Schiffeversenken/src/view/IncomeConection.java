package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class IncomeConection extends Thread {

	private JFrame frame;
	private JTextField infoBox;
	private JTextField ipAdressText;
	public static boolean SVFound; // if SVFound(true) or SVSearch(False)
	public static String ipAdress;
	private JTextField svRequestText;
	
	/**
	 * Launch the application.
	 */
	
	public static void Start() {
		try {
			IncomeConection window = new IncomeConection();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public IncomeConection() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		infoBox = new JTextField();
		infoBox.setText("Eingehende anfrage von:");
		infoBox.setBounds(24, 33, 366, 20);
		infoBox.setEditable(false);
		frame.getContentPane().add(infoBox);
		infoBox.setColumns(10);
		
		ipAdressText = new JTextField();
		ipAdressText.setBounds(24, 64, 366, 20);
		ipAdressText.setEditable(false);
		ipAdressText.setText(ipAdress);
		frame.getContentPane().add(ipAdressText);
		ipAdressText.setColumns(10);
		
		JButton btnAccept = new JButton("Akzeptieren");
		btnAccept.setBounds(24, 126, 366, 46);
		listner.ListenerIncomeConection.accept(btnAccept, frame, ipAdress, SVFound);
		frame.getContentPane().add(btnAccept);
		
		JButton btnDeny = new JButton("Ablehnen");
		btnDeny.setBounds(24, 183, 366, 46);
		listner.ListenerIncomeConection.deny(btnDeny, frame);
		frame.getContentPane().add(btnDeny);
		
		svRequestText = new JTextField();
		if(SVFound) {
			svRequestText.setText("Suchanfrage");
		}else{
			svRequestText.setText("Best�tigung");
		}
		svRequestText.setEditable(false);
		svRequestText.setColumns(10);
		svRequestText.setBounds(24, 95, 366, 20);
		frame.getContentPane().add(svRequestText);
	}
}
