package scaletests;


import java.io.IOException;
import java.util.LinkedHashMap;

import exceptions.Factory;
import exceptions.MyException;
import io.ioClass;
import model.PizzaConfig;
import wrapper.ProxyPizzerias;

public class UpdatePrintUser extends SimulatedUser{
	public UpdatePrintUser(ProxyPizzerias configAPI) {
		API=configAPI;
		new Thread(this,"2ndThread").start();
	}

	@Override
	public void run()
	{
		
			
			try {
				API.updateBasePrice("AMERICANO", 500000);
				API.printPizzerias();
				Thread.sleep(3000);
			} catch (Exception e) {
				try {
					Factory.fixError(API, e);
				} catch (NumberFormatException | MyException | IOException e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("Thread "+Thread.currentThread().getName()+ " Finished!");
		
	}
}

