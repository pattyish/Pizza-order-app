package scaletests;

import java.io.IOException;

import exceptions.Factory;
import exceptions.MyException;
import wrapper.ProxyPizzerias;

public class DeleteAllThread extends SimulatedUser{
	public DeleteAllThread(ProxyPizzerias linkedHashMap) {
		API=linkedHashMap;
		new Thread(this,"4th Thread").start();
	}

	@Override
	public void run() {


		try {
			Thread.sleep(3000);
			API.printPizzerias();
			API.deleteAllPizzeria();
			System.out.println("Thread "+Thread.currentThread().getName()+ " Finished!");
			Thread.sleep(3000);
		} catch (InterruptedException | MyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
