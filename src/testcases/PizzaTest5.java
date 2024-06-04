package testcases;
import model.PizzaConfig;

//deleting all the option in the set!
// a pizzeria of zero or negative set or also for option set

public class PizzaTest5 extends PizzaTest{


	@Override
	public void executeTest() {
		config= new PizzaConfig("AMERICANO",8000);
		
		config.addOptionSet("Size");
		config.addOption("size", "SMALL");
		config.addOption("size", "MEDIUM",2000);
		config.addOption("size", "LARGE",3500);
		
		config.addOptionSet("Delivery");
		config.addOption("Delivery", "Eat_In");
		config.addOption("Delivery", "TAKE_AWAY!",838);
		config.addOption("Delivery", "Delivered", 2000);
		
		config.addOptionSet("Saussage");
		config.addOption("Saussage", "rep_tomato");
		config.addOption("Saussage", "pepperoni_beef",740);
		config.addOption("Saussage", "anchovy_fish",640);
		
		config.addOptionSet("Topping");
		config.addOption("Topping", "Pre_Cooked_Bacon_Pieces",640);
		config.addOption("Topping", "Cooked_Beef_Topping",636);
		config.addOption("Topping", "Cooked_Italian_Sausage",730);
		config.addOption("Topping", "Cooked_Italian_Style_Meatballs",990);
	}

}
