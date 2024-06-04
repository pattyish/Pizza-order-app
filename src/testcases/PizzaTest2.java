package testcases;

import model.PizzaConfig;
//This test case is testing the case when deleting a full set a optionSet to the full set,
//updating an option by setting the 
public class PizzaTest2 extends PizzaTest{

	@Override
	public void executeTest() {
		
		//Creating configuration
		config= new PizzaConfig("nice",8000);

		config.addOptionSet("Size");
		config.addOption("size", "SMALL");
		config.addOption("size", "MEDIUM",2000);
		config.addOption("size", "LARGE",3500);

		config.addOptionSet("Delivery");
		config.addOption("Delivery", "Eat_In");
		config.addOption("Delivery", "TAKE_AWAY!",640);
		config.addOption("Delivery", "Delivered", 1000);

		config.addOptionSet("Meat");
		config.addOption("meat", "beef",736);
		config.addOption("meat", "ham",663);
		config.addOption("meat", "pepperoni",600);
		config.addOption("meat", "anchovy",5363);

		config.addOptionSet("Vegetables");
		config.addOption("Vegetables", "mushroom",747);
		config.addOption("Vegetables", " onion",880);
		config.addOption("Vegetables", "black_olives",737);
		config.addOption("Vegetables", "pineapple",748);
		config.addOption("Vegetables", "tomatoes",3700);

	}

}
