package io;

import java.io.*;

/**
 *  InputDataFile is a class that represents an input datafile.
 *
 *  @author Cathy Bishop
 */

// Note: no comments on purpose - read the signatures to figure out how to use this class!

public class InputDataFile
{
private String		_filename;
private BufferedReader	_inReader = null;

public InputDataFile(String filename)
{
	_filename = filename;
}

public String getName()
{
	return(_filename);
}

public BufferedReader getReader()
{
	return(_inReader);
}

public boolean isOpen()
{
	if (_inReader == null)
		return(false);
	else
		return(true);
}

public boolean open()
{
	if (_filename == null)
		return(false);

	try
	{
		_inReader = new BufferedReader(new FileReader(_filename));
	}
	catch	(IOException e)
	{
		_inReader = null;
		return(false);
	}

	return(true);
}

/** This method reads a string and returns null if it doesn't work (for instance, if it is at the end of the file */

public String readString()
{
	String inputString;

	try
	{	
		inputString = _inReader.readLine();
	}
	catch (IOException e)
	{
		inputString = null;
	}

	return(inputString);
}

public double readDouble() throws NumberFormatException, EOFException
{
	String	inString;
	double	number;

	inString = readString();
	if (inString == null)
		throw new EOFException();

	return(Double.parseDouble(inString));
}

public double readPositiveDouble()
{
	String	inString;
	double	number;

	inString = readString();
	try
	{
		number = Double.parseDouble(inString);
	}
	catch (NumberFormatException e1)
	{
		number = -1.0;
	}

	if (number < 0.0)
		number = -1.0;

	return(number);
}


public void close()
{
	try
	{
		_inReader.close();
	}
	catch	(IOException e){}

	_inReader = null;
}
}