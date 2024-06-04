package model;
import java.io.Serializable;

import java.util.ArrayList;
import model.OptionSet.Option;

//This class will represent the Pizza Configuration 
public class PizzaConfig implements Serializable 
{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String configName;
	private double basePrice; 
	private ArrayList<OptionSet> options; // Note: Version 1.0 you MUST use a partially-filled array and handle all consequences
	private int arraySize;

	public PizzaConfig() {
		options = new ArrayList<OptionSet>();
	}
	public PizzaConfig(String configName) {
		this.configName = configName;
		options = new ArrayList<OptionSet>();
	}
	public PizzaConfig(String configName, double basePrice) {
		this.configName = configName;
		this.basePrice = basePrice;
		options = new ArrayList<OptionSet>();
	}


	public PizzaConfig(double baseprice) {
		try {
			this.basePrice=baseprice;
			options = new ArrayList<OptionSet>();
		}
		catch (Exception e) {
			////System.out.println("_??????_*_*__Ooopss! invalid inputs"+e+"\n");
		}
	}

	public OptionSet getOptionSet(int k) {
		return options.get(k);
	}

	public OptionSet getOptionSet(String name) {
		for(OptionSet set:options) {
			if (set.getName().equalsIgnoreCase(name)) {
				set.print();
				return set;
			}
		}
		return null;
	}
	public double getPriceIncrease(String set, String option) {
		Option opt= findOption(set, option);
		double price=opt.getPriceIncrease();
		return price;
	}
	public OptionSet findOptionSet(String opt) {

		try {
			for(OptionSet optio:options) {
				if(optio.getName().equalsIgnoreCase(opt)){
					return optio;
				}
			}
		}
		catch (Exception e) {
		}
		System.out.println("_??????_*_*__Ooopss! No optionset found\n");
		return null;
	}

	public Option findOption(String optionSet,String opt) {
		////System.out.println("Finding "+opt.toUpperCase()+" option... ");
		try {
			OptionSet set=findOptionSet(optionSet);
			for(int i=0; i<set.getChoices().size();i++) {
				if(set.getChoices().get(i).getName().equalsIgnoreCase(opt))
				{
					//System.out.println("_?? Option found!");
					return set.getChoices().get(i);
				}
			}
			////System.out.println("_??????_*_*__Ooopss! option not found\n");
		}
		catch (Exception e) {
			//System.out.println("_??????_*_*__Ooopss! No optionset found\n");
		}
		//System.out.println("_??????_*_*__Ooopss! No optionset found\n");
		return null;
	}
	public ArrayList<String> getOptions(String set)
	{
		OptionSet sete= findOptionSet(set);
		ArrayList<Option> optionss=sete.getChoices(); 
		ArrayList<String> toRetu= new ArrayList<>();
		for(Option optio:optionss) {
			String kk= optio.getName();
			toRetu.add(kk);
		}
		return toRetu;
	}
	public synchronized void addOptionSet(String optionName) 
	{


		////System.out.println("Adding "+optionName.toUpperCase()+" optionSet ... ");
		if(!options.contains(new OptionSet(optionName))) 
		{
			options.add(new OptionSet(optionName));
			arraySize++;
		}
	}
	public void addOption(String optionSet, String optionName, double priceIncrease) {
		////System.out.println("Adding "+optionName.toUpperCase()+" option..");
		if(findOptionSet(optionSet)!=null) {
			findOptionSet(optionSet).addOption(optionName, priceIncrease);
		}
		//else ////System.out.println("_??????_*_*__Ooopss! No space available for adding an Set \n");
	}

	public void addOption(String optionSet, String optionName) {
		////System.out.println("Adding "+optionName.toUpperCase()+" option..");
		OptionSet set=findOptionSet(optionSet);
		if(set!=null) {
			set.addOption(optionName);
		}
	}

