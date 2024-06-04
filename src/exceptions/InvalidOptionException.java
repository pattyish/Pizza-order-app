package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wrapper.ProxyPizzerias;

public class InvalidOptionException extends MyException {

	private static final long serialVersionUID = 1L;

	public InvalidOptionException(String message) {
		super(message);
	}

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		try {
		System.out.println("OPTION NAMES EXIST!!");
		String err=" SOLVING InvalidOptionException !!";
		System.out.println(err);
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to change the name of option or you want to add a new Option??");
		System.out.println("Enter a \" 1\" to update the name \nEnter \" 2 \" to create a new option\nOr enter press ENTER exit");
		String k=reader.readLine();
		if(k.equals("1")) 
		{
			System.out.println(" Please enter the optionset where the option you want is located! ");
			System.out.println("We have "+pizzeriaAPI.getPizzaConf().getOptionsSets());

			String set = reader.readLine();
			if(pizzeriaAPI.getPizzaConf().findOptionSet(set)!=null) 
			{
				System.out.println(" Please enter old optionName you want");
				System.out.println("We have ");
				System.out.println(pizzeriaAPI.getPizzaConf().findOptionSet(set));

				String optionName = reader.readLine();
				if(pizzeriaAPI.getPizzaConf().findOption(set,optionName)!=null)
				{
					System.out.println(" Please enter a new option Name you want");
					String newpOtionName = reader.readLine();
					System.out.println(" Please enter a new option Name price increase");
					int  newprice = Integer.parseInt(reader.readLine());
					pizzeriaAPI.getPizzaConf().updateOption(set, optionName, newpOtionName, newprice);
				}
				else
					System.err.println("You entered invalid option Name!!");
			}
			else
			{
				System.err.println("You entered Invalid Optionset!!");
			}

		}
		else if(k.equals("2")) {
			System.out.println(" Please enter the optionset where the option locate! ");
			System.out.println("We have "+pizzeriaAPI.getPizzaConf().getOptionsSets());
			String set = null;
			try {
				set = reader.readLine();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			System.out.println(" Please enter a new option Name you want to add");
			String optionName = null;
			try {
				optionName = reader.readLine();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println(" Please enter a new option Name price increase");
			double newprice = 0;
			
				newprice = Double.parseDouble(reader.readLine());
		
			pizzeriaAPI.getPizzaConf().addOption(set, optionName,newprice);
		}
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}


}
