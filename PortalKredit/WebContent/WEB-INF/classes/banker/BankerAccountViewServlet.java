package banker;

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

@WebServlet("/banker/ViewClientAccount")
public class BankerAccountViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerAccountViewServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	// Is called from the BankerViewSingleClientServlet
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		// Checking the user has access to the page, else redirect to the login page
		if(Model.checkAuth(Model.Type.banker, session)){
			Connection con = Model.getConnection(ds1);
			Account account = (Account) session.getAttribute("account");
			
			// Finding the transactions on the given account
			session.setAttribute("transactions", Model.getNewTransactions(account.getAccountNumber(), account.getRegNo(), con));
			Model.cleanUpConnection(con);
			request.getRequestDispatcher("ViewClientAccount.jsp").forward(request, response);

		} else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
			}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		// Determining whether the page is redirected to Edit Account ot Delete Account
		if(request.getParameter("EditAccount") != null){
			response.sendRedirect(request.getContextPath() + "/banker/EditAccount");
		
		} else if(request.getParameter("DeleteAccount") != null){
			response.sendRedirect(request.getContextPath() + "/banker/DeleteAccount");	
		}
		
	}
	
}