	//delete option in OptionSet!
	public synchronized void deleteOptionSet(String optionName) {
		////System.out.println("Deleting "+optionName.toUpperCase()+" optionSet..");


		for(int i=0; i<options.size();i++)
			if(options.get(i).getName().equalsIgnoreCase(optionName))
			{			
				options.remove(options.get(i));
				////System.out.println(optionName.toUpperCase()+" OptionSet deleted successfully!! \n");
				arraySize++;
			}
		//else ////System.out.println(optionName.toUpperCase()+" does not exist!!  No optionSet to deleted");
	}
	//delete option in OptionSet alsoooo!
	public void deleteOption(String optionSetName,String optionName) {
		////System.out.println("Deleting "+optionName.toUpperCase()+ "in "+optionSetName.toUpperCase()+ "option set");
		try {
			OptionSet set=findOptionSet(optionSetName);
			set.deleteOption(optionName);
		}
		catch (Exception e) {
			////System.out.println("_??????_*_*__Ooopss! Invalid option set!");
		}
	}


	public ArrayList<OptionSet> updateOptionSet(String set,String setNew) 
	{
		try {
			for(int i=0; i<options.size();i++) {
				////System.out.println(" -------------> "+options.get(i).getName());
				if(options.get(i).getName().equalsIgnoreCase(set)) {
					options.get(i).setName(setNew);
					return options;
				}
			}
			////System.out.println("_??????_*_*__Ooopss! not update made! no such set exist!\n");
			return options;
		}
		catch (Exception e) {
			////System.err.println("No such update exist\n");
		}
		return options;
	}

	public ArrayList<OptionSet> updateOption(String setName,String oldOptionName,String newOptionName,int increasePrice) {
		OptionSet mySet= findOptionSet(setName);
		//		try {
		if(mySet!=null) 
		{
			for(int i=0; i<mySet.getChoices().size();i++) {
				if(mySet.getChoices().get(i)!=null)
					if(mySet.getChoices().get(i).getName().equalsIgnoreCase(oldOptionName)) {
						mySet.getChoices().get(i).setName(newOptionName);
						mySet.getChoices().get(i).setPrice(increasePrice);
						////System.out.println("Done update");
						break;
					}
			}
			////System.out.println("_??????_*_*__Ooopss! No update done!");
		}
		else {
			////System.out.println("_??????_*_*__Ooopss! No update place available\n");
		}
		return options;
		//		}
		//		catch (Exception e) {
		//		}
		//		return options;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public ArrayList<OptionSet> getOptionsSets() {
		return options;
	}
	
	public ArrayList<String> getOptionsSetNames() {
		ArrayList<String> sts= new ArrayList<>();
		for(OptionSet opset:options) {
			sts.add(opset.getName());
		}
		return sts;
	}
	
	public ArrayList<String> viewOptionSet() {
		//		System.out.println("Available Sets");
		ArrayList<String> set=new ArrayList<>();
		for(int i=0;i<options.size();i++) {
			set.add(options.get(i).getName());
		}
		return set;
	}

	public synchronized void print()
	{

		try {
			System.out.println("*************** WELCOME IN NEW PIZZERIA MODEL**************");
			System.out.println("Pizzerria \nname=" + configName + ", basePrice=" + basePrice);
			System.out.println("Here is the configuration made");
			for(int i=0;i<options.size() ;i++) {
				if(options.get(i)!=null) {
					System.out.println((i+1)+"."+options.get(i).getName());
					options.get(i).print();
				}

			}
		}
		catch (Exception e) {
			////System.out.println("_??????_*_*__Ooopss! Invalid input "+e+"\n");
		}

	}

	@Override
	public String toString() {
		StringBuilder mine= new StringBuilder("PizzaConfig [ basePrice=" + basePrice + ", setSize=" + arraySize + "]");
		String k=String.valueOf(mine);
		return k;
	}
	public void printOptionSet(OptionSet set) {
		set.print();
	}


	public String getConfigName() {
		return configName;
	}


	public void setConfigName(String configName) {
		this.configName = configName;
	}
	
	public ArrayList<Double> getOptionsPriceInc(String optionSet) {
		OptionSet sete= findOptionSet(optionSet);
		ArrayList<Option> optionss=sete.getChoices(); 
		ArrayList<Double> toRetu= new ArrayList<>();
		for(Option optio:optionss) {
			double kk= optio.getPriceIncrease();
			toRetu.add(kk);
		}
		return toRetu;
	}
}
