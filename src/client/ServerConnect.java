package client;




/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.io.*;
import java.net.*;

public class ServerConnect
{

	static String fromUser;
	static Object fromServer;
	static ObjectOutputStream out;
	static ObjectInputStream in;

	public static Object conect(String string, String string2)  
	{
		String hostName = "localHost";
		int portNumber = 8100;

		try (Socket kkSocket = new Socket(hostName, portNumber);)
		{
			out = new 	ObjectOutputStream(kkSocket.getOutputStream());
			in = new ObjectInputStream(kkSocket.getInputStream());

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			fromServer = in.readObject();
	        while ((fromServer) != null)
	        {
	            if (fromServer.equals("Bye."))
	                break;

	            fromUser = string +" "+string2;
	            if (fromUser != null)
	            {
	                out.writeObject(fromUser);
	                fromServer = in.readObject();
	            }
	            return fromServer;
	        }
			
			
			
		}
		catch (UnknownHostException e)
		{
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		}
		catch (IOException e)
		{
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

//	public Object conect(String string, String string2)  {
//
//		fromUser = string +" "+string2;
//		while (fromUser != null)
//		{
//			System.out.println("Client: " + fromUser);
//			Object send=new Object();
//			send=fromUser;
//			try {
//				out.writeObject(send);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				fromServer = in.readObject();
//			} catch (ClassNotFoundException | IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Server: " + fromServer.toString());
//			//					if (fromServer.equals("Bye."))
//			//						break;
//			return fromServer;
//		}
//
//
//		return null;

	
}
