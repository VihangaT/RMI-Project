/*GroupID:16
 * Group Members:
 * 				Vihanga Thathsara Kaluarachchi Liyanage : 1940916
 * 				vindya sisindi asarappulige silva       : 1940907 
 */
package com.rmi.libraryinterface;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.mail.MessagingException;

public interface remoteQuestions extends Remote {
	
	public JSONObject getQuestion(int i) throws RemoteException;
	
	public JSONArray getAllQuestions() throws RemoteException;
	/*
	 * Takes all the questions and answers to a JASON array and passes to the client side
	 */
	
	public boolean submitAnswers(JSONArray AnswerObject,String Date)throws RemoteException;
	/*
	 *  submit answer for the questions which will be saved in the databse
	 *  @param AnswerObject is for the Answer
	 *  @param Date is for the recorded date
	 *  @throws RemoteException
	 *  
	 */
	
	public boolean login(String username, String password) throws RemoteException;
	/*
	 *  Login for the admin which will be validated with the database
	 *  @param username is for the admin username
	 *  @param password is for the admin account password
	 *  @throws RemoteException
	 */
	
	
	public boolean sendEmail(String to, String subject, String bodyText) throws MessagingException, Exception ,RemoteException;
	/*
	 *  Sends a mail to the client
	 *  @param to is for the email address of the receiver
	 *  @param subject is for the subject of the email
	 *  @param bodyText is for the content of the email
	 *  @throws RemoteException,IOException
	 */
	
	
	
	public ArrayList getallclients() throws RemoteException;
	/*
	 *  get all the details of the participants who answered the questions
	 *  @throws RemoteException,IOException
	 */
	
	public String SessionLogin(boolean status) throws RemoteException;
	/*
	 * Creates a session for the loged in user after the username and the password is validated by the login module.
	 * @param status is for the validated data returned from the loged in user
	 * @throws RemoteException
	 */
	
	public String logout(String cookie) throws RemoteException;
	/*
	 * Destroys the cookie created when the user login
	 * @param is for the created cookie
	 * @throws RemoteException
	 */
	
	public String TestConnection() throws RemoteException;
	/*
	 * Test the connection status with the server
	 * @throws RemoteException
	 */
	
	public boolean addAdmin(String newuser,String newpassword)throws RemoteException;
	/*
	 * Creates new admin users to the system from the Admin Control Center
	 * param newuser for the new admin username
	 * param newpassword for the new admin password
	 * @throws RemoteException
	 */
	public boolean deleteAdmin(String username)throws RemoteException;
	/*
	 * 
	 * Deletes an admin users
	 * param username is the username of the admin account which should be deeleted
	 * @throws RemoteException
	 */
	public boolean changepwd(String username, String Password)throws RemoteException;
	/*
	 * change password of an admin
	 * param username for the existing admin username
	 * param Password for the new admin password
	 * @throws RemoteException
	 */
	
	public ArrayList loadAdmins()throws RemoteException;
	/*
	 * Takes all the details of the admins into an array and passes to the client
	 * @throws RemoteException
	 */
	
	public boolean saveParticipants(String name,String email)throws RemoteException;
	/*
	 * Takes all the details of the admins into an array and passes to the client
	 * param name for the survey participants name
	 * param email for the survey participants email
	 * @throws RemoteExceptiont
	 */
	
	public JSONArray getAllData() throws RemoteException;
	/*
	 * Get all the data on the survey for the use of creating charts
	 * @throws RemoteException
	 */
}

