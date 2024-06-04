package servelets;
import java.io.*;

import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import client.Client;
import client.ServerConnect;
import model.PizzaConfig;

@WebServlet(urlPatterns= {"/Servelet22"})

public class Servelet22 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	static String pizz;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Client client = new Client();
		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html");

		out.println("<html><body>");
		out.println("<H2>Here is you pizza details:</H2>");
		out.println("<ul>");

		out.println("<form action=\"http://localhost:8080/Program_1.4/jsp1.jsp\" method=\"get\">");
		
		PizzaConfig configServer= (PizzaConfig) ServerConnect.conect(req.getParameter("PIZZA"), "Print");
		//out.println("<H2> NAME: "+configServer.getConfigName()+"</H2>");
		 pizz=req.getParameter("PIZZA");
		
		out.println("Name: <input type=\"text\" name=\"pizz\" value= "+configServer.getConfigName()+">");
		
		ArrayList<String> optionSets=configServer.viewOptionSet();

		for(String optionSet:optionSets) 
		{
			
			out.println("<li><H3> "+optionSet+"</H3>");
			ArrayList<String> optionss= configServer.getOptions(optionSet);

			out.println("<select name="+optionSet+">");
			for(String opt:optionss) {
				out.println("<option value= "+opt+"> "+opt+" "+" </option>");
			}
			out.println("</select>");	
			out.println("<br>");
		}

		//out.println("<p> <a href = \"http://localhost:8080/Program_1.4/jsp1.jsp\" > <button>ORDER</button> </a>");
		
		out.println("<input type=\"submit\" value=\"ORDER\">");
		out.println("</form>");
		out.println("</body></html>");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		PrintWriter out = resp.getWriter();

		doGet(req, resp);
	}
	
	public static String getPizz() {
		return pizz;
		
	}
}