package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;

import com.rmi.libraryinterface.remoteQuestions;

import javax.swing.ListSelectionModel;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.border.CompoundBorder;

public class AdminHome extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField adusername;
	private JTable table;
	private JTextField adusrpassword;
	@SuppressWarnings("rawtypes")
	static String Session;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AdminHome(String Session) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1089, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(Color.YELLOW);
		layeredPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		layeredPane.setBackground(Color.YELLOW);
		contentPane.add(layeredPane, "name_952190399572100");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(518, 59, 538, 534);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Management");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		lblNewLabel.setBounds(10, 10, 223, 32);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Username\r\n");
		lblNewLabel_3.setForeground(Color.GRAY);
		lblNewLabel_3.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewLabel_3.setBounds(10, 63, 83, 13);
		panel.add(lblNewLabel_3);
		
		adusername = new JTextField();
		adusername.setBounds(10, 86, 518, 49);
		panel.add(adusername);
		adusername.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setForeground(Color.GRAY);
		lblNewLabel_4.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
		lblNewLabel_4.setBounds(10, 145, 83, 20);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Create\r\n");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				String newusername=adusername.getText();
				String newpassword=adusrpassword.getText();
				try {
					remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					boolean rs=myService.addAdmin(newusername, newpassword);
					
					if(rs) {
						JOptionPane.showMessageDialog(null,"Admin Created Successfully");
					}
				}catch(Exception ex){
					System.out.println("Error while creaing an admin");
					JOptionPane.showMessageDialog(null,"Error when creating the Admin");
				}

			}
		});
		btnNewButton.setBounds(8, 255, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String usrname=adusername.getText();
				
				try {
					remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					boolean rs3=myService.deleteAdmin(usrname);
					
					if(rs3) {
						JOptionPane.showMessageDialog(null,"Admin Removed Successfully");
					}else {
						JOptionPane.showMessageDialog(null,"Error while removing the Admin");
					}
				}catch(Exception ex){
					System.out.println("Error while removing the Admin");
					
				}
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(156, 255, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String usrname=adusername.getText();
				String newpassword=adusrpassword.getText();
				
				
				try {
					remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					boolean rs4=myService.changepwd(usrname, newpassword);
					
					if(rs4) {
						JOptionPane.showMessageDialog(null,"Admin Password Changed Successfully");
					}else {
						JOptionPane.showMessageDialog(null,"Error while changing the password");
					}
				}catch(Exception ex){
					System.out.println("Error while changing the password");
				}
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(299, 255, 85, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View All\r\n");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				
					try {
						ArrayList fetch = new ArrayList();
						int size;
						
						remoteQuestions myService =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
						fetch =(ArrayList) myService.loadAdmins();
	                    
	                    
	                    DefaultTableModel tm=(DefaultTableModel)table.getModel();
	                    size=fetch.size();
						
	                    Object rowData[]=new Object[size];
	                    tm.setRowCount(0);
	                    rowData=new Object[] {"ID","User Name","Password"};
	                    tm.addRow(rowData);
						
						for(int i=0;i<size;i+=3) {
							//int a=0;
							Object[] rowdata = new Object[] {fetch.get(i), fetch.get(i+1),fetch.get(i+2)};
							//a++;
							tm.addRow(rowdata);
							
						}
						
						System.out.println("Admin details Successfully Loaded");
//							
//						for(int i=0;i<size;i++) {
//							int a=i+1;
//							int b=i+2;
//							Object o[]= {fetch.get(i),fetch.get(a),fetch.get(b)};
//							
//							tm.addRow(o);
//							
//						}
					}catch(Exception ex) {
						System.out.println("Error while loading the admins"+ex);
						JOptionPane.showMessageDialog(null,"Error while loading the admins");
					}
					
//
//    				try {
//					Class.forName("com.mysql.jdbc.Driver");
//					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarysurvey", "root", "");
//					ResultSet rs4;
//					
//					System.out.println("Loading Admin Details");
//					
//					Statement stmt = con.createStatement();
//					rs4 = stmt.executeQuery("select * from tblogin;");
//					
//					DefaultTableModel tm=(DefaultTableModel)table.getModel();
//					tm.setRowCount(0);
//					
//					while(rs4.next()) {
//						Object o[]= {rs4.getInt("ID"),rs4.getString("username"),rs4.getString("password")};
//						tm.addRow(o);
//					}
//					System.out.println("Admins loaded successfully");
//				}catch(Exception ex) {
//					
//					System.out.println("There was an error"+ex);
//					
//				}
				
				
				
				
				
			}
		});
		btnNewButton_3.setBounds(443, 255, 85, 21);
		panel.add(btnNewButton_3);
		
		table = new JTable();
		table.setForeground(Color.GRAY);
		table.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(30, 144, 255)), null));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Username", "Password"
			}
		));
		table.setBackground(Color.WHITE);
		table.setBounds(10, 286, 518, 238);
		panel.add(table);
		
		adusrpassword = new JTextField();
		adusrpassword.setBounds(10, 172, 518, 49);
		panel.add(adusrpassword);
		adusrpassword.setColumns(10);
		
		JButton btnNewButton_2_1 = new JButton("Log out");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					remoteQuestions	myService = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					String rs=myService.logout(Session);
					
					System.out.println("Admin has logged out:"+rs);
					
					ClientStartQ1 cli=new ClientStartQ1();
					cli.setVisible(true);
					dispose();
				}catch(Exception ex) {
					
					System.out.println("Error while logging out"+ex);
				}
				
				
				
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2_1.setBounds(443, 19, 85, 21);
		panel.add(btnNewButton_2_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(10, 59, 483, 534);
		layeredPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Controls");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		lblNewLabel_1.setBounds(10, 10, 195, 28);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Analyse Data");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//loading questions to the report generator
				try {
					int temp = 1;
					remoteQuestions	myService = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					JSONArray objjs = myService.getAllQuestions();
					
					new ChartViewer(objjs, temp).setVisible(true);
					dispose();

				} catch (RemoteException | MalformedURLException | NotBoundException er) {
					JOptionPane.showMessageDialog(null,"Server is unavailable!");
					er.printStackTrace();
				} 
			}
		});
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setForeground(Color.DARK_GRAY);
		btnNewButton_4.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		btnNewButton_4.setBounds(10, 70, 463, 36);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Send Mail");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MailSender ms=new MailSender(Session);
				ms.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.setForeground(Color.DARK_GRAY);
		btnNewButton_5.setFont(new Font("Tw Cen MT", Font.BOLD, 22));
		btnNewButton_5.setBounds(10, 129, 463, 36);
		panel_1.add(btnNewButton_5);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("E:\\sliit\\SLIIT\\3rd year\\UOB\\CIS\\sandya\\14032020\\ProjectClient\\pic\\data3.jpg"));
		lblNewLabel_5.setBounds(10, 175, 463, 349);
		panel_1.add(lblNewLabel_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 10, 1046, 39);
		layeredPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Admin Control Center\r\n");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tw Cen MT", Font.BOLD, 21));
		panel_2.add(lblNewLabel_2);
	}

	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
