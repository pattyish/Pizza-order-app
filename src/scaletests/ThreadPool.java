package scaletests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wrapper.ProxyPizzerias;

public class ThreadPool extends SimulatedUser{
	public ThreadPool(ProxyPizzerias linkedHashMap) {
		API=linkedHashMap;
		new Thread(this,"ThreadPool Thread").start();
	}
	@Override
	public void run() {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);

		try {
			threadPool.execute( new DeleteAndPrintUser(API));
			//System.out.println("In Threadpool: Thread one finished!!");
			Thread.sleep(3000);
			threadPool.execute( new UpdatePrintUser(API));
			//System.out.println("In Threadpool: Thread two finished!!");
			Thread.sleep(3000);
			threadPool.execute( new AddingPizieriaFromFileUser(API));
			//System.out.println("In Threadpool: Thread three finished!!");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadPool.shutdown();
	}



}
