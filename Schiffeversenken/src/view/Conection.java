package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Conection {

	public JFrame frameConection;

	public JTextArea textArea = new JTextArea();
	public JRadioButton radioButton[] = new JRadioButton[10];
	private JTextField textField;
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
		
		
		textArea.setBounds(0, 0, 327, 452);
		frameConection.getContentPane().add(textArea);
		
	    ButtonGroup group = new ButtonGroup();
		for(int i = 0; i < 10; i++) {
			radioButton[i] = new JRadioButton(String.valueOf(i));
			radioButton[i].setBounds(333, 1+30*i, 109, 23);
		    group.add(radioButton[i]);
			frameConection.getContentPane().add(radioButton[i]);
		}
		
		JButton btnStartGame = new JButton("Spiel starten");
		btnStartGame.setBounds(472, 429, 102, 23);		
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectionBroadcast.Conect.refresh();
			}
		});
		frameConection.getContentPane().add(btnStartGame);
		
		//TODO add host button
		
		JButton btnReload = new JButton("Neu Laden");
		btnReload.setBounds(337, 429, 89, 23);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conectionBroadcast.Conect.refresh();
			}
		});
		frameConection.getContentPane().add(btnReload);
		
		textField = new JTextField();
		textField.setBounds(28, 463, 86, 20);
		frameConection.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Target = textField.getText();
				int[] coordinates = new int[2];
				coordinates = networkComunication.Respond.getCoordinates(Target);
				String msg = networkComunication.Respond.getHit(coordinates[0], coordinates[1]);
				System.out.println(">>>>>>>>>>>>>>>>>" + msg);
				
				
				
			}
		});
		btnNewButton.setBounds(124, 463, 89, 23);
		frameConection.getContentPane().add(btnNewButton);
	}
}
