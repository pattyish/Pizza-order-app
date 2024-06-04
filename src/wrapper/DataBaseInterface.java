package wrapper;

import java.io.IOException;
import java.sql.SQLException;

import exceptions.MyException;
import model.PizzaConfig;

public interface DataBaseInterface {

	void createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig) throws MyException, IOException;
	void configurePizzeria(String filename) throws MyException, IOException;
	void printPizzeria(String pizzeriaName);
	void updateOptionSetName(String pizzeriaName, String optionSetname, String newName) throws MyException, IOException;
	void updateBasePrice(String pizzeriaName, double newPrice) throws MyException, IOException;
	void updateOptionPrice(String pizzeriaName, String optionName, String Option, int newPrice) throws MyException, IOException;
	void addOptionPizza(String string, String string2, String string3, double parseDouble) throws MyException, IOException;
	void deletePizzeria(String string) throws IOException, MyException;
	Object getpizzBasePrice(String string);
	Object getPizzaConf(String string);
	Object getKeys();
	Object viewSets(String string);
	
}
