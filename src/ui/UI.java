package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import client.Client;
import client.PizzeriaClient;
import client.TestClient;
import exceptions.MyException;
import io.InputDataFile;

public class UI  {

	public static void main(String args[]) {
		
		try {
			Client client = new Client();

			System.out.println("*************** WELCOME IN NEW CLIENT/SERVER MODEL**************");
			printMessage();
			BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
			String input=reader.readLine();
			while(!input.equalsIgnoreCase(""))
			{
				if(input.contains("UP") | input.contains("up") | input.contains("Up") | input.contains("uP") | input.equalsIgnoreCase("1")) 
				{
					System.out.println("do you want to upload a file ? \"Enter yes,y or yep to upload ");
					String in=reader.readLine();
					if((!in.equalsIgnoreCase("")) && (in.contains("Y")| in.contains("y"))) 
					{
						System.out.println("Enter the name of the file");
						String path=reader.readLine();
						InputDataFile datafile = new InputDataFile(path);

						//opening file
						datafile.open();
						if(!datafile.isOpen())
						{
							System.err.println("Oops, I could not open the file. :( \n FILE DOESN'T EXIST");
						}
						else
						{
							client.uploadPropertiesFile(path);
						}
					}
					else
					{
						System.out.println("WRONG INPUT!");
					}

				}
				// printing all the pizzerias
				else if(input.contains("SH") | input.contains("Sh") | input.contains("sh") | input.contains("sH") | input.equalsIgnoreCase("2")) 
				{
					client.ShowAvailablePizzerias();
				}
				//Printing a pizzeria
				else if(input.contains("PR") |input.contains("Pr") |input.contains("pR") |input.contains("pr") | input.equalsIgnoreCase("3")) {
					System.out.println("Here is the list of available Pizzerias on the Server.");
					client.ShowAvailablePizzerias();
					System.out.println("Enter a valid pizzeria name or Pless ENTER TO CONTINUE!");
					System.out.println("Select one to print!");
					String selected=reader.readLine();

					while(!client.PrintAPizzeria(selected.toUpperCase()) && !selected.equalsIgnoreCase("")) {
						System.out.println("Enter a valid pizzeria name or Pless ENTER TO CONTINUE!");
						selected=reader.readLine();
					}
					if(selected.equalsIgnoreCase("")) {
						System.out.println("You entered CONTINUE option");
					}
				}

				else if(input.contains("De") |input.contains("DE") |input.contains("de") |input.contains("dE") | input.equalsIgnoreCase("4")) {
					System.out.println("Here is the list of available Pizzerias on the Server.");
					client.ShowAvailablePizzerias();
					System.out.println("Enter a valid pizzeria to delete or Pless ENTER TO CONTINUE!");
					System.out.println("Select one to delete!");
					String selected=reader.readLine();

					while(!client.DeleteAPizzeria(selected.toUpperCase()) && !selected.equalsIgnoreCase("")) {
						System.out.println("Enter a valid pizzeria to delete or Pless ENTER TO CONTINUE!");
						selected=reader.readLine();
					}
					if(selected.equalsIgnoreCase("")) {
						System.out.println("You entered CONTINUE option");
					}
				}

				else if(input.contains("Co") |input.contains("CO") |input.contains("co") |input.contains("cO") | input.equalsIgnoreCase("5")) {
					System.out.println("Here is the list of available Pizzerias on the Server.");
					client.ShowAvailablePizzerias();
					System.out.println("We have two options to configure!\nEnter \"1\" to a Update the base price.\r\nEnter \"2\"" + 
							" View the existing option sets, select one, and add an option.");
					System.out.println("Enter a valid choice or Pless ENTER TO CONTINUE!");

					String put= reader.readLine();
					if(!put.equalsIgnoreCase("")) {
						int selected=0;
						try {
							selected=Integer.parseInt(put);
						}
						catch (Exception e) {

						}
						while(selected!=1 && selected!=2) {
							System.out.println("Enter a valid choice or Press ENTER TO CONTINUE!");
							System.out.println("We have two options to configure!\nEnter \"1\" to a Update the base price.\r\nEnter \"2\"" + 
									" View the existing option sets, select one, and add an option.");
							put=reader.readLine();
							if(!put.equalsIgnoreCase("")) {
								try
								{
									selected=Integer.parseInt(put);
									break;
								}
								catch (Exception e) {
									System.err.println("Invalid input!!");
								}
							}
							else 
							{
								System.out.println("You entered CONTINUE option");
								break;
							}
						}
						if(selected==1) {

							System.out.println("Thank you for chosing to update the base price!");
							System.out.println("Please enter the name of the pizzeria!");
							String key=reader.readLine();
							while(!client.checkContainKeySet(key)&& !key.equalsIgnoreCase("")) {
								System.out.println("Please enter the name of the pizzeria! Press ENTER TO EXIT!!!");
								key=reader.readLine();
							}
							if(client.checkContainKeySet(key)) {
								System.out.println("Please enter the base price!!");
								double base=Double.parseDouble(reader.readLine());
								client.UpdateBasePrice(key,base);
								//System.out.println("Here s your Configured Pizzeria!");
								//client.PrintAPizzeria(key);
							}
							if(key.equalsIgnoreCase("")) {
								System.out.println("You entered CONTINUE option");
							}
							else {
								System.out.println("You've chosen to exit!");
							}
						}
						else if(selected ==2) 
						{
							System.out.println("Thank you for chosing to add a new option!");
							System.out.println("Please enter the name of the pizzeria to configure!");
							String pizzeria=reader.readLine();
							while(!client.checkContainKeySet(pizzeria)&& !pizzeria.equalsIgnoreCase("")) {
								System.out.println("Please enter the name of the pizzeria! Press ENTER TO EXIT!!!");
								pizzeria=reader.readLine();
							}
							if(client.checkContainKeySet(pizzeria)) {
								System.out.println("We have these optionSets in it!");
								client.viewOptionSet(pizzeria);
								System.out.println("Please enter the name of the optionSet you want to configure");
								String optionSet=reader.readLine();
								if(!optionSet.equalsIgnoreCase(""))
								{
									System.out.println("Please enter the option Name you want to add");
									String option=reader.readLine();
									if(!option.equalsIgnoreCase("")) {
										System.out.println("Enter the option price increase");
										double increase = Double.parseDouble(reader.readLine());
										client.AddOption(pizzeria.toUpperCase(), optionSet, option, increase);
									}
									
								}
								else
									System.out.println("Invalid optionSet");


							}
							else if(pizzeria.equalsIgnoreCase("")) {
								System.out.println("You entered CONTINUE option");
							}
							else System.out.println("Invalid pizzeria");
						}
						else
							System.err.println("Invalid input!!");
					}
				}
				System.out.println("//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//");
				System.out.println("//**************THANK YOU !!*****************//");
				System.out.println("//~~~~~~~Do you want to Continue??~~~~~~~~~~~// \nEnter yes,y or yep to continue OR \'Press enter to EXIT \' ");
				//				System.out.println("//___________________________________________//");
				String in2 = reader.readLine();
				if((!in2.equalsIgnoreCase("")) && (in2.contains("Y")| in2.contains("y"))) 
				{
					printMessage();
					input=reader.readLine();
					continue;
				}
				else if(in2.equalsIgnoreCase("")) {
					System.err.println("You entered EXIT option");
					break;	
				}
				else 
				{
					System.err.println("INVALID CHOICE!"); 
					break;
				}
			}
			if(input.equalsIgnoreCase("")) {
				System.err.println("You entered EXIT option");
			}
			System.out.println("//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//");
			System.out.println("//*************THANK YOU OR USING OUR SYSTEM!! !!**************//");
			System.out.println("//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//");
			System.out.println("              //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//");
			System.out.println("                        //~~~~~~~~~~~//");
			System.out.println("                           //@@@@//");
			System.out.println("                            //-//");
		}
		catch (IOException |NumberFormatException| ClassNotFoundException | MyException E) {
			E.printStackTrace();
		}
	}




	private static void printMessage() {
		System.out.println("\n\nWe dave different options in our Client/Server communication  model");
		System.out.println("If you want to: \nUpload a Properties file to the server: : type \"Upload\" or type 1\r\n" + 
				"Show the available Pizzerias: : type \"Show\" or type 2\r\n" + 
				"Print a Pizzeria: : type \"Print\" or type 3\r\n" + 
				"Delete a Pizzeria: : type \"Del\" or type 4\r\n" + 
				"Configure a Pizzeria: : type \"Conf\" or type 5\r\n"+
				"OR \'Press enter to EXIT \'");
	}
}
