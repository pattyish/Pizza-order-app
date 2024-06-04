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
import java.util.ArrayList;
import java.util.Set;

import exceptions.MyException;
import model.PizzaConfig;

public class Client implements PizzeriaClient{

	ObjectOutputStream out;
	ObjectInputStream in;
	String userInput;
	Object fromServer;
	String fromUser;

	public Client() {		
	}


	@Override
	public void uploadPropertiesFile(String filename) throws IOException {
		Object output= ServerConnect.conect(filename,"Upload");
		String kk= output.toString();
		System.out.println(kk);
	}

	@Override
	public void ShowAvailablePizzerias() throws IOException, ClassNotFoundException {
		//		if(.size()!=0) {
		//			System.out.println("EEEEEEEE");
		//			for( String keyss:giveSet()) {
		for(String ne:giveSet())
			System.out.println(ne);
	}

	@Override
	public boolean PrintAPizzeria(String pizzeri) throws IOException, ClassNotFoundException {
		if(giveSet().contains(pizzeri)) {
			PizzaConfig configServer= (PizzaConfig) ServerConnect.conect(pizzeri, "Print");
			configServer.print();
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteAPizzeria(String pizzeria) throws MyException, IOException, ClassNotFoundException {

		if(giveSet().contains(pizzeria)) {
			Object output =ServerConnect.conect(pizzeria,"Delete");
			System.out.println(output.toString());
			System.out.println("Left pizzeria");
			ShowAvailablePizzerias();
			return true;
		}
		return false;

	}                                                            
	@Override
	public void UpdateBasePrice(String pizzeria,double newPrice) throws MyException, IOException, ClassNotFoundException {
		if(giveSet().contains(pizzeria)) 
		{
			Object	keys = ServerConnect.conect(pizzeria+":"+String.valueOf(newPrice)+"", "Price");
			System.out.println(keys.toString());
		}
		else System.out.println("Invalid pizzeria!");
	}
	@Override
	public boolean checkContainKeySet(String key) throws IOException, ClassNotFoundException {
		if(giveSet().contains(key.toUpperCase())) {
			return true;
		}
		else return false;
	} 

	@SuppressWarnings("unchecked")
	public ArrayList<String> giveSet() throws IOException, ClassNotFoundException{

		//	System.out.println("We have the following Pizzeria!!");
		Object	keys = ServerConnect.conect("Give", "keySet");
		ArrayList<String> out= (ArrayList<String>)keys;
		ArrayList<String> toRet= new ArrayList<>();
		for(String oute:out) {
			toRet.add(oute.toUpperCase());
		}
		return toRet;
	}
	@Override
	public void viewOptionSet(String pizzeri) {
		Object	keys = ServerConnect.conect(pizzeri, "viewSet");
		ArrayList<String> fromSe= (ArrayList<String>) keys;
		for(String key:fromSe) {
			System.out.println(key);
		}
	}
	@Override
	public void AddOption(String pizzeria,String optionSet, String Option,double newPrice) {
		Object	keys = ServerConnect.conect(pizzeria+":"+optionSet+":"+Option+":"+String.valueOf(newPrice), "addOption");
		System.out.println(keys.toString());
	}
}