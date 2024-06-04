package scaletests;


import java.io.IOException;
import java.util.LinkedHashMap;

import exceptions.Factory;
import exceptions.MyException;
import model.PizzaConfig;
import wrapper.ProxyPizzerias;

public class DeleteAndPrintUser extends SimulatedUser {

	public DeleteAndPrintUser(ProxyPizzerias linkedHashMap) {
		API=linkedHashMap;
		new Thread(this,"1stThread").start();
	}

	@Override
	public void run() {


		try 
		{
			API.deletePizzeria("CLASSIC");
			System.out.println("Thread "+Thread.currentThread().getName()+ " Finished!");
			Thread.sleep(3000);
		} catch (MyException | IOException | InterruptedException e) {
			try {
				Factory.fixError(API, e);
			} catch (NumberFormatException | MyException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			API.printPizzerias();
		} catch (MyException | IOException e) {
			try {
				Factory.fixError(API, e);
			} catch (NumberFormatException | MyException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		






	}



}
