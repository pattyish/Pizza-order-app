package client;
import java.io.IOException;

import javax.swing.plaf.synth.SynthSeparatorUI;

import exceptions.MyException;
import wrapper.*;

public class TestClient implements PizzeriaClient {

	ProxyPizzerias pizz;
	public TestClient() throws NumberFormatException, IOException {
		pizz= PizzeriaConfigAPI.getInstance();

	}

	@Override
	public void uploadPropertiesFile(String filename) {
		try {
			pizz.configurePizzeria(filename);
		} catch (MyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void ShowAvailablePizzerias() {
		if(pizz.getKeys().size()!=0) {
			for( String keyss:pizz.getKeys()) {
				System.out.println("- "+ keyss);
			}
		}
		else System.out.println("No pizzeria on the server!");

	}

	@Override
	public boolean PrintAPizzeria(String pizzeri) {
		if(pizz.getKeys().contains(pizzeri)) {
			pizz.printPizzeria(pizzeri);
			return true;
		}
		return false;
	}

	@Override
	public boolean DeleteAPizzeria(String pizzeria) throws MyException, IOException {
		if(pizz.getKeys().contains(pizzeria)) {
			pizz.deletePizzeria(pizzeria);
			System.out.println("Pizzeria deleted succesfully!\nLeft pizzeria");
			ShowAvailablePizzerias();
			return true;
		}
		return false;

	}                                                            
	@Override
	public void UpdateBasePrice(String pizzeria,double newPrice) throws MyException, IOException {
		if(pizz.getKeys().contains(pizzeria)) 
		{
			pizz.updateBasePrice(pizzeria, newPrice);
		}
		else System.out.println("Invalid pizzeria!");
	}
	@Override
	public boolean checkContainKeySet(String key) {
		if(pizz.getKeys().contains(key.toUpperCase())) {
			return true;
		}
		else return false;
	}
	@Override
	public void viewOptionSet(String pizzeri) {
		pizz.viewSets(pizzeri);
	}
	@Override
	public void AddOption(String pizzeria,String optionSet, String Option,double newPrice) {
		pizz.addOptionPizza(pizzeria, optionSet, Option, newPrice);
	}
}
