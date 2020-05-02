package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rmi.libraryinterface.remoteQuestions;

import junit.framework.Test;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtpassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(100, 72, 432, 253);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(47, 87, 91, 19);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(240, 255, 240));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_2.setBounds(47, 143, 91, 19);
		panel.add(lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setToolTipText("Enter the Username\r\n");
		txtName.setBounds(174, 83, 211, 32);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(0, 120, 215));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(329, 213, 93, 30);
		panel.add(btnNewButton);
		
		txtpassword = new JPasswordField();
		txtpassword.setBounds(174, 143, 211, 32);
		panel.add(txtpassword);
		
		JButton btnNewButton_1 = new JButton("Test Connection");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					String rs=myService.TestConnection();
					System.out.println("The Connection to the server: "+rs);
				} catch (MalformedURLException | RemoteException | NotBoundException e) {
					System.out.println("The Connection to the server: Failed ");
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton_1.setBounds(174, 214, 145, 30);
		panel.add(btnNewButton_1);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				ClientStartQ1 l2= new ClientStartQ1 ();
				l2.setVisible(true);
				dispose();
				
				
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setFont(new Font("Arial", Font.PLAIN, 13));
		btnHome.setBackground(SystemColor.textHighlight);
		btnHome.setBounds(71, 213, 93, 30);
		panel.add(btnHome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\sliit\\SLIIT\\3rd year\\UOB\\CIS\\sandya\\14032020\\ProjectClient\\pic\\logbk.png"));
		//lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("./logbk.png")));
		
		lblNewLabel.setBounds(42, 32, 552, 338);
		contentPane.add(lblNewLabel);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String name= txtName.getText();
				String password= txtpassword.getText();
				
				
				try {
					remoteQuestions	myService = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					boolean rs=myService.login(name, password);
					String rs2=myService.SessionLogin(rs);
					System.out.println(rs2);
					if(rs) {
						JOptionPane.showMessageDialog(null,"Login Successfull");
						AdminHome adhome= new AdminHome(rs2);
						adhome.setVisible(true);
						dispose();
						
					}else {
						JOptionPane.showMessageDialog(null,"Incorrect username and password");
					}
					
					
					
					
				} catch (MalformedURLException | RemoteException | NotBoundException e1) {
								
					e1.printStackTrace();
				}
				
				
				
				
				
				
				

				
				
				
				
				
			
				
			}
		});
		
		
		
		
		
	}
}
