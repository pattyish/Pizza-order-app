package servelets;
import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import client.Client;

@WebServlet(urlPatterns= {"/Servelet11"})

public class Servelet11 extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		Client client = new Client();

		PrintWriter out = resp.getWriter();

		resp.setContentType("text/html");
		out.println("<html><body>");
		out.println("<H3>Welcome on Online Pizza Order System!</H3>");
		out.println("<H3>SELECT A PIZZERRIA TO ORDER:</H3>");
		out.println("<ul>");
		ArrayList<String> pizzeria= new ArrayList<>();
		
		out.println("<form action=\"http://localhost:8080/Program_1.4/Servelet22\" method=\"get\">");
		out.println("<select name=\"PIZZA\"> ");
		try {

			for(String pizz:client.giveSet()) 
			{
				out.println("<option value= "+pizz+"> "+pizz+" "+" </option>");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		out.println("</select>");	
		out.println("<input type=\"submit\" value=\"CONTINUE\">");
		
		out.println("</form>");
		out.println("</body></html>");


	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		PrintWriter out = resp.getWriter();

		doGet(req, resp);
	}
}