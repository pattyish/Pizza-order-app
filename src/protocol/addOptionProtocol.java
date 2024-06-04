package protocol;

import java.io.IOException;

import exceptions.MyException;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class addOptionProtocol extends ProtocolFactory {

	public addOptionProtocol() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		String[] toInsert=string.split(":");
		try {
			pizz.addOptionPizza(toInsert[0], toInsert[1], toInsert[2], Double.parseDouble(toInsert[3]));
		} catch (NumberFormatException | MyException | IOException e) {
			e.printStackTrace();
		}
		return "Success add!!";
	}

}
