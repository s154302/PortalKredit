package banker;
import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/banker/EditAccount")
public class BankerEditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerEditAccountServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			request.getRequestDispatcher("EditClientAccount.jsp").forward(request, response);
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
		
		Account account = (Account) session.getAttribute("account");
		String accountName = request.getParameter("clientAccountName");
		String accountType = request.getParameter("clientAccountType");
		String clientID = request.getParameter("clientID");
		String currency = request.getParameter("clientCurrency");
		
		Controller.editAccount(accountName, account.getAccountNumber(), account.getRegNo(), accountType, clientID, account.getBalance(), currency, ds1);
		account = Controller.getAccountInfo(account.getAccountNumber(), account.getRegNo(), ds1);
		
		session.setAttribute("account", account);
		
		response.sendRedirect(request.getContextPath() + "/banker/ViewClientAccount");
	}
}