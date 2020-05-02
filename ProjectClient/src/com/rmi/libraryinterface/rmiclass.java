package com.rmi.libraryinterface;

import java.rmi.*;

import GUI.*;

public class rmiclass {
	
	public static void main(String[] args) {
		
		remoteQuestions hello;
		
		String name= "rmi://localhost:1087/HelloServer";
		
		try {
			hello = (remoteQuestions)Naming.lookup(name);
			System.out.println("client is ready");
		}
		catch (Exception e) {
			System.out.println("Hello Client exception: " +e);
		}
		
		
	}
	

}
