package com.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.rmi.libraryinterface.remoteQuestions;
import com.rmi.server.QuestionServerImplementation;

public class StartServer {
	
	public static void main(String[] args) {
				
		try {
			
			System.out.println("Library Survey System is about to start ");
			
			remoteQuestions questions = new QuestionServerImplementation(); //creating a server instance
			Registry reg = LocateRegistry.createRegistry(1551); //registering to a port
			reg.rebind("LibrarySurvey", questions);//binding the to the server instance
			
			System.out.println("Server is ready !!!");
			System.out.println("!!!!! Welcome to the Library Survey System !!!! ");
			
		}catch(RemoteException e) {
			
			System.out.println("Server failiure : "+e.toString());
			
		}
		
		
	}

}
