package protocol;

import java.io.IOException;

import exceptions.MyException;
import model.PizzaConfig;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class FileUploadProtocol extends ProtocolFactory {

	
	public FileUploadProtocol() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
//	System.out.println("I have this finallllyyy");
		try {
			pizz.configurePizzeria(string);
			System.out.println(string+"    Uploaded suceeesfullyuy");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string+ " is Successfully Uploaded";
		
		
	}
}
