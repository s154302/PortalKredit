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
import classes.Banker;
import classes.Client;
import classes.Controller;

@WebServlet("/banker/ViewSingleClient")
public class BankerViewSingleClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerViewSingleClientServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		String clientID = (String) session.getAttribute("clientID");
		Client client = Controller.getClientInfo(clientID, ds1);
		
		session.setAttribute("accounts", client.getAccounts());
		session.setAttribute("clientName", client.getFirstName());
		
		request.getRequestDispatcher("ViewSingleClient.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String accountNumber = request.getParameter("accountNumber");
		
		
	}
}
