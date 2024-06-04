package wrapper;
import java.io.IOException;

import testcases.*;
public class PizzeriaConfigAPI extends ProxyPizzerias implements DataBaseInterface
{
	private static PizzeriaConfigAPI api;
	
	private PizzeriaConfigAPI() throws NumberFormatException, IOException {
		
	}
	public static PizzeriaConfigAPI getInstance() throws NumberFormatException, IOException {
		if(api== null) {
			api=new PizzeriaConfigAPI();
			//TestDriver.runAll();
		}
		return api;
	}
} 