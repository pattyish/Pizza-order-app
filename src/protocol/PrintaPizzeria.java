package protocol;

import java.io.IOException;

import model.PizzaConfig;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class PrintaPizzeria extends ProtocolFactory {



	public PrintaPizzeria() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		return pizz.getPizzaConf(string);
	}

}
 