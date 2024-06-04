package wrapper;

import java.io.IOException;

public class DatabaseConfigAPI extends DatabaseAPIStuff implements DataBaseInterface{
private static DatabaseConfigAPI api;
	
	private DatabaseConfigAPI() throws NumberFormatException, IOException {
		
	}
	public static DatabaseConfigAPI getInstance() throws NumberFormatException, IOException {
		if(api== null) {
			api=new DatabaseConfigAPI();
			//TestDriver.runAll();
		}
		return api;
	}
}
