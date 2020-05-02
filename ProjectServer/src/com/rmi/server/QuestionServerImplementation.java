package com.rmi.server;

import java.awt.Label;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import com.rmi.classes.GmailServiceImplementation;
import com.rmi.libraryinterface.remoteQuestions;



import com.google.api.client.json.jackson2.JacksonFactory;
public class QuestionServerImplementation extends UnicastRemoteObject implements remoteQuestions {

	private static final long serialVersionUID = 1L;
	private static Connection con;
	private String sessionCookie = "abc" + Math.random();
	private static String dbusername="root";
	
	
	
	
	
	public QuestionServerImplementation() throws RemoteException {
		super();

		
		System.out.println("Waiting for the connection");
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarysurvey", dbusername, "");
			
			System.out.println("Successfully connected to the database");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("Error while connecting to the databse" + e);
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			System.out.println("Error while connecting to the databse" + e);
			e.printStackTrace();
		}

	}

	@Override
	public JSONObject getQuestion(int i) throws RemoteException {

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `questions`");

			JSONParser parser = new JSONParser();
			JSONArray Array = null;

			while (rs.next()) {
				Array = (JSONArray) parser.parse(rs.getString("question"));
			}
			// con.close();
			return (JSONObject) Array.get(i);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public JSONArray getAllQuestions() throws RemoteException {
		
		System.out.println("Attempting to retrive questions");
		try {

			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM `questions`");

			JSONParser parser = new JSONParser();
			JSONArray Array = null;

			while (result.next()) {
				
				Array = (JSONArray) parser.parse(result.getString("question"));
			}
			
			System.out.println("Question Retrival Successfull");
			return Array;
			
		} catch (Exception ex) {
			
			System.out.printf("There was an issue when retriving the questions", ex);
			
			return null;
		}
	}
	
	
	@Override
	public boolean submitAnswers(JSONArray AnswerObject,String Date) throws RemoteException {
		
		boolean stat = false;
		int ping = 0;

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM `questions`");

			while (rs.next()) {
				ping = rs.getInt("ping");
				stat = true;
			}

			stmt.executeUpdate("UPDATE `questions` SET `ping`=" + (++ping) + " WHERE `QID`= '" + 1 + "'");

			stmt.executeUpdate("INSERT INTO `answers` (`ID`, `QID`, `Answers`, `Date`) VALUES (NULL, '" + 1 + "', '"+ AnswerObject + "', '" + Date + "')");
			
			System.out.println("submitAnswers Successfully Executed");
			
			return stat;

		} catch (Exception e) {
			
			System.out.println("Error While submitting Answers"+e.getMessage());
			
			return false;
		}

	}

	@Override
	public boolean login(String Username, String Password) throws RemoteException {

		boolean result = false;
		try {
			System.out.println("Login Attempt");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tblogin where username = '" + Username + "';");

			while (rs.next()) {
				
				if (rs.getString("password").equalsIgnoreCase(Password)) {
					result = true;

				} else {
					result = false;
				}
			}
			
			
			return result;
			
		} catch (Exception e) {
			
			System.out.println("Error While login"+e);
			return false;

		}

	}

	@Override
	public String SessionLogin(boolean status) throws RemoteException {

		String check = "true";
		String Status = Boolean.toString(status);

		if (Status.equals(check)) {

			sessionCookie = "xyz" + Math.random();
			System.out.println("SessionLogin successfully executed");
			return sessionCookie;
			
		} else {

			return "You have to login to proceed";

		}

	}

	@Override
	public String logout(String cookie) throws RemoteException {

		System.out.println("The user session has been destroyed");

		return null;
	}

	@Override
	public ArrayList getallclients() throws RemoteException {

		String email = "";

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from participants");

			
			ArrayList fetch = new ArrayList();
            while(rs.next()){
                
            	
            	fetch.add(rs.getString(3));
			
			
			
			
			
		
			}
			
			System.out.println("getallclients successfully executed");
			return fetch;
			
		} catch (Exception ex) {
			
			System.out.println("Error in getallclients"+ex);
			return null;
		}
	}



	@Override
	public String TestConnection() throws RemoteException {
		//testing the server connection
		return "Established";
	}

	@Override
	public boolean addAdmin(String newuser, String newpassword) throws RemoteException{
		
		try{
			System.out.println("Attempt to create a new admin");
			
			Statement stmt = con.createStatement();
			String sql="INSERT INTO `tblogin`(`id`,`username`, `password`, `type`) VALUES (NULL,'"+newuser+"','"+newpassword+"','"+"admin"+"')";
			stmt.executeUpdate(sql);
			return true;
						
		}catch(Exception ex) {
			
			System.out.println("Error while creating the admin"+ex);
			return false;
		}
		
		
	}

	@Override
	public boolean deleteAdmin(String username) {
		
		try{
			
			System.out.println("Attempt to remove an admin");
			
			Statement stmt = con.createStatement();
			String sql="Delete from `tblogin` WHERE `username`='"+username+"'";
			stmt.executeUpdate(sql);
			return true;
			
		}catch(Exception ex) {
			
			System.out.println("Error while removing the admin"+ex);
			return false;
			
		}
	}

	@Override
	public boolean changepwd(String username, String Password) {

		try {

			System.out.println("Attempt to change a password");

			Statement stmt=con.createStatement();
			String sql="UPDATE `tblogin` SET `password`='"+Password+"' WHERE `username`='"+username+"'";
			stmt.executeUpdate(sql);
			
			return true;

		} catch (Exception ex) {
			
			System.out.println("Error while changing the password"+ex);
			return false;
		}
	}

	@Override
	public ArrayList loadAdmins() throws RemoteException {
		
		try {
			
			ResultSet rs;
			
			System.out.println("Loading Admin Details");
			
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select * from tblogin;");
			
			ArrayList grab = new ArrayList();
            while(rs.next()){
                
            	grab.add(rs.getInt(1));
            	grab.add(rs.getString(2));
            	grab.add(rs.getString(3));
                
            }   
            return grab;
			
		}catch(Exception ex) {
			
			System.out.println("Error While loading Admin Details");
			return null;
			
		}
		
	}



	@Override
	public boolean saveParticipants(String name, String email) throws RemoteException {

		try{
			
			System.out.println("Inserting participants details");
			
			Statement stmt = con.createStatement();
			String sql="INSERT INTO `participants`(`id`, `name`, `email`) VALUES (NULL,'"+name+"','"+email+"')";
			stmt.executeUpdate(sql);
			return true;
			
		}catch(Exception ex) {
			
			System.out.println("Error while inserting participants details"+ex);
			return false;
		}
		
	}



	@Override
	public boolean sendEmail(String to, String subject, String bodyText)throws MessagingException, Exception,RemoteException,IOException, GeneralSecurityException  {
		try {
			
		GmailServiceImplementation RMI=new GmailServiceImplementation();
		RMI.sendMessage(to,subject,bodyText);
		return true;
		
		}catch(Exception ex) {
			
		System.out.println("Error While sending email at server");
		ex.printStackTrace();
		return false;
		
		}
		
	}



	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getAllData() throws RemoteException {
		JSONArray Array = new JSONArray();
		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `answers`");
			JSONParser parser = new JSONParser();

			while (rs.next()) {
				Array.add((JSONArray) parser.parse(rs.getString("answers")));
			}
			// con.close();
			return Array;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
		
		
	

	

}
