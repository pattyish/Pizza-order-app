package protocol;

import java.io.IOException;

import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class InvalidInput extends ProtocolFactory {

	public InvalidInput() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		// TODO Auto-generated method stub
		return "You entered invalid input!!!";
	}

}
