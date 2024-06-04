package testcases;

import model.PizzaConfig;

//This test case is testing the case of finding exixtant and inexistant option or option set

public class PizzaTest3 extends PizzaTest{


	@Override
	public void executeTest() {

		config= new PizzaConfig("CHEAP",8000);

		config.addOptionSet("Size");
		config.addOption("size", "SMALL");
		config.addOption("size", "MEDIUM",2000);
		config.addOption("size", "LARGE",3500);

		config.addOptionSet("Delivery");
		config.addOption("Delivery", "Eat_In");
		config.addOption("Delivery", "TAKE_AWAY!");
		config.addOption("Delivery", "Delivered", 1000);

		config.addOptionSet("Meat_Veg_Mix");
		config.addOption("Meat_Veg_Mix", "beef_mushroom",636);
		config.addOption("Meat_Veg_Mix", "ham_onion",637);
		config.addOption("Meat_Veg_Mix", "pepperoni_black_olives",634);
		config.addOption("Meat_Veg_Mix", "anchovy_tomatoes",6363);
		
	}

}
