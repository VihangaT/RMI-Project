package com.rmi.classes;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.Message;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


import com.google.api.client.json.jackson2.JacksonFactory;








public class GmailServiceImplementation{
	
	private static final long serialVersionUID = -2048689102966996732L;

	private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	private static final String CREDENTIALS_FILE_PATH = "../resources/credentials.json";
	private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS,GmailScopes.GMAIL_SEND);

	private static NetHttpTransport httpTransport;
	Gmail service;
	
	private static Connection con;
	private static String dbusername="root";
	
	final java.util.logging.Logger buggyLogger = java.util.logging.Logger.getLogger(FileDataStoreFactory.class.getName()); 
	
	
	public GmailServiceImplementation() throws GeneralSecurityException, IOException {
		super();
		buggyLogger.setLevel(java.util.logging.Level.SEVERE); 
		System.out.println("Waiting for the connection @Email");
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

		
		

		httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials()).setApplicationName(APPLICATION_NAME)
				.build();

		String user = "me";
		ListLabelsResponse listResponse = service.users().labels().list(user).execute();
		List<Label> labels = listResponse.getLabels();
		if (labels.isEmpty()) {
			System.out.println("No labels found.");
		} else {
			System.out.println("Labels:");
			for (Label label : labels) {
				System.out.printf("- %s\n", label.getName());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private Credential getCredentials() throws IOException {
		// Load client secrets.
		InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
//		InputStream in = GmailServiceImplementation.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		
		if (in == null) {
			
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
				.Builder(httpTransport, JSON_FACTORY,clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	/**
	 * Create a MimeMessage using the parameters provided.
	 *
	 * @param to       email address of the receiver
	 * @param from     email address of the sender, the mailbox account
	 * @param subject  subject of the email
	 * @param bodyText body text of the email
	 * @return the MimeMessage to be used to send email
	 * @throws MessagingException
	 */
	public MimeMessage createEmail(String to, String from, String subject, String bodyText)
			throws MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(from));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);
		return email;
	}

	public Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}


	public  void sendMessage(String to, String subject, String bodyText)	throws MessagingException, IOException, RemoteException {
		Message message = createMessageWithEmail(createEmail(to, "me", subject, bodyText));
		message = service.users().messages().send("me", message).execute();

		System.out.println("Message id: " + message.getId());
		System.out.println(message.toPrettyString());

	}
}
