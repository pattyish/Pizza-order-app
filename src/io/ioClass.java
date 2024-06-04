package io;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import model.PizzaConfig;

public class ioClass {

	public static PizzaConfig buildPizzaConfig(String filename) throws IOException
	{
		PizzaConfig mm = null;
		Properties props= new Properties();
		FileInputStream in = new FileInputStream(filename);
		props.load(in);                                 // This loads the entire file in memory.
		// OK for this version of the assignment.
		String p = props.getProperty("Pizzeria"); // This is how you read a property.
		// It is like getting a value from HashTable.
		if(!p.equals(null))
		{
			mm= new PizzaConfig(p.toUpperCase());

			String price = props.getProperty("BasePrice");
			mm.setBasePrice(Double.parseDouble(price));


			for(int i=1; i<=6; i++)
			{

				String option1 = props.getProperty("Option"+i);
				if(option1!=null) 
				{
					mm.addOptionSet(option1);
					for(char alphabet = 'a'; alphabet <='z'; alphabet++ )
					{
						String optionValue1a = props.getProperty("OptionValue"+i+alphabet);
						if(optionValue1a!=null) 
						{
							String increaseP1a=props.getProperty("priceIncrease"+i+alphabet);
							if(increaseP1a!=null) 
							{
								mm.addOption(option1, optionValue1a,Double.parseDouble(increaseP1a));
							}
							else 
							{
								mm.addOption(option1, optionValue1a);
							}
						}

						else 
						{
							break;
						}
					}
				}
			}
		}
		return mm;
	}


	//		InputDataFile inputDataFile= new InputDataFile(filename);
	//		inputDataFile.open();
	//		if(inputDataFile.isOpen())
	//		{
	//			PizzaConfig config = null;
	//			BufferedReader buff = null;
	//			FileReader file;
	//			String set = null;
	//			try {
	//				file = new FileReader(filename);
	//				buff = new BufferedReader(file);
	//				String line;
	//				line = buff.readLine();
	//				if (line.startsWith("N*")) {
	//					System.out.println("Making config");
	//					config = new PizzaConfig(line.substring(line.indexOf("*") + 1, line.length()));
	//					line = buff.readLine();
	//
	//					if (line.startsWith("P*")) {
	//						double price = Double.parseDouble(line.substring(line.indexOf("*") + 1, line.length()));
	//						// exception issues
	//						System.out.println("Setting price!!");
	//						config.setBasePrice(price);
	//						line = buff.readLine();
	//						String tempLine;
	//						if (line.startsWith("S*")) {
	//							set = line.substring(line.indexOf("*") + 1, line.length());
	//							config.addOptionSet(set);
	//							line = buff.readLine();
	//							while (line.startsWith("S*") | line.startsWith("O*")) {
	//								while (line.startsWith("O*") && line != null) {
	//									if (line.contains(",")) {
	//										config.addOption(set, line.substring(line.indexOf("*") + 1, line.indexOf(",")),
	//												Double.parseDouble(line.substring(line.indexOf(",") + 1, line.length())));
	//									} else {
	//										config.addOption(set, line.substring(line.indexOf("*") + 1, line.length()));
	//									}
	//									line = buff.readLine();
	//									if (line == null) {
	//										break;
	//									}
	//								}
	//								if (line == null) {
	//									break;
	//								}
	//								tempLine = line;
	//								set = line.substring(tempLine.indexOf("*") + 1, tempLine.length());
	//								config.addOptionSet(set);
	//								line = buff.readLine();
	//							}
	//						}
	//					}
	//					line = buff.readLine();
	//				} 
	//				else 
	//				{
	//					System.out.println("I cant read Your file weelll");
	//					System.out.println(line);
	//					return null;
	//				}
	//				buff.close();
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//
	//			}
	//			return config;
	//		}
	//		else
	//			System.out.println("Oops, I could not open the file. :( ");
	//		return null;
}

