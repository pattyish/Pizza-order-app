package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wrapper.ProxyPizzerias;

public class NameMissingException extends MyException {

	private static final long serialVersionUID = 1L;

	public NameMissingException(String message) {
		super(message);
		
	}

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println("INVALID PIZZERIA NAME!!!");
		String err=" SOLVING NameMissingException !!";
		System.out.println(err);

		System.out.println("Please Enter a valid Pizzeria Name");
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		String name = null;
		try {
			name = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pizzeriaAPI.setNameOfPizzeria(name);
		try {
			pizzeriaAPI.createPizzeria(name, pizzeriaAPI.getPizzaConf());
		} catch (MyException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
