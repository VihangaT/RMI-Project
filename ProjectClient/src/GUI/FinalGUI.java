package GUI;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rmi.libraryinterface.remoteQuestions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class FinalGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textname;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalGUI frame = new FinalGUI();
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
	public FinalGUI() {
		setResizable(false);
		setFont(new Font("Agency FB", Font.PLAIN, 12));
		setBackground(new Color(138, 43, 226));
		setTitle("Lets Start the Questionnaire!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(new Color(240, 255, 240));
		lblNewLabel.setBackground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel.setBounds(63, 100, 88, 42);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setBackground(new Color(128, 128, 128));
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblNewLabel_1.setBounds(63, 199, 108, 27);
		panel.add(lblNewLabel_1);
		
		textname = new JTextField();
		textname.setToolTipText("Please enter your name");
		textname.setForeground(new Color(0, 0, 0));
		textname.setFont(new Font("Tahoma", Font.BOLD, 18));
		textname.setBounds(212, 101, 367, 42);
		panel.add(textname);
		textname.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setToolTipText("Please enter your email address");
		txtemail.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtemail.setBounds(212, 196, 367, 42);
		panel.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnNewButton = new JButton("End the Survey\r\n"); 
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String name= textname.getText();
		String email=txtemail.getText();
		
		try {
		
			if(!name.isEmpty() && !email.isEmpty())
			{
			
			
				remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
				boolean rs=myService.saveParticipants(name, email);
				
					if(rs) {
						JOptionPane.showMessageDialog(null, "Thank you for participating in our survey!!");
						ClientStartQ1 Start1= new ClientStartQ1();
						Start1.setVisible(true);
						dispose();
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Please insert your name and the email");
			}
			
		}catch(Exception ex){
			System.out.println("Error while saving participant data"+ex);
		}
		
		
		
	}
});
		btnNewButton.setBounds(246, 273, 281, 42);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Vindya Silva\\Desktop\\lab\\ProjectClient\\bin\\bk.jpg"));
		//
		
		
		//lblNewLabel_2.setIcon(new ImageIcon(FirstGUI.class.getResource("/bk.png")));
		//Image img= new ImageIcon(this.getClass().getResource("hkhjkh.png")).getImage();
		//lblNewLabel_2.setIcon(new ImageIcon(img));
		lblNewLabel_2.setBounds(371, 62, 156, 128);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("E:\\sliit\\SLIIT\\3rd year\\UOB\\CIS\\sandya\\14032020\\ProjectClient\\pic\\book2.jpg"));
		lblNewLabel_3.setBounds(10, 10, 658, 395);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(30, 65, 129, 42);
		panel.add(lblNewLabel_4);
		
	}
}
