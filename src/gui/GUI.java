package gui;


import java.awt.*;  // import the AWT graphic classes
import java.io.IOException;

import javax.swing.*;   // import the Swing classes

public class GUI
{
	public static void main(String args[])
	{
		// Note: this is an anonymous inner-class

		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try {
					createAndShowGUI(args);
				} catch (NumberFormatException | IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private static void createAndShowGUI(String args[]) throws NumberFormatException, IOException, ClassNotFoundException
	{
		// Create a JFrame and get its content pane

		JFrame      theFrame = new JFrame("PIZZERIA");
		Container   c = theFrame.getContentPane();

		// Instantiate the GUI, which is a JPanel

		TheUserGUI   theGui = new TheUserGUI();
		theGui.addComponents(theFrame);

		// Set the size of the frame and exit behavior

		theFrame.setPreferredSize(new Dimension(400, 400));
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the GUI to the frame

		c.add(theGui);

		// show the framE
		theFrame.pack();
		theFrame.setVisible(true);
	}
}