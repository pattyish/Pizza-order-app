package server;




/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;

import wrapper.DataBaseInterface;
import wrapper.PizzeriaConfigAPI;
import wrapper.ProxyPizzerias;
import wrapper.DatabaseConfigAPI;

import java.io.*;

public class Server {

	static ClientHandler handler;
	public static void main(String[] args) throws IOException, ClassNotFoundException 
	{
		
		DataBaseInterface pizz = null;
		System.out.println("Welcome in our OWESOME pizza order system");
		System.out.println("Enter 1 if you want to load DATABASE or \nEnter 2 if you want to load LIINKEDHASHMAP");
		System.out.println("Press enter to exit");
		System.out.println("\n*****  Waiting for client to connect!!  ****");
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		String in = reader.readLine();
		while(!in.equals("1") && !in.equals("2") && !in.equals("")) 
		{
			System.out.println("You entered wrong input");
			System.out.println("Enter 1 if you want to load DATABASE or \nEnter 2 if you want to load LIINKEDHASHMAP");
			System.out.println("Press enter to exit");
			System.out.println("\n*****  Waiting for client to connect!!  ****");
			in=reader.readLine();
		}
		if(in.equals("1")) {
			pizz= DatabaseConfigAPI.getInstance();
		}
		else if(in.equals("2")) 
		{
			pizz= PizzeriaConfigAPI.getInstance();
		}
		else if(in.equals("")) {
			System.out.println("You choses to exit!!\nTHANK YOUUUU!!");
		}
		Socket    clientSocket = null;
		int portNumber;
		boolean input=true;
		System.out.println("CONNECTED!");
		portNumber = 8100;
		try{
			ServerSocket serverSocket = new ServerSocket(portNumber);

			while(input) {
				clientSocket = acceptConnection(serverSocket);
				if(clientSocket !=null) 
				{
					handler = new ClientHandler(pizz,clientSocket);
					handler.start();
				}
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("The Server is ending.");
	}

	private static Socket acceptConnection(ServerSocket socket)
	{

		try
		{
			// blocked on an I/O until a connection is made
			Socket clientSocket = socket.accept(); 
			System.out.println("You are connected!!");
			return clientSocket;
		}
		catch (SocketTimeoutException e)
		{
			System.out.println("Server timed out waiting for a connection.");
		}
		catch (IOException e)
		{
			System.err.println("Server caught exception when trying to listen on port "
					+ socket.getLocalPort() );
			System.err.println(e);
		}
		return null;
	}
}

