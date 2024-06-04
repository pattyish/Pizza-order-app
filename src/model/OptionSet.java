package model;

import java.io.Serializable;
import java.util.ArrayList;

class OptionSet implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList <Option> choices=new ArrayList<Option>();
	private int optionsArraySize=0;

	protected OptionSet(String name) {
		this.name = name;
		choices=new ArrayList<Option>();

	}

	protected void addOption(String name) 
	{
		if(!choices.contains(new Option(name))) {
			choices.add(new Option(name));
			//System.out.println(name.toUpperCase()+" Option added successfully!! \n");
			optionsArraySize++;
		}
		//else //System.out.println(name.toUpperCase()+" Option already exist!!\n");
	}

	protected void addOption(String name, double priceIncrease)
	{
		if(!choices.contains(new Option(name,priceIncrease))) {
			choices.add( new Option(name,priceIncrease));
			//System.out.println(name.toUpperCase()+" Option added successfully!! \n");
			optionsArraySize++;
		}
		//else
			
			//System.out.println(name.toUpperCase()+" Option already exist!!");


	}


	protected void deleteOption(String name) {
		//System.out.println("Deleting "+name.toUpperCase()+" option..");
		for(int i=0; i<choices.size();i++)
			if(choices.get(i).getName()==name)
			{			
				choices.remove(choices.get(i));
				//System.out.println(name.toUpperCase()+" Option deleted successfully!! \n");
				optionsArraySize--;
			}
			else System.out.println(name.toUpperCase()+" does not exist!!  No option to deleted");
	}

	protected int getOptionsArraySize() {
		return optionsArraySize;
	}

	protected void setOptionsArraySize(int optionsArraySize) {
		this.optionsArraySize = optionsArraySize;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getChoices() {
		return choices;
	}

	class Option implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name1;
		private double priceIncrease;

		protected Option(String name) {
			setName(name);
			setPrice(0);
		}

		protected Option(String name, double priceIncrease) {
			this.name1 = name;
			this.priceIncrease = priceIncrease;
		}

		protected String getName() {
			return name1;
		}

		protected void setName(String name) {
			this.name1 = name;
		}

		protected double getPriceIncrease() {
			return priceIncrease;
		}

		protected void setPrice(double priceIncrease) {
			this.priceIncrease = priceIncrease;
		}
		protected void print() {
			System.out.println(this);
		}
		@Override
		public String toString() {
			String string = null;
			if(priceIncrease>=0) {
				StringBuilder str= new StringBuilder("name=" + name1 + "\n	   price Increase= " + priceIncrease +"\n");
				string= String.valueOf(str);
			}
			else {
				StringBuilder str= new StringBuilder("name=" + name1 + "\n	   price decrease= " + priceIncrease +"\n");
				string= String.valueOf(str);
			}
			return string;
		}

	}

	protected void print() {
		if(choices!=null) {
			for(Option opt:choices) {
				System.out.print("  Option:: ");
				opt.print();
			}

		}
		else System.out.println("Empty set!!");
	}

	@Override
	public String toString() {
		StringBuilder str= new StringBuilder("OptionSet [name=" + name +  ", has ="
				+ optionsArraySize + " options ]");
		String string= String.valueOf(str);
		return string;
	}

}


