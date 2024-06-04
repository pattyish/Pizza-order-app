package wrapper;
import model.PizzaConfig;

public interface CreatePizzeria {
	void createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig) throws Exception;
	void configurePizzeria(String filename) throws Exception;
	void printPizzeria(String pizzeriaName) throws Exception;
}
