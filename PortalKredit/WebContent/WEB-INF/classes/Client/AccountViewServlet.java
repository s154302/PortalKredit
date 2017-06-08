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
	
		session.setAttribute("transactions", Controller.getNewTransactions(account.getAccountNumber(), account.getRegNo(), ds1));
		
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		Account account = (Account)session.getAttribute("account");
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) session.getAttribute("transactions");
		transactions.addAll(Controller.getOldTransactions(account.getAccountNumber(), account.getRegNo(), ds1));
		session.setAttribute("transactions", transactions);
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
}
