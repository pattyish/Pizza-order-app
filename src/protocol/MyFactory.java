package protocol;

import java.io.IOException;
import wrapper.PizzeriaConfigAPI;
import wrapper.ProxyPizzerias;

public class MyFactory extends Thread{

	public MyFactory() throws NumberFormatException, IOException {
	}
	
	public ProtocolFactory checkRequest(String pizzerria, String message) throws NumberFormatException, IOException {		
		System.out.println(message);
		if(message.equals("Delete")) {
			return new DeleteProtocol(pizzerria, message);
		} 
		if(message.equals("Print")) {
			return new PrintaPizzeria();
		}
		if(message.equals("keySet")) {
			return new ShowAvailablePizzerias();
		}
		if(message.equals("Upload")) {
			return new FileUploadProtocol();
		}
		if(message.equals("Price")) {
			return new UpdatePriceProtocol();
		}
		if(message.equals("giveBase")) {
			return new GivePriceProtocol();
		}
		if(message.equals("viewSet")) {
			return new viewSetProtocol();
		}
		
		if(message.equals("addOption")) {
			return new addOptionProtocol();
		}
		return new InvalidInput();
	}

}
