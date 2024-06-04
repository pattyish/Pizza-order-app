package protocol;

import java.io.IOException;
import java.util.Set;

import exceptions.MyException;
import wrapper.DataBaseInterface;
import wrapper.ProxyPizzerias;

public class DeleteProtocol extends ProtocolFactory {
	String pizzeria,keyword;
	
	public DeleteProtocol(String pizzeria, String keyword)  throws IOException  {
		this.pizzeria = pizzeria;
		this.keyword = keyword;
	}
 
	@Override
	public Object fixRequest(DataBaseInterface pizz, String string) {
		try {
			pizz.deletePizzeria(string);
		} catch (MyException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string.toUpperCase() +" Pizza deleted successfully !!";
	}
	
	
	
}
