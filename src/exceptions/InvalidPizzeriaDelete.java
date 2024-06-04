package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wrapper.ProxyPizzerias;

public class InvalidPizzeriaDelete extends MyException {

	public InvalidPizzeriaDelete(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println("Please Enter the valid pizzeria to delete!! if not Click ENTER TO CONTINUE!");
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		String name = null;
		try {
			name = reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(name!= null && pizzeriaAPI.getKeys().contains(name) ) {
		try {
			pizzeriaAPI.deletePizzeria(name);
		} catch (MyException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else {
			System.out.println("Nothing deleted! INVALID INPUT\n\n");
		}
		
	}

	

}
