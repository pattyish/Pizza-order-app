package exceptions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import wrapper.ProxyPizzerias;

public class Factory extends Exception{

	private static final long serialVersionUID = 1L;

	public Factory() {

	}
	public Factory(String message) {
		super(message);
	}
	static Logger logger = Logger.getLogger(Factory.class.getName());


	public MyException exceptionFactory( String message) throws MyException, IOException  {

		if(message.equalsIgnoreCase("OptionSet")) {
			throw new  DuplicatedOptionSetNameException(message);
		}
		else if(message.equalsIgnoreCase("Option")) {
			throw new InvalidOptionException(message);
		}
		else if(message.equalsIgnoreCase("BasePrice")) {
			throw new BasePriceMissingException(message);
		}
		else if(message.equalsIgnoreCase("ConfigNameMiss")) {
			throw new NameMissingException(message);
		}
		else if(message.equalsIgnoreCase("File Not Found!")) {
			throw new InvalidPizzeriaToConfigure(message);
		}
		else if(message.equalsIgnoreCase("Delete not found")) {
			throw new InvalidPizzeriaDelete(message);
		}
		else if(message.equalsIgnoreCase("Delete not found")) {
			throw new EmptyPizzeriaPrintException("Empty pizzera!");
		}
		return null;
	}

	public static void fixError(ProxyPizzerias pizzeriaAPI,Exception e) throws NumberFormatException, IOException, MyException  {

		logger.log(Level.WARNING, "This is a warning!" );

		if(e instanceof BasePriceMissingException) {
			((BasePriceMissingException) e).fix(pizzeriaAPI, e);
		}

		if(e instanceof DuplicatedOptionSetNameException) {
			((DuplicatedOptionSetNameException) e).fix(pizzeriaAPI, e);
		}
		if(e instanceof InvalidOptionException)
		{
			((InvalidOptionException) e).fix(pizzeriaAPI, e);
		}

		if(e instanceof NameMissingException) {
			((NameMissingException) e).fix(pizzeriaAPI, e);
		}

		if(e instanceof InvalidPizzeriaToConfigure) 
		{
			((InvalidPizzeriaToConfigure) e).fix(pizzeriaAPI, e);	
		}
		if(e instanceof InvalidPizzeriaDelete) {
			((InvalidPizzeriaDelete) e).fix(pizzeriaAPI, e);
		}
		if(e instanceof EmptyPizzeriaPrintException) {

			((EmptyPizzeriaPrintException) e).fix(pizzeriaAPI, e);
		}

	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(String value) {
		if(value=="ON") {
			System.out.println("                                        LOOGING ENABLED!");
		}
		else if(value=="OFF") {
			System.out.println("                                        LOOGING DISABLED!");
			Factory.logger.setLevel(Level.OFF);
		}
	}

}
