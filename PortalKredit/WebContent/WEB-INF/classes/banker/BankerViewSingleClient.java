package banker;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Account;
import classes.Client;
import classes.Controller;

@WebServlet("/banker/ViewSingleClient")
public class BankerViewSingleClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerViewSingleClient(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		Client client = (Client) session.getAttribute("user");
		System.out.println("Andent tjek" + client.toString());
		session.setAttribute("accounts", client.getAccounts());
		
		request.getRequestDispatcher("ViewSingleClient.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String accountNumber = request.getParameter("accountNumber");
		
		
	}
}
