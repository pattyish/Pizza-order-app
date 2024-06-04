package exceptions;

import wrapper.ProxyPizzerias;

public abstract class MyException extends Factory {
	private static final long serialVersionUID = 1L;

	public MyException(String message) {
		super(message);
	}
	abstract void fix(ProxyPizzerias pizzeriaAPI,Exception e);
		
}
