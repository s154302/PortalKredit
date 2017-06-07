package banker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.BCrypt;
import classes.Banker;
import classes.Client;
import classes.Controller;

@WebServlet("/banker/CreateAccount")
public class BankerCreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerCreateAccountServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
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
		PrintWriter out = response.getWriter();
		
		String accountName = request.getParameter("clientAccountName");
		String accountNumber = request.getParameter("clientAccountNumber");
		int regNo = Integer.parseInt(request.getParameter("clientRegNo"));
		String accountType = request.getParameter("clientAccountType");
		String clientID = request.getParameter("clientID");
		double balance = Double.parseDouble(request.getParameter("clientBalance"));
		String currency = request.getParameter("clientCurrency");
		
		session.setAttribute("clientID", clientID);
		try {
			Controller.createAccount(accountName, accountNumber, regNo, accountType, clientID, balance, currency, ds1);
			response.sendRedirect(request.getContextPath() + "/banker/ViewSingleClient");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("createAccountStatus", "Wrong value inserted");
			request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
		}
		
//		Banker banker = (Banker) session.getAttribute("user");
//		banker.getClient(clientID).setAccounts( Controller.getAccounts(clientID, ds1));
//		session.setAttribute("user", banker);

	}
}