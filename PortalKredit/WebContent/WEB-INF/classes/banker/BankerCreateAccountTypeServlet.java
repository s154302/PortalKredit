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
import classes.Banker;
import classes.Controller;

@WebServlet("/banker/CreateAccountType")
public class BankerCreateAccountTypeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public BankerCreateAccountTypeServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	// Called from BankerNavbar.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		// Checking Authentication
		if(Controller.checkAuth(Controller.Type.banker, session)){
			request.getRequestDispatcher("CreateAccountType.jsp").forward(request, response);
		} else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	// Methoed when 'Create Account Type' bottom is clicked
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection con = Controller.getConnection(ds1);
		
		// Saving parameters
		String accountType = request.getParameter("accountType");
		Double interestRate = Double.parseDouble(request.getParameter("interestRate"));
		
		// Creating Account
		boolean status = Controller.createAccountType(accountType, interestRate, con);
		
		// Checking status and redirecting accordingly
		if(status){
			request.setAttribute("createAccountTypeCreated", "Account Type and Interest Rate has been created");
		} else {
			request.setAttribute("createAccountTypeDenied", "Account Type and Interest Rate could not be created");
		}
		request.getRequestDispatcher("CreateAccountType.jsp").forward(request, response);

		Controller.cleanUpConnection(con);
	}


}