package protocol;

import java.io.IOException;
import java.util.Set;

import exceptions.MyException;
import model.PizzaConfig;
import wrapper.DataBaseInterface;
import wrapper.PizzeriaConfigAPI;
import wrapper.ProxyPizzerias;

public abstract class ProtocolFactory extends MyFactory
{

	public ProtocolFactory() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	public abstract Object fixRequest(DataBaseInterface proxyPi, String string) ;
	
}
