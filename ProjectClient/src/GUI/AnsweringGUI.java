package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rmi.libraryinterface.remoteQuestions;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;






public class AnsweringGUI extends JFrame implements ActionListener {
	
	JButton btnSubmit = new JButton("Submit");
	JButton btnBack = new JButton("Back");
	@SuppressWarnings("rawtypes")
	ArrayList input = new ArrayList();
	static JSONArray ArrayT = new JSONArray();
	static int ID = 0;
	private JPanel contentPane;
	private static final long serialVersionUID = 3373789560406912723L;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AnsweringGUI(JSONArray Array, int QID) {
		
		ArrayT = Array;
		ID = QID;

		int i = 0;
		setBounds(0, 0, 950, 200 + (Array.size() / 2) * 100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 13));
		btnSubmit.setBackground(Color.WHITE);

		btnSubmit.addActionListener(this);
		btnSubmit.setBounds(800, 60 * Array.size(), 89, 23);
		btnBack.setFont(new Font("Arial", Font.BOLD, 13));
		btnBack.setBackground(Color.WHITE);
		
		btnBack.addActionListener(this);
		btnBack.setBounds(700, 60 * Array.size(), 89, 23);
		contentPane.add(btnSubmit);
		contentPane.add(btnBack);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 900, 2);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 60 * Array.size(), 900, 13);
		contentPane.add(separator_1);

		// get all the questions
		for (i = 0; i < Array.size(); i++) {
			int temp = (i + 1);// a counter for the dynamic components and their positioning
								
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

			switch (j.get("type").toString()) {
			case "Radio":

				JComboBox<String> comboBox = new JComboBox<String>();

				
				comboBox.setBounds(400, temp * 40, 250, 20);

				comboBox.addItem("Select");

				JSONArray ans = (JSONArray) j.get("answers");

				for (Object o : ans) {
					comboBox.addItem(Integer.toString(ans.indexOf(o)) + "-"
							+ o.toString());
				}
				input.add(comboBox);

				break;
			case "Checkbox":

				ArrayList<JCheckBox> checkArray = new ArrayList<JCheckBox>();

				JSONArray ans1 = (JSONArray) j.get("answers");

				for (Object o : ans1) {
					JCheckBox temp1 = new JCheckBox(Integer.toString(ans1.indexOf(o)) + "-" + o.toString());
					temp1.setBounds(300 + 100 * (ans1.indexOf(o) + 1),temp * 40, 100, 20);
					checkArray.add(temp1);
				}
				input.add(checkArray);
				break;

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
		if (e.getSource().equals(btnSubmit)) {
						
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime now = LocalDateTime.now();
			remoteQuestions remoteServer;
			
			try {
				
				remoteServer = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
				System.out.println("Connection to the server successfull:@submit");
				
			} catch (NotBoundException | MalformedURLException | RemoteException e2) {
				
				System.out.println("Error while connecting to the server:@submit"+e2);
				e2.printStackTrace();
			}
		
			JSONArray AnswerArray = new JSONArray();
			
			boolean status = true;
			
			for (int p = 0; p < input.size(); p++) {
				
				if (input.get(p) instanceof JComboBox) {

					if (((JComboBox<String>) input.get(p)).getSelectedItem().toString().equalsIgnoreCase("Select")) {
						
						JOptionPane.showMessageDialog(this,"Please answer to the question " + (p + 1));
						status = false;
						break;

					} else {
						
						JSONObject j = (JSONObject) ArrayT.get(p);
						JSONArray ans = (JSONArray) j.get("answers");
						String answer = ((JComboBox<String>) input.get(p)).getSelectedItem().toString().substring(0, 1);

						JSONArray AnswerEach = new JSONArray();

						for (Object o : ans) {
							
							if (Integer.toString(ans.indexOf(o)).equalsIgnoreCase(answer)) {
								
								AnswerEach.add(true);
								
							} else {
								
								AnswerEach.add(false);
							}
						}
						
						AnswerArray.add(AnswerEach);

					}
				} else if (input.get(p) instanceof ArrayList) {
					
					JSONObject j = (JSONObject) ArrayT.get(p);
					JSONArray ans = (JSONArray) j.get("answers");
					JSONArray AnswerEach = new JSONArray();

					int counter = 0;
					
			for (Object b : ans) {
						
						boolean count = true;
						
				for (Object o : (ArrayList) input.get(p)) {
							
					if (((JCheckBox) o).isSelected()) {
								
						if (Integer.toString(ans.indexOf(b)).equalsIgnoreCase(Integer.toString((((ArrayList) input.get(p)).indexOf(o))))) {
								count = true;
								counter++;
								break;
						}
					} else {
							
								count = false;
					}
				}
				
						AnswerEach.add(count);
			}

				
					AnswerArray.add(AnswerEach);
				}

			}
			
			System.out.println(AnswerArray);

			if (status) {

				try {
					
					remoteServer = (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
					if (remoteServer.submitAnswers(AnswerArray,dtf.format(now))) {
						
						JOptionPane.showMessageDialog(this,"Answer Submission Successfully!");
						new FinalGUI().setVisible(true);
						this.dispose();

					} else { 
						
						JOptionPane.showMessageDialog(this,"Answer Submission Failed!");
						
					}
				} catch (RemoteException | MalformedURLException | NotBoundException e1) {
					
					JOptionPane.showMessageDialog(this,"Answer Submission Failed!"+e1);
					
				}

			}

			
		}else if(e.getSource().equals(btnBack)){
			
			ClientStartQ1 myGUI = new ClientStartQ1();
			myGUI.setVisible(true);
			this.dispose();

		}
	}

	
	}


