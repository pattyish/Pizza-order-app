package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import protocol.MyFactory;
import protocol.ProtocolFactory;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;



public class ClientHandler extends Thread
{
	static int clientNumber = 0;

	ObjectInputStream request;
	ObjectOutputStream response;
	MyFactory protocolFactory= new MyFactory();
	DataBaseInterface proxyPi;
	public ClientHandler(DataBaseInterface pizz, Socket clientSocket) throws IOException
	{ 
		request = new ObjectInputStream(clientSocket.getInputStream());
		response = new 	ObjectOutputStream(clientSocket.getOutputStream());                   
		clientNumber++;
		setName("Client " + clientNumber);
		proxyPi=pizz;
	}

	public void run()
	{
		Object inputObject;
		try
		{
			response.writeObject(" ");
			while ((inputObject = request.readObject()) != null)
			{
				Object toClient= new Object();
				String in=(String) inputObject;				
				String []keywords=in.split(" ");
				
				if(keywords.length==2) 
				{
					ProtocolFactory protocol =protocolFactory.checkRequest(keywords[0],keywords[1]);
					toClient = protocol.fixRequest(proxyPi, keywords[0]);
					response.writeObject(toClient);
					response.flush();
				}
				else 
				{
					ProtocolFactory protocol =protocolFactory.checkRequest("nothing","nothing");

					toClient = protocol.fixRequest(proxyPi,keywords[0]);
					response.writeObject(toClient);
					response.flush();
				}
			}
		} catch (NumberFormatException | IOException e) {

		}
		catch ( ClassNotFoundException e)
		{
			System.err.println("Exception caught when trying to read requests");
			e.printStackTrace();
			System.err.println(e.getMessage());

		} 
		System.out.println("Server finished handling " + Thread.currentThread().getName());
	}
}
	
