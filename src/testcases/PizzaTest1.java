package testcases;
import model.PizzaConfig;

//This test case is testing the case when adding a optionSet to the full set,
//updating an option by setting the name 

public class PizzaTest1 extends PizzaTest{

	@Override
	public void executeTest()    {
			

		config= new PizzaConfig("classic",9000);
		
		config.addOptionSet("Size"); 
		config.addOption("size", "SMALL");
		config.addOption("size", "MEDIUM",2000);
		config.addOption("size", "LARGE",3500);
		
		config.addOptionSet("Delivery");
		config.addOption("Delivery", "Eat_In",200);
		config.addOption("Delivery", "TAKE_AWAY!",450);
		config.addOption("Delivery", "Delivered", 1000);
		
		config.addOptionSet("Meat");
		config.addOption("meat", "beef",2800);
		config.addOption("meat", "ham",738);
		config.addOption("meat", "pepperoni",857);
		config.addOption("meat", "anchovy",6450);
		
		config.addOptionSet("Vegetables");
		config.addOption("Vegetables", "mushroom",645);
		config.addOption("Vegetables", "onion",2900);
		config.addOption("Vegetables", "black_olives",645);		
		
	}

}
