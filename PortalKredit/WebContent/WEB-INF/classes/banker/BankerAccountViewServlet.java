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

@WebServlet("/banker/ViewClientAccount")
public class BankerAccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerAccountViewServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.banker, session)){
			
		
		Account account = (Account) session.getAttribute("account");
		session.setAttribute("transactions", Controller.getNewTransactions(account.getAccountNumber(), account.getRegNo(), ds1));
		request.getRequestDispatcher("ViewClientAccount.jsp").forward(request, response);
		
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
			}
	}
	

	
}
