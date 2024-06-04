package testcases;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import exceptions.Factory;
import exceptions.MyException;
import model.PizzaConfig;
import scaletests.*;
import wrapper.PizzeriaConfigAPI;
import wrapper.ProxyPizzerias;

public class TestDriver 
{ 
	public static ArrayList<PizzaConfig> giveMeConfigs() throws NumberFormatException, IOException 
	{
		ProxyPizzerias configAPI= PizzeriaConfigAPI.getInstance();
		ArrayList<PizzaConfig> configs= new ArrayList<PizzaConfig>();				 
		ArrayList <PizzaTest> pizzaTest= new ArrayList<PizzaTest>();

		pizzaTest.add(new PizzaTest1()); 
		pizzaTest.add(new PizzaTest2()); 
		pizzaTest.add(new PizzaTest3()); 
		pizzaTest.add(new PizzaTest4()); 
		pizzaTest.add(new PizzaTest5()); 



		for(int i=0; i<pizzaTest.size();i++) {
			if(pizzaTest.get(i)!=null)
			{
				try {
					pizzaTest.get(i).executeTest();


					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Case"+(i+1)+".dat"));
					out.writeObject(pizzaTest.get(i).getConfig());
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		for(int i=0;i<pizzaTest.size();i++) {

			try {
				if(pizzaTest.get(i)!=null)
				{
					@SuppressWarnings("resource")
					ObjectInputStream in = new ObjectInputStream(new FileInputStream("Case"+(i+1)+".dat"));
					PizzaConfig newConfig = (PizzaConfig) in.readObject();
					configs.add(newConfig);
				} 
			}
			catch (Exception e) {
				e.printStackTrace();

			}
		}
		return configs;
	}
}