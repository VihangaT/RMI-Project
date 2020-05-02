package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rmi.libraryinterface.remoteQuestions;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MailSender extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	static String Session;
	private JTextField textFieldsubj;
	ArrayList fetch;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MailSender frame = new MailSender();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MailSender(String Session) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 545);
//		setBounds(new Rectangle(800, 600, 1000, 1000));
//		setPreferredSize(new Dimension(1000, 1000));
//		setVisible(true);
//		setSize(new Dimension(825, 705));
//		setMaximumSize(new Dimension(1000, 1000));
//		setTitle("Mail Sender");
//		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 660, 433);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setVerifyInputWhenFocusTarget(false);
		contentPane.setSize(new Dimension(800, 600));
		contentPane.setOpaque(false);
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 22, 777, 46);
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Admin Mail Control Center");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(259, 5, 259, 27);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(20, 84, 777, 403);
		panel_2.setLayout(null);
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(156, 26, 600, 39);
		panel_2.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(211, 211, 211));
		textArea.setBounds(156, 147, 600, 193);
		panel_2.add(textArea);
		
		try {
			
			 String email;
			 int Size;
			 fetch= new ArrayList();
			 
			 remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
			 fetch=myService.getallclients();
			 Size=fetch.size();
			 
			 comboBox.removeAll();
			 comboBox.addItem("Please Select a Recipients");
			 
			 for(int i=0;i<Size;i++) {
				
					email=(String) fetch.get(i);
					comboBox.addItem(email);					
					
			}
			 
		}catch(Exception e) {
			
			System.out.println("Error while loading the recipients"+e);
			
		}
		
		
		
		
		
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					boolean status;
					
					String to=comboBox.getSelectedItem().toString();
					String subject=textFieldsubj.getText();
					String bodyText=textArea.getText();
					
					if(comboBox.getSelectedItem().toString().isEmpty() || textFieldsubj.getText().isEmpty() || textArea.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Please fill the required fields");
					}
					
					remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					status=myService.sendEmail(to, subject, bodyText);
					
					if (status)
					{
						
						JOptionPane.showMessageDialog(null, "Mail Sent Successfully");
						
					}else {
						
						JOptionPane.showMessageDialog(null, "Error While Sending Mail");
						
					}
					
				}catch(Exception ex)
				{
					
					System.out.println("Error while sending mails:Check the server connection");
					ex.printStackTrace();
					
				}
							
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(650, 362, 106, 31);
		panel_2.add(btnNewButton);
		
		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHome.setBounds(10, 362, 106, 31);
		panel_2.add(btnHome);
		
		JLabel lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(37, 186, 109, 39);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("TO");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(37, 26, 109, 39);
		panel_2.add(lblNewLabel_1_1	);
		JLabel lblNewLabel_1_1_1 = new JLabel("Subject");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(37, 85, 109, 39);
		panel_2.add(lblNewLabel_1_1_1);
		
		textFieldsubj = new JTextField();
		textFieldsubj.setColumns(10);
		textFieldsubj.setBackground(new Color(211, 211, 211));
		textFieldsubj.setBounds(156, 85, 600, 39);
		panel_2.add(textFieldsubj);
	}
}
