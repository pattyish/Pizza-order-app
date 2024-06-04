package protocol;

import java.io.IOException;

import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class viewSetProtocol extends ProtocolFactory {

	public viewSetProtocol() throws NumberFormatException, IOException {
		super();
	}
	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		Object toReturn=pizz.viewSets(string);
		return toReturn;
	}

}
