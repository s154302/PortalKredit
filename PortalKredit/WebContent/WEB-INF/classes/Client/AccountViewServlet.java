package Client;

import java.io.IOException;
import java.sql.Connection;
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
import classes.Controller;
import classes.Transaction;

@WebServlet("/client/accountView")
public class AccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AccountViewServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	//TODO - Add some auth here
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		Account account = (Account)session.getAttribute("account");
		Connection con = Controller.getConnection(ds1);
		session.setAttribute("transactions", Controller.getNewTransactions(account.getAccountNumber(), account.getRegNo(), con));
		Controller.cleanUpConnection(con);
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.client, session)){
			accountTransactions(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
	}
	private void accountTransactions(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		Account account = (Account)session.getAttribute("account");
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) session.getAttribute("transactions");
		Connection con = Controller.getConnection(ds1);
		transactions.addAll(Controller.getOldTransactions(account.getAccountNumber(), account.getRegNo(), con, session));
		session.setAttribute("transactions", transactions);
		Controller.cleanUpConnection(con);
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
}
