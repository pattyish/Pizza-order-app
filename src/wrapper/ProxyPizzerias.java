package wrapper;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import exceptions.Factory;
import exceptions.MyException;
import io.ioClass;
import model.PizzaConfig;

public abstract class ProxyPizzerias
{


	private LinkedHashMap <String,PizzaConfig> pizzeria= new LinkedHashMap<String,PizzaConfig>();
	int k=0;
	String nameOfPizzeria;
	PizzaConfig pizzaConf;

	public synchronized void createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig) throws MyException, IOException{
		if(pizzeriaName.equalsIgnoreCase("")) 
		{
			setNameOfPizzeria(" ");
			pizzaConf=pizzaConfig;
			throw new Factory().exceptionFactory("ConfigNameMiss");
		}
		if(pizzeriaName !="") {
			setNameOfPizzeria(pizzeriaName);
			pizzaConf=pizzaConfig;
			pizzeria.put(pizzeriaName, pizzaConfig);

		}
	}


	public  synchronized void configurePizzeria(String filename) throws MyException, IOException {
		if(ioClass.buildPizzaConfig(filename)==null) 
		{
			System.out.println("Invalid file name");
			throw new Factory().exceptionFactory("File Not Found!");
		}
		else
		{
			pizzaConf=ioClass.buildPizzaConfig(filename);
			createPizzeria(pizzaConf.getConfigName(), pizzaConf);
		}

	}


	public  synchronized  void printPizzeria(String pizzeriaName) {

		System.out.println("*************** WELCOME IN NEW PIZZERIA MODEL**************");
		System.out.println("Pizzerria");
		System.out.println("NAME: "+pizzeriaName+ " \nBasePrice =  " + pizzeria.get(pizzeriaName).getBasePrice() );
		for(String me:pizzeria.keySet()) 
		{
			if(pizzeriaName.equalsIgnoreCase(me)) {
				pizzeria.get(me).print();
			}
		}


	}

	public  void updateOptionSetName(String configName, String optionSetname, String newName) throws MyException, IOException {

		if(optionSetname.equalsIgnoreCase(newName)) {
			throw new Factory().exceptionFactory("OptionSet");
		}
		if(pizzeria.keySet().contains(configName)) {
			PizzaConfig key= pizzeria.get(configName);
			key.updateOptionSet(optionSetname, newName);
			System.out.println(" DONE@ --> Updated");
		}
		else 
			System.out.println("No Pizzeria Found!");
	}

	public  synchronized void updateBasePrice(String configName, double newPrice) throws MyException, IOException 
	{
		if(newPrice==0) {
			throw new Factory().exceptionFactory("BasePrice");
		}
		for(String val:pizzeria.keySet()) {
			if(val.equalsIgnoreCase(configName)) {
				pizzeria.get(val).setBasePrice(newPrice);
			}
		}
	}

	public ArrayList<String> viewSets(String pizzeri) {
		for(PizzaConfig con:pizzeria.values()) {
			if(con.getConfigName().equalsIgnoreCase(pizzeri)) {
				ArrayList<String> toSend=con.viewOptionSet();
				return toSend;
			}
		}
		return null;
	}

	public  void updateOptionPrice(String configName, String optionName, String Option, int newPrice) throws MyException, IOException {
		if(newPrice==0 | Option=="") {
			throw new Factory().exceptionFactory("Option");
		}
		if(configName.equalsIgnoreCase("")) {
			throw new Factory().exceptionFactory("ConfigNameMiss");
		}
		for(PizzaConfig val:pizzeria.values()) {
			if(val.getConfigName().equalsIgnoreCase(configName)) {

				val.updateOption(optionName, Option, Option, newPrice);
			}
		}

	}
	public  synchronized void deletePizzeria(String name) throws  IOException, MyException {

		if(!pizzeria.keySet().contains(name)) {
			throw new Factory().exceptionFactory("Delete not found");
		}
		for(String conf: pizzeria.keySet())
		{
			if(conf.equalsIgnoreCase(name)) {
				pizzeria.remove(name);
				break;
			}
		}

	}	
	public PizzaConfig getPizzaConf() {
		return pizzaConf;
	}

	public PizzaConfig getPizzaConf(String name) {
		for(PizzaConfig conf:pizzeria.values())
			if(conf.getConfigName().equalsIgnoreCase(name)) {
				return conf;
			}
		return null;
	} 

	public void setNameOfPizzeria(String nameOfPizzeria) {
		this.nameOfPizzeria = nameOfPizzeria;
	}

	public  ArrayList<String> getKeys(){
		
		ArrayList<String> keysss=new ArrayList<>();
		keysss.addAll(pizzeria.keySet());
		
		return keysss;
	}
	public synchronized void printPizzerias() throws MyException, IOException {
		if(pizzeria.isEmpty()) 
		{
			throw new Factory().exceptionFactory("Empty pizzera!");
		}
		else 
		{
			for(String keys:pizzeria.keySet()) 
			{
				pizzeria.get(keys).print();
			}
		}
	}
	public synchronized void addOptionPizza(String pizzer,String optionSet, String Option,double newPrice) {
		for(PizzaConfig keysi:pizzeria.values()) {
			if(keysi.getConfigName().equalsIgnoreCase(pizzer)) {
				keysi.addOption(optionSet, Option, newPrice);
				break;
			}
		}
	}


	public synchronized void deleteAllPizzeria() {

		pizzeria.clear();

	}

	public String getpizzBasePrice(String mine) {
		for(PizzaConfig keysi:pizzeria.values()) {
			if(keysi.getConfigName().equalsIgnoreCase(mine)) {
				double price= keysi.getBasePrice();
				return String.valueOf(price);
			}

		}
		return null;
	}
}
