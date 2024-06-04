package testcases;

import model.PizzaConfig;

public abstract class PizzaTest {
	
	protected PizzaConfig config;

	public PizzaConfig getConfig() {
		return config;
	}

	abstract void executeTest();
}
