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
import classes.Model;
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
		
		if(Model.checkAuth(Model.Type.client, session)){
			Account account = (Account)session.getAttribute("account");
			Connection con = Model.getConnection(ds1);
			session.setAttribute("transactions", Model.getNewTransactions(account.getAccountNumber(), account.getRegNo(), con));
			Model.cleanUpConnection(con);
			request.getRequestDispatcher("accountView.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Model.checkAuth(Model.Type.client, session)){
			accountTransactions(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
	}
	
	private void accountTransactions(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		Connection con = Model.getConnection(ds1);
		
		// Finding old and new transactions from the DB
		Account account = (Account)session.getAttribute("account");
		ArrayList<Transaction> transactions = Model.getNewTransactions(account.getAccountNumber(), account.getRegNo(), con);
		ArrayList<Transaction> oldTransactions = Model.getOldTransactions(account.getAccountNumber(), account.getRegNo(), con);
		
		transactions.addAll(oldTransactions);
		session.setAttribute("transactions", transactions);
		
		Model.cleanUpConnection(con);
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
}
