package protocol;

import java.io.IOException;

import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class GivePriceProtocol extends ProtocolFactory {

	public GivePriceProtocol() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		return pizz.getpizzBasePrice(string);
	}

}
