package wrapper;

import java.sql.*;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.Factory;
import exceptions.MyException;
import io.ioClass;
import model.PizzaConfig;


public abstract class DatabaseAPIStuff implements DataBaseInterface {
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String dbAddress = "jdbc:mysql://localhost:3306/";
	private String userPass = "?user=root&password=";
	private String dbName = "IHIRWE";
	private String userName = "root";
	private String password = "";

	private Statement s;
	private ResultSet result;
	private Connection con;
	private String PIZZERRIAS="pizza";
	private String OPTIONSETS="optionsets";
	private String OPTIONS="options";
	private static String pizzerriasName =null;
	private static String optionSetName =null;
	private static String optionsName =null;
	private int k=1;
	private String nameOfPizzeria;
	private PizzaConfig pizzaConf;
	int i=1;
	int m=1;


	public DatabaseAPIStuff() {
		try {

			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbAddress + dbName, userName, password);
			s = con.createStatement();
			s.executeUpdate("DROP DATABASE IF EXISTS "+dbName);
			con = DriverManager.getConnection(dbAddress + dbName, userName, password);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			createDatabase();
			createTables();

		}
	}

	private void createDatabase() {
		try {
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbAddress + userPass);
			s = con.createStatement();
			int myResult = s.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);

		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTables() {
		try {
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(dbAddress + dbName, userName, password);

			pizzerriasName = "create table "+PIZZERRIAS+" ( "
					+ "   pizzaId INT PRIMARY KEY, pizzName VARCHAR(50), basePrice INT )";
			optionSetName = "create table "+OPTIONSETS+" ( "
					+ "   setId INT PRIMARY KEY, setName VARCHAR(50), setFid INT, FOREIGN KEY (setFid) REFERENCES "+PIZZERRIAS+" (pizzaId) )";
			optionsName = "create table "+OPTIONS+" ( "
					+ "   optionId INT PRIMARY KEY, optionName VARCHAR(50), priceIncrease INT, optionFid INT, FOREIGN KEY (optionFid) REFERENCES "+OPTIONSETS+" (setId) )";

			s = con.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS "+OPTIONS);
			s = con.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS "+OPTIONSETS);
			s = con.createStatement();
			s.executeUpdate("DROP TABLE IF EXISTS "+PIZZERRIAS);
			s = con.createStatement();
			s.executeUpdate(pizzerriasName);
			s = con.createStatement();
			s.executeUpdate(optionSetName);
			s = con.createStatement();
			s.executeUpdate(optionsName);
		} catch (SQLException e) {
			System.out.println("error: failed to create a connection object.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("other error:");
			e.printStackTrace();
		}
	}

	public synchronized void createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig) throws MyException, IOException{
		try {
			if(pizzeriaName.equalsIgnoreCase("")) 
			{
				setNameOfPizzeria(" ");
				pizzaConf=pizzaConfig;
				throw new Factory().exceptionFactory("ConfigNameMiss");
			}
			if(pizzeriaName !="") {
				setNameOfPizzeria(pizzeriaName);
				pizzaConf=pizzaConfig;
				s.executeUpdate("insert into "+PIZZERRIAS+"(pizzaId, pizzName, basePrice) values("+k+", '"+pizzeriaName.toLowerCase()+"', "+(int)pizzaConfig.getBasePrice()+" )");

				ArrayList<String> opSets= pizzaConfig.getOptionsSetNames();

				for(String oSet:opSets) {
					s.executeUpdate("insert into "+OPTIONSETS+"(setId, setName, setFid) values("+i+", '"+oSet+"', "+k+")");		

					ArrayList<String> names=pizzaConfig.getOptions(oSet);
					ArrayList<Double> priceInc=pizzaConfig.getOptionsPriceInc(oSet);
					int priceInd=0;
					for(String name:names) {
						s.executeUpdate("insert into "+OPTIONS+"(optionId, optionName, priceIncrease, optionFid) values("+m+", '"+name+"', "+priceInc.get(priceInd)+", "+i+")");	
						priceInd++;
						m++;
					}
					i++;
				}
				k++;
			}
		}catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}




	private void setNameOfPizzeria(String string) {
		nameOfPizzeria=string;

	}

	public  synchronized void configurePizzeria(String filename) throws MyException, IOException {
		if(ioClass.buildPizzaConfig(filename)==null) 
		{
			System.out.println("Invalid file name");
			throw new Factory().exceptionFactory("File Not Found!");
		}
		else
		{
			pizzaConf=ioClass.buildPizzaConfig(filename);
			createPizzeria(pizzaConf.getConfigName(), pizzaConf);
		}

	}


	@SuppressWarnings("resource")
	public PizzaConfig pullConfig(String nameOfPizza) throws SQLException {
		PizzaConfig toreturn= null;
		s=con.createStatement();
		ResultSet results = s.executeQuery("Select pizzName, pizzName from pizza");

		ResultSetMetaData metaData;
		int numCols;

		metaData = results.getMetaData();
		numCols = metaData.getColumnCount();

		double amount;
		String pizzName;
		String setName;
		String optionName;
		int priceIncrease;


		ArrayList<String> pizConf=new ArrayList<>();
		ArrayList<String> optionSets=new ArrayList<>();
		while (results.next())
		{
			pizzName = results.getString("pizzName");
			pizConf.add(pizzName);
		}

		if(pizConf.contains(nameOfPizza)) 
		{
			s=con.createStatement();
			//ResultSet results2 = s2.executeQuery("SELECT * FROM options where optionFid= "+o);
			results = s.executeQuery("Select * FROM pizza where pizzName= \""+nameOfPizza+"\" ");

			while(results.next())
			{
				String namee=results.getString("pizzName");
				if(namee.equalsIgnoreCase(nameOfPizza)) 
				{
					int basP=results.getInt("basePrice");
					int idPizz=results.getInt("pizzaId");
					toreturn= new PizzaConfig(nameOfPizza, basP);

					s=con.createStatement();
					results = s.executeQuery("SELECT * FROM optionsets where setFid= "+idPizz);
					while (results.next())
					{

						setName = results.getString("setName");
						optionSets.add(setName);

						int o=results.getInt("setId");
						//adding option set
						toreturn.addOptionSet(setName);
						Statement s2=con.createStatement();
						ResultSet results2 = s2.executeQuery("SELECT * FROM options where optionFid= "+o);
						while(results2.next()) 
						{
							optionName = results2.getString("optionName");
							priceIncrease = results2.getInt("priceIncrease");
							toreturn.addOption(setName, optionName, priceIncrease);
						}
					}

				}
				return toreturn;
			}
		}
		else {
			System.out.println();
		}

		System.out.println("sIZEEE"+pizConf.size()+" WITH "+pizConf.toString());
		return toreturn;

	}



	public  synchronized  void printPizzeria(String pizzeriaName) {
		try {
			for(String me:getKeys()) 
			{
				if(pizzeriaName.equalsIgnoreCase(me)) 
				{
					PizzaConfig conf=pullConfig(me.toLowerCase());
					conf.print();
				}
			}

		}catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}
	public  void updateOptionSetName(String configName, String optionSetname, String newName) throws MyException, IOException {
		try {
			if(getKeys().contains(configName)) 
			{
				s=con.createStatement();
				result=s.executeQuery("Select * from pizza where pizzName ='"+configName+"'");
				result.next();
				int pizzId=result.getInt("pizzaId");
				s.executeUpdate(" update optionsets SET setName= '"+newName+"' where setName= '"+optionSetname+"' AND setFid = "+pizzId );
				System.out.println(" DONE@ --> Updated");
			}
			else 
				System.out.println("No Pizzeria Found!");
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}
	public  synchronized void updateBasePrice(String configName, double newPrice) throws MyException, IOException
	{
		try 
		{
			for(String val:getKeys()) {
				if(val.equalsIgnoreCase(configName)) 
				{
					s=con.createStatement();
					result=s.executeQuery("Select * from pizza where pizzName ='"+configName+"'");
					result.next();
					int pizzId=result.getInt("pizzaId");
					s.executeUpdate(" update pizza SET basePrice= '"+newPrice+"' where  pizzaId = "+pizzId );
					System.out.println(" DONE@ --> Updated");
				}
			}
		}catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}

	public ArrayList<String> viewSets(String pizzeri)
	{
		try 
		{
			for(String con:getKeys()) {
				if(con.equalsIgnoreCase(pizzeri)) 
				{
					ArrayList<String> toSend=pullConfig(con).viewOptionSet();
					return toSend;
				}
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
		return null;
	}

	public  void updateOptionPrice(String configName, String optionName, String Option, int newPrice) throws MyException, IOException {
		try 
		{
			if(newPrice==0 | Option=="") {
				throw new Factory().exceptionFactory("Option");
			}
			if(configName.equalsIgnoreCase("")) {
				throw new Factory().exceptionFactory("ConfigNameMiss");
			}
			for(String val:getKeys()) {
				if(val.equalsIgnoreCase(configName)) 
				{
					s=con.createStatement();
					result=s.executeQuery("Select * from pizza where pizzName ='"+configName.toLowerCase()+"'");
					result.next();
					int pizzId=result.getInt("pizzaId");

					s=con.createStatement();
					result=s.executeQuery("Select * from optionsets where setFid ="+pizzId);
					while(result.next()) 
					{
						String setNamee= result.getString("setName");
						if(setNamee.equalsIgnoreCase(optionName)) 
						{

							int idd= result.getInt("setId");
							Statement sstt= con.createStatement();
							sstt.executeUpdate("update options SET priceIncrease= "+newPrice+" where optionFid ="+idd+" AND optionName= '"+Option+"'"); 
						}
					}

				}
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}


	public  synchronized void deletePizzeria(String name) throws  IOException, MyException {
		try {
			if(!getKeys().contains(name)) {
				System.out.println("Delete not found");
			}
			for(String conf: getKeys())
			{
				if(conf.equalsIgnoreCase(name)) 
				{

					s=con.createStatement();
					result=s.executeQuery("Select * from pizza where pizzName ='"+name+"'");
					result.next();
					int pizzId=result.getInt("pizzaId");

					s=con.createStatement();
					result=s.executeQuery("Select * from optionsets where setFid ="+pizzId);
					while(result.next()) {
						int setId=result.getInt("setId");
						Statement stat=con.createStatement();
						stat.executeUpdate("Delete from options where optionFid ="+setId);
						stat.executeUpdate("delete from optionsets where setId ="+setId);
					}
					s.executeUpdate("delete from pizza where pizzaId = "+pizzId);
					break;
				}
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}	
	public PizzaConfig getPizzaConf() {
		return pizzaConf;
	}

	public PizzaConfig getPizzaConf(String name) {

		try
		{
			for(String confName: getKeys())
				if(confName.equalsIgnoreCase(name)) {
					return pullConfig(confName.toLowerCase());
				}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
		return null;
	} 

	public  ArrayList<String> getKeys(){
		ArrayList<String> keysss=new ArrayList<>();
		try 
		{

			ResultSet results = s.executeQuery("Select pizzName, pizzName from pizza");
			String pizzName;

			while (results.next())
			{
				pizzName = results.getString("pizzName");
				keysss.add(pizzName);
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
		return keysss;
	}

	public synchronized void printPizzerias() throws MyException, IOException, SQLException {
		if(getKeys().isEmpty()) 
		{
			throw new Factory().exceptionFactory("Empty pizzera!");
		}
		else 
		{
			for(String keys:getKeys()) 
			{
				pullConfig(keys).print();
			}
		}
	}
	public synchronized void addOptionPizza(String pizzer,String optionSet, String Option,double newPrice) throws MyException, IOException {
		try 
		{
			for(String val:getKeys()) {
				if(val.equalsIgnoreCase(pizzer)) 
				{
					s=con.createStatement();
					result=s.executeQuery("Select * from pizza where pizzName ='"+pizzer.toLowerCase()+"'");
					result.next();
					int pizzId=result.getInt("pizzaId");

					s=con.createStatement();
					result=s.executeQuery("Select * from optionsets where setFid ="+pizzId);
					while(result.next()) 
					{
						String setNamee= result.getString("setName");
						if(setNamee.equalsIgnoreCase(optionSet)) 
						{
							Statement sstt= con.createStatement();
							ResultSet ree=sstt.executeQuery("SELECT MAX(optionId) FROM options");
							ree.next();
							int maxx= ree.getInt(1);

							int idd= result.getInt("setId");
							sstt.executeUpdate("insert into options (optionId, optionName, priceIncrease, optionFid) values("+(maxx+1)+", '"+Option+"', "+newPrice+", "+idd+")");	

						}
					}

				}
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
	}


	public synchronized void deleteAllPizzeria() throws SQLException {		
		s.executeUpdate("delete from options");
		s.executeUpdate("delete from optionsets");
		s.executeUpdate("delete from pizza");
	}

	public String getpizzBasePrice(String mine) {
		try {
			for(String keysi:getKeys()) {
				if(keysi.equalsIgnoreCase(mine)) {
					double price= pullConfig(keysi).getBasePrice();
					return String.valueOf(price);
				}
			}
		}
		catch(SQLException E) {
			System.out.println("Error occured!!");
		}
		return null;
	}
}