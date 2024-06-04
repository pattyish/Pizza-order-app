package client;

import java.io.IOException;

import exceptions.MyException;

public interface PizzeriaClient {

	void uploadPropertiesFile(String filename) throws IOException;
	void ShowAvailablePizzerias() throws  Exception;
	boolean PrintAPizzeria(String pizzeri) throws IOException, ClassNotFoundException;
	boolean DeleteAPizzeria(String pizzeria) throws MyException, IOException, ClassNotFoundException;
	boolean checkContainKeySet(String key) throws IOException, ClassNotFoundException;
	void UpdateBasePrice(String key, double base) throws MyException, IOException, ClassNotFoundException;
	void AddOption(String pizzeria,String optionSet, String Option,double newP);
	void viewOptionSet(String pizzeri);
}
