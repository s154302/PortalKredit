package Client;

import java.io.IOException;
import java.sql.Connection;

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
import classes.Model;

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
		if(Model.checkAuth(Model.Type.client, session)){
		Client client = (Client) session.getAttribute("user");
		Connection con = Model.getConnection(ds1);
		client.setAccounts(Model.getAccounts(client.getClientID(), con));
		session.setAttribute("accounts", client.getAccounts());

		Model.cleanUpConnection(con);
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
		if(Model.checkAuth(Model.Type.client, session)){
			accountInfo(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
			
		}
	
		
	}
	
	private void accountInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String accountNumber = request.getParameter("accountNumber");
		String regNo = request.getParameter("regNo");
		Connection con = Model.getConnection(ds1);
		Account account = Model.getAccountInfo(accountNumber, regNo, con);
		session.setAttribute("account", account);
		Model.cleanUpConnection(con);
		response.sendRedirect("accountView");
	}
	
}
