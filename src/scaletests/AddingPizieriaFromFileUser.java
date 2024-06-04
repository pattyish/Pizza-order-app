package scaletests;

import java.io.IOException;

import exceptions.Factory;
import exceptions.MyException;
import io.ioClass;
import wrapper.ProxyPizzerias;

public class AddingPizieriaFromFileUser extends SimulatedUser{

	public AddingPizieriaFromFileUser(ProxyPizzerias configAPI) {
		API=configAPI;
		new Thread(this,"3rd Thread").start();
	}
	
	@Override
	public void run() {
		try {
			API.createPizzeria("File PIZZA", ioClass.buildPizzaConfig("pizzaObj2.txt"));
			API.printPizzeria("File PIZZA");
			System.out.println("Thread "+Thread.currentThread().getName()+ " Finished!");
			Thread.sleep(3000);
			
		} catch (MyException | IOException | InterruptedException e) {
			try {
				throw new Factory().exceptionFactory("File Not Found!");
			} catch (MyException | IOException e1) 
			{
				//e1.printStackTrace();
			}
		}
		System.out.println("Thread "+Thread.currentThread().getName()+ " Finished!");
		
	}

	
}
