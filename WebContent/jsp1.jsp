<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%@ page import="client.ServerConnect"%>
<%@ page import="model.PizzaConfig"%>
<%@ page import="java.util.ArrayList"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Final Order!</title>
</head>
<body>
<%
	PizzaConfig configServer = (PizzaConfig) ServerConnect.conect(request.getParameter("pizz"), "Print");
	out.println("<H2> Name: " + configServer.getConfigName() + "</H2>");
	out.println("<H3> BASE Price= " + configServer.getBasePrice() + "</H3>");
	out.println("Your Selection are as follows: ");
	ArrayList<String> optionSets = configServer.viewOptionSet();
	double increase=0;

	for (String str : optionSets) {
		out.println("<li><H4> " + str + " = " + request.getParameter(str) + " </H4>");
		out.println("||~~~~~>>Price Increase= " + configServer.getPriceIncrease(str, request.getParameter(str)));
		increase=increase+configServer.getPriceIncrease(str, request.getParameter(str));
	}
	
	out.println("<H3> Total price= " + (configServer.getBasePrice()+increase) + " Rwf</H3>");
	
%>
<p>Thank you for your Order!!
</br> We will get Back to you soon!</p>
<H4> :~ Regards</br>__ Ihirwe __</H4>
</body>
</html>