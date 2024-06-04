package exceptions;

import wrapper.ProxyPizzerias;

public class EmptyPizzeriaPrintException extends MyException{
	private static final long serialVersionUID = 1L;

	public EmptyPizzeriaPrintException(String message) {
		super(message);
	}

	
	public void fix(ProxyPizzerias pizzeriaAPI, Exception e) {
		System.out.println(" You are trying to print empty Pizzieriaaa??");
		
	}

}
