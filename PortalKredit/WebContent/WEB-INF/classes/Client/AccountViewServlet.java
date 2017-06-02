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

@WebServlet("/client/accountView")
public class AccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AccountViewServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		String accountNumber = (String) session.getAttribute("accountNumber");
		int regNo = (int) session.getAttribute("regNo");
		
		session.setAttribute("transactions", Controller.getNewTransactions(accountNumber, regNo, ds1));
		
		request.getRequestDispatcher("accountView.jsp").forward(request, response);
	}
}
