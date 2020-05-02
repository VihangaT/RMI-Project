package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;

import com.rmi.libraryinterface.remoteQuestions;

import GUI.AnsweringGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ClientStartQ1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientStartQ1 frame = new ClientStartQ1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientStartQ1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Start the Survey");
		btnNewButton.setToolTipText("Please start the survey");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Starting the Questionnaire
				
				try {
					int temp = 1;
					remoteQuestions	myService = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					JSONArray objjs = myService.getAllQuestions();
					
					new AnsweringGUI(objjs, temp).setVisible(true);
					dispose();

				} catch (RemoteException | MalformedURLException | NotBoundException er) {
					JOptionPane.showMessageDialog(null,"Server is unavailable!");
					er.printStackTrace();
				} 

				
				
				
				
				
			}
		});
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setBounds(22, 188, 155, 25);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(235, 0, 465, 469);
		lblNewLabel_1.setIcon(new ImageIcon(ClientStartQ1.class.getResource("/survey1.png")));
		panel.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Access only for the library Administration");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Arial", Font.BOLD, 13));
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setBounds(22, 223, 155, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login l2= new Login();
				l2.setVisible(true);
				dispose();
			}
		});
		panel.add(btnLogin);
		
		
	}
}
