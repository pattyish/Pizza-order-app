package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wrapper.ProxyPizzerias;

public class DuplicatedOptionSetNameException extends MyException {
	private static final long serialVersionUID = 1L;

	public DuplicatedOptionSetNameException(String message)  {
		super(message);
	}

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println("OPTIONSET NAME EXIST!!");
		String err=" SOLVING DuplicatedOptionSetNameException !!";
		System.out.println(err);


		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Please enter a optionset Name you want to update");
		System.out.println("We have "+pizzeriaAPI.getPizzaConf().getOptionsSets());
		String currentString = null;
		try {
			currentString = reader.readLine();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(" Please enter a new optionset Name you want!");
		String newString = null;
		try {
			newString = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pizzeriaAPI.getPizzaConf().updateOptionSet(currentString, newString);
		
	}
}
