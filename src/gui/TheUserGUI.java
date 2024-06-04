package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import client.ServerConnect;
import exceptions.MyException;
import wrapper.PizzeriaConfigAPI;
import wrapper.ProxyPizzerias;

public class TheUserGUI extends JPanel
{

	JComboBox<String> comboLayout;
	JLabel kk;
	JTextArea   message= new JTextArea(5, 30);
	JTextArea   notes = new JTextArea(2, 20);

	JButton viewPriceBut= new JButton("View Base Price");
	JButton updatePrice= new JButton("Update the base price");
	JButton update= new JButton("UPDATE NOW!");



	public void addComponents (JFrame theFrame) throws ClassNotFoundException, IOException
	{
		JLabel  l1  = new JLabel("//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//"); 
		JLabel  l2  = new JLabel("//~~~~~ WELCOME IN NEW CLIENT/SERVER MODEL~~~~~//"); 
		JLabel  l3  = new JLabel("//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//");
		JLabel pizzLabel= new JLabel("Select the Pizzeria you want!");

		Container c = theFrame.getContentPane();
		Container labelCont= new Container();
		labelCont.setLayout(new GridLayout(4, 1));
		labelCont.add(l1);
		labelCont.add(l2);
		labelCont.add(l3);
		//pizza container choices creations
		Container pizzaContainer= new Container();
		pizzaContainer.setLayout(new GridLayout(3,2));

		//functions container creation
		Container statusCont= new Container();
		statusCont.setLayout(new GridLayout(1,1));

		statusCont.add(update);
		update.addActionListener(new Update());


		JPanel funPan= new JPanel();
		funPan.add(statusCont);



		JPanel pan= new JPanel();
		comboLayout = new JComboBox<String>();
		viewPriceBut.addActionListener(new PizzaListerner());
		updatePrice.addActionListener(new UpdateListerner());
		comboLayout.setToolTipText("Select a Pizzeria you want");
		//adding pizzeria names
		comboLayout.addItem("Select....");

		if(giveSet().size()!=0) {
			for( String keyss:giveSet()) {
				comboLayout.addItem(keyss);
			}
		}

		comboLayout.addActionListener(new listernToSelect());

		pizzaContainer.add(pizzLabel);
		pizzaContainer.add(new JLabel(""));
		pizzaContainer.add(comboLayout);
		pizzaContainer.add(viewPriceBut);
		pizzaContainer.add(new JLabel(""));
		pizzaContainer.add(updatePrice);


		JPanel pizzaPanel= new JPanel();
		pizzaPanel.add(pizzaContainer);


		updatePrice.setEnabled(false);
		update.setEnabled(false);
		viewPriceBut.setEnabled(false);

		message.setSelectedTextColor(Color.RED);
		c.setLayout(new FlowLayout());
		c.setBackground(Color.LIGHT_GRAY);

		pan.add(labelCont);

		pan.setBackground(Color.MAGENTA);
		c.add(pan);
		c.add(pizzaPanel);
		c.add(notes);
		c.add(funPan);
		kk= new JLabel("Message!                                                      ");
		kk.setForeground(Color.RED);
		c.add(kk);

		c.add(message);



	}
	public void getPizzeriaPrice(String item) throws ClassNotFoundException, IOException {
		if(item !=null) 
		{
			String mine=item;
			if(giveSet().contains(mine)) {
				//System.out.println(mine);

				notes.setText(" Name : "+item+"\n Base price= "+ServerConnect.conect(mine, "giveBase").toString());
				updatePrice.setEnabled(true);
				update.setEnabled(false);
				message.setText("See above pizzeria base price details");
			}
			else
			{
				message.setText(" Invalid selection!!");
				notes.setText("");

			}
		}
	}
	public ArrayList<String> giveSet() throws IOException, ClassNotFoundException{

		Object	keys = ServerConnect.conect("Give", "keySet");
		ArrayList<String> out= (ArrayList<String>)keys;
		return out;
	}
	public void doUpdateJob(String string) {
		notes.setText("");
		message.setText("Please enter the new Price in the above field");
		updatePrice.setEnabled(false);
		update.setEnabled(true);

	}

	public void UpdateBasePrice(String pizzeria,double newPrice) throws MyException, IOException, ClassNotFoundException {
		if(giveSet().contains(pizzeria)) 
		{
			Object	keys = ServerConnect.conect(pizzeria+":"+String.valueOf(newPrice)+"", "Price");
			notes.setText("DONE!\n "+keys.toString());
		}
		else System.out.println("Invalid pizzeria!");
	}
	
	public void doUpdate(String string) throws ClassNotFoundException
	{
		if(!notes.getText().equals("")) 
		{
			try 
			{
				UpdateBasePrice(string, Double.parseDouble(notes.getText()));
				
//				notes.setText("DONE!");
				message.setText("You can view the updated price \nby clicking View Base Price button");
				updatePrice.setEnabled(true);
				update.setEnabled(false);
			} catch (NumberFormatException | MyException | IOException e) 
			{
				message.setText("Please enter a number not a string!!");
				notes.setText("NUMBER ONLY PLEASE!!");
				updatePrice.setEnabled(true);
				update.setEnabled(false);
			}
		}
		else
			message.setText("Please enter a value!!!!\n\"You didn't enter anything!\"");
	}

	public void checkSelect(String string) throws ClassNotFoundException, IOException {
		notes.setText("");
		message.setText("Welcome!!");
		if(!giveSet().contains(string)) 
		{
			updatePrice.setEnabled(false);
			update.setEnabled(false);
			viewPriceBut.setEnabled(false);
			message.setText("Please make a right selection");
		}
		else
		{
			updatePrice.setEnabled(true);
			update.setEnabled(false);
			viewPriceBut.setEnabled(true);
		}
		repaint();
	}

	class PizzaListerner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				getPizzeriaPrice(comboLayout.getSelectedItem().toString());
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class UpdateListerner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			doUpdateJob(comboLayout.getSelectedItem().toString());
		}

	}
	class Update implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				doUpdate(comboLayout.getSelectedItem().toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	class listernToSelect implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				checkSelect(comboLayout.getSelectedItem().toString());
				comboLayout.repaint();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}