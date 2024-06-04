package exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import wrapper.ProxyPizzerias;

public class BasePriceMissingException extends MyException{
	private static final long serialVersionUID = 1L;

	public BasePriceMissingException(String message) {
		super(message);
	}

	@Override
	void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println("Base price is missing!");
		String err=" SOLVING BasePriceMissingException !!";
		System.out.println(err);

		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Please enter the pizzeria BASE PRICE");
		double price = 0;

		try {
			price = Double.parseDouble(reader.readLine());
		} catch (NumberFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		pizzeriaAPI.getPizzaConf().setBasePrice(price);
		
	}
}




