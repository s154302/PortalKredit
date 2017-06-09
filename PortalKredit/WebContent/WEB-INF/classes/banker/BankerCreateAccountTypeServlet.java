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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Controller.checkAuth(Controller.Type.banker, session)){

			request.getRequestDispatcher("CreateAccountType.jsp").forward(request, response);
			
		} else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		Connection con = Controller.getConnection(ds1);
		
		String accountType = request.getParameter("accountType");
		System.out.println("interest rate: "+request.getParameter("interestRate"));
		Double interestRate = Double.parseDouble(request.getParameter("interestRate"));
		boolean status = Controller.createAccountType(accountType, interestRate, con);
		if(status){
			request.setAttribute("createAccountTypeStatus", "Account Type and Interest Rate has been created");
		} else {
			request.setAttribute("createAccountTypeStatus", "Account Type and Interest Rate could not be created");
		}
		request.getRequestDispatcher("CreateAccountType.jsp").forward(request, response);
		

		Controller.cleanUpConnection(con);
	}


}