package protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class ShowAvailablePizzerias extends ProtocolFactory {

	public ShowAvailablePizzerias() throws NumberFormatException, IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {		
		Object kk=new Object();
		kk=pizz.getKeys();
		System.out.println(kk.toString());
		return kk;
	}

} 
