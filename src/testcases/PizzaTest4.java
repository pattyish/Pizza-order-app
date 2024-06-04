package testcases;
import model.PizzaConfig;

//This test case is testing the case UPDATING THE WHOLE OPTION SET NAME AND SIZE!

public class PizzaTest4 extends PizzaTest{


	@Override
	public void executeTest() {

		config= new PizzaConfig("ESPANIA",8000);

		config.addOptionSet("Size");
		config.addOption("size", "SMALL");
		config.addOption("size", "MEDIUM",2000);
		config.addOption("size", "LARGE",3500);

		config.addOptionSet("Delivery");
		config.addOption("Delivery", "Eat_In");
		config.addOption("Delivery", "TAKE_AWAY!",477);
		config.addOption("Delivery", "Delivered", 1000);

		config.addOptionSet("Drinks");
		config.addOption("Drinks", "Heineken",1000);
		config.addOption("Drinks", "Orane",700);
		config.addOption("Drinks", "lagagga",748);
		config.addOption("Drinks", "Primus",1439);

		config.addOptionSet("Vegetables");
		config.addOption("Vegetables", "mushroom",747);
		config.addOption("Vegetables", "pineapple",100);
		config.addOption("Vegetables", "tomatoes",6000);


	}

}
