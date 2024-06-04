package protocol;

import java.io.IOException;

import exceptions.MyException;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class UpdatePriceProtocol extends ProtocolFactory {

	public UpdatePriceProtocol() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		String [] string2=string.split(":");
		try {
			pizz.updateBasePrice(string2[0], Double.parseDouble(string2[1]));
			return string2[0].toUpperCase() + " price Updated succesfully";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Update failed!!";
	}

}
