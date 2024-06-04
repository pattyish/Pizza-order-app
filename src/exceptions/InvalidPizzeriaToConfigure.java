package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.InputDataFile;
import wrapper.ProxyPizzerias;

public class InvalidPizzeriaToConfigure extends MyException {
	private static final long serialVersionUID = 1L;

	public InvalidPizzeriaToConfigure(String message) {
		super(message);
	}

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println("You enterd invalid file name to configure");
		System.out.println("Enter a valid filename you want to configure! with .txt extention \n Or If you don't have a file PRESS ENTER TO EXIT");

		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		String name;
		try {
			name = reader.readLine();
		
		if(name.equals("")) {
			System.out.println("You choose to leave!!");
		}
		else
		{
			InputDataFile datafile = new InputDataFile(name);
			
			//opening file
			datafile.open();
			if(!datafile.isOpen())
			{
				System.err.println("Oops, I could not open the file. :( \n FILE DOESN'T EXIST");
			}
			else {
				pizzeriaAPI.configurePizzeria(name);
			}
		}
		} catch (IOException | MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
