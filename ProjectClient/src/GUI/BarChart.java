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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.rmi.libraryinterface.remoteQuestions;

public class BarChart  extends JFrame {
	
	private static final long serialVersionUID = 4559865050689856038L;
	remoteQuestions remote;
	private static String title;
	private static int QID;
	@SuppressWarnings("unused")
	private static char type;
	private static int qno;
	final JFreeChart barChart ;
	
@SuppressWarnings("static-access")
public BarChart( final String applicationTitle ,final int QID, char type, final int qno) throws HeadlessException, RemoteException {
	   
	   super();
	   try {
			remote =  (remoteQuestions) Naming.lookup("rmi://localhost:1551/LibrarySurvey");
			
	   }catch(Exception ex) {
		   
				ex.printStackTrace();
				
	   }
		
    this.title=applicationTitle;
	this.QID = QID;
	this.type = type;
	this.qno = qno;

     barChart = ChartFactory.createBarChart(" ","Answer", "Frequency", createDataset(), PlotOrientation.VERTICAL,  true, true, false);
         
      JButton savePDF= new JButton("Save to JPEG");
      savePDF.addActionListener(new ActionListener() {
    	  
			public void actionPerformed(ActionEvent arg0) {
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDateTime now = LocalDateTime.now();
				int width = 640;   /* Width of the image */
			    int height = 480;  /* Height of the image */ 
			 
			      File bar = new File( "Reports/"+applicationTitle+"/"+dtf.format(now).toString()+" QuestionNo-"+Integer.toString(qno)+" Bar.jpeg" ); 
			      
			    try {
			    	  
					ChartUtilities.saveChartAsJPEG( bar , barChart, width , height );
					JOptionPane.showMessageDialog(rootPane, "Report Saved to Reports Folder");

				} catch (IOException e) {
					JOptionPane.showMessageDialog(rootPane, "Report Not Saved");

					e.printStackTrace();
				}
			}
		});
      
      ChartPanel chartPanel = new ChartPanel( barChart );  
      chartPanel.add(savePDF);
      chartPanel.repaint();

      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
    

  	JSONArray Data = null;
	JSONArray Questions = null;

	try {
		
		Data = (JSONArray) remote.getAllData();

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

		// get the object of question using question number provided

		JSONObject obj = (JSONObject) remote.getQuestion(qno);

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
		      dataset.addValue(new Double(count), a.toString() , "" );        

			
			count = 0;
		}

		return dataset;
	} catch (RemoteException e) {
		
		e.printStackTrace();
	}
	return null;
   }

}
