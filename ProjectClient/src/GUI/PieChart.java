package GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.json.simple.JSONArray;
import org.jfree.chart.ChartUtilities;
import org.json.simple.JSONObject;

import com.rmi.libraryinterface.remoteQuestions;




public class PieChart extends JFrame {


	private static final long serialVersionUID = 7505520148489089500L;

	static remoteQuestions remote;

	private static String title;
	private static int QID;
	@SuppressWarnings("unused")
	private char type;
	private static int qno;
	private static JFreeChart chartGlobal ;
	@SuppressWarnings("static-access")
	public PieChart(final String title, final int QID, char type, final int qno) throws HeadlessException, RemoteException {

		 super();
		   try {
				remote =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
				
		   }catch(Exception ex) {
					ex.printStackTrace();
		   }
		this.title=title;
		this.QID = QID;
		this.type = type;
		this.qno = qno;

		JButton savePDF= new JButton("Save to JPEG");
		
		JPanel temp=createDemoPanel();
		temp.setBounds(200,200, 100, 20);

		savePDF.setBounds(100,100, 100, 20);
		savePDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				int width = 640;   /* Width of the image */
			    int height = 480;  /* Height of the image */ 
			      File pieChart = new File( "Reports/"+title+"/" +dtf.format(now).toString()+" QuestionNO-"+Integer.toString(qno)+" Pie.jpeg" ); 
			      
			      try {
			    	  
					ChartUtilities.saveChartAsJPEG( pieChart , chartGlobal, width , height );
					JOptionPane.showMessageDialog(rootPane, "Report Saved to Reports Folder");
					
				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Report Not Saved");

					e.printStackTrace();
				}
			}
		});
		temp.add(savePDF);
		temp.repaint();
		setContentPane(temp);
	     
		
	}

	private static PieDataset createDataset() {
		JSONArray Data = null;
		JSONArray Questions = null;

		try {


			Data = (JSONArray) remote.getAllData();
		
			
			Questions = (JSONArray) remote.getAllQuestions();

			DefaultPieDataset dataset = new DefaultPieDataset();

			// get the object of question using question number provided
			JSONObject obj = (JSONObject) Questions.get(qno);
			JSONArray arr = (JSONArray) obj.get("answers");
			title = obj.get("question").toString();

			for (Object a : arr) {
				
				int count = 0;
				// each user input
				for (Object d : Data) {

					JSONArray f = (JSONArray) d;
					// get the data for question number
					JSONArray g = (JSONArray) f.get(qno);

					// do the counting of response for that answer
					if ((boolean) g.get(arr.indexOf(a))) {
						count++;
					}
				}

				dataset.setValue(a.toString(), new Double(count));
				count = 0;
				
			}

			return dataset;
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart(" ",dataset,true,true, false);

		chartGlobal=chart;
		return chart;
	}

	public static JPanel createDemoPanel() {
		
		JFreeChart chart = createChart(createDataset());
		return new ChartPanel(chart);
	}

}