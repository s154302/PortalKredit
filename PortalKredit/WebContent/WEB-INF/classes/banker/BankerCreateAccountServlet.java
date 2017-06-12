package banker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Banker;
import classes.Controller;

@WebServlet("/banker/CreateAccount")
public class BankerCreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerCreateAccountServlet() {
		super();
	}
	
	// Is called from ViewSingleClient.jsp file
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		//Authenticating the user
		if(Controller.checkAuth(Controller.Type.banker, session)){
			Connection con = Controller.getConnection(ds1);
			
			//Setting accountTypes for dropdown
			session.setAttribute("accountTypes", Controller.getAccountTypes(con));
			Controller.cleanUpConnection(con);
			request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			
			createAccount(request, response);
		
		} else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	private void createAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//Saving information from the user's input and Banker's information
		Banker banker = (Banker) request.getSession().getAttribute("user");
		String accountName = request.getParameter("clientAccountName");
		String accountNumber = request.getParameter("clientAccountNumber");
		String regNo = banker.getRegNo();
		String accountType = request.getParameter("clientAccountType");
		String clientID = (String) request.getSession().getAttribute("clientID");
		double balance = Double.parseDouble(request.getParameter("clientBalance"));
		String currency = request.getParameter("clientCurrency");
		
		request.getSession().setAttribute("clientID", clientID);
		Connection con = Controller.getConnection(ds1);

		try {
			// Creating the account
			Controller.createAccount(accountName, accountNumber, regNo, accountType, clientID, balance, currency, con);
			response.sendRedirect(request.getContextPath() + "/banker/ViewSingleClient");
			
		} catch (SQLException e) {
			// Catching error and informing the user
			e.printStackTrace();
			request.setAttribute("createAccountStatus", "Wrong value inserted");
			request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
		}finally{
			Controller.cleanUpConnection(con);
		}
	}
}