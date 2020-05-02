package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

import org.jdesktop.swingx.prompt.PromptSupport;
import org.jfree.ui.RefineryUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.rmi.libraryinterface.remoteQuestions;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.text.Document;





public class ChartViewer extends JFrame implements ActionListener {
	
	JButton btnHome = new JButton("Home");
	JButton btnGenerate = new JButton("Generate");
	JComboBox comboBoxChartTy = new JComboBox();
	JTextField QuestionNO = new JTextField();
	
	@SuppressWarnings("rawtypes")
	ArrayList input = new ArrayList();
	static JSONArray ArrayT = new JSONArray();//////////////////////////////
	static int ID = 0;////////////////////////////////////////////////////////
	private JPanel contentPane;
	private static final long serialVersionUID = 3373789560406912723L;
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ChartViewer(JSONArray Array, int QID) {
		setTitle("Report Generator");
		
		ArrayT = Array;
		ID = QID;

		int i = 0;
		setBounds(0, 0, 950, 200 + (Array.size() / 2) * 100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		PromptSupport.setPrompt("Select a Question ID", QuestionNO);
	
		comboBoxChartTy.setSize(new Dimension(10, 0));
		comboBoxChartTy.setFont(new Font("Arial", Font.BOLD, 13));
		comboBoxChartTy.setBackground(Color.WHITE);
		comboBoxChartTy.addActionListener(this);
		comboBoxChartTy.setBounds(400, 0, 142, 23);

		QuestionNO.setSize(new Dimension(10, 0));
		QuestionNO.setFont(new Font("Arial", Font.BOLD, 13));
		QuestionNO.setBackground(Color.WHITE);
		QuestionNO.addActionListener(this);
		QuestionNO.setBounds(550, 0, 138, 23);
		
		btnHome.setSize(new Dimension(10, 0));
		btnHome.setFont(new Font("Arial", Font.BOLD, 13));
		btnHome.setBackground(Color.WHITE);
		btnHome.addActionListener(this);
		btnHome.setBounds(800, 60 * Array.size(), 89, 23);
		
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 13));
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.addActionListener(this);
		btnGenerate.setBounds(700, 60 * Array.size(), 89, 23);
		
		
		contentPane.add(btnHome);
		contentPane.add(btnGenerate);
		contentPane.add(QuestionNO);
		contentPane.add(comboBoxChartTy);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 900, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 60 * Array.size(), 900, 13);
		contentPane.add(separator_1);
		
		comboBoxChartTy.addItem("Select");
		comboBoxChartTy.addItem("Pie");
		comboBoxChartTy.addItem("Bar");

		// get all the questions
		for (i = 0; i < Array.size(); i++) {
			int temp = (i + 1);// a counter for the dynamic components and their
								// positioning
			JSONObject j = (JSONObject) Array.get(i);
			JLabel question = null;
			JLabel question2 = null;
			JLabel answers = null;
			JLabel Qno = null;
			System.out.println(j.get("type").toString());

			question = new JLabel("Q" + Integer.toString(temp));
			question2 = new JLabel("Q" + Integer.toString(temp));

			Qno = new JLabel(Integer.toString(temp) + " - ");
			Qno.setBounds(10, temp * 40, 20, 20);
			question.setBounds(30, temp * 40, 350, 20);
			question2.setBounds(35, temp * 40 + 20, 350, 20);


			if (j.get("question").toString().length() > 55) {
				
				question.setText(j.get("question").toString().substring(0, 55));
				
				char c=' ';
				
				if(j.get("question").toString().charAt(54)==c){
					
					question2.setText(j.get("question").toString().substring(55));
					
				}else{
					
					question2.setText("-"+j.get("question").toString().substring(55));
	
				}

			} else {
				
				question.setText(j.get("question").toString());
				question2.setText("");
			}

			contentPane.add(Qno);
			contentPane.add(question);
			contentPane.add(question2);

			for (int p = 0; p < input.size(); p++) {
				
				if (input.get(p) instanceof JComboBox) {
					
					contentPane.add((Component) input.get(p));
					
				} else if (input.get(p) instanceof ArrayList) {
					
					for (Object o : (ArrayList) input.get(p)) {
						
						contentPane.add((JCheckBox) o);

					}
				}

			}

		}

	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnGenerate)) {
			//String []no= {"1"};
			try {
				if(QuestionNO.getText().equalsIgnoreCase("Select")&& comboBoxChartTy.getSelectedItem().toString().equalsIgnoreCase("Select")) {

					JOptionPane.showMessageDialog(rootPane,"Please Select All Fields");
					
				} else {

					
						if( comboBoxChartTy.getSelectedItem().toString().equalsIgnoreCase("Pie")){
								PieChart demo = new PieChart("Pie", 1, 'c',Integer.parseInt(QuestionNO.getText()));
											demo.setSize(560, 367);
											RefineryUtilities.centerFrameOnScreen(demo);
											demo.setVisible(true);
						
						}else if (comboBoxChartTy.getSelectedItem().toString().equalsIgnoreCase("Bar")){
								BarChart chart = new BarChart("Bar",1, 'c',Integer.parseInt(QuestionNO.getText()));
											chart.pack( );        
											RefineryUtilities.centerFrameOnScreen( chart );        
											chart.setVisible( true ); 
						}
					
				}
				
			} catch(Exception ex) {
				
				JOptionPane.showMessageDialog(rootPane,"Please Fill the Required Fields");
				ex.printStackTrace();
				
			}
			
		}
		if(e.getSource().equals(btnHome)){
			
			ClientStartQ1 myGUI = new ClientStartQ1();
			myGUI.setVisible(true);
			this.dispose();

		}
	}
}



