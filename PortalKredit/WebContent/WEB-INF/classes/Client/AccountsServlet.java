package Client;

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

@WebServlet("/client/accounts")
public class AccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AccountsServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.client, session)){
		Client client = (Client) session.getAttribute("user");
		client.setAccounts(Controller.getAccounts(client.getClientID(), ds1));
		session.setAttribute("accounts", client.getAccounts());
		
		request.getRequestDispatcher("accounts.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
	
		String accountNumber = request.getParameter("accountNumber");
		int regNo = Integer.parseInt(request.getParameter("regNo"));
		
		Account account = Controller.getAccountInfo(accountNumber, regNo, ds1);
		session.setAttribute("account", account);
		
		response.sendRedirect("accountView");
	}
}
