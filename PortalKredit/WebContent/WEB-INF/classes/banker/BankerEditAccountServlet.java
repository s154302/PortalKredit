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
import classes.Model;

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
		Connection con = Model.getConnection(ds1);
		if(Model.checkAuth(Model.Type.banker, session)){
			session.setAttribute("accountTypes", Model.getAccountTypes(con));
			request.getRequestDispatcher("EditClientAccount.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		Model.cleanUpConnection(con);
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Model.checkAuth(Model.Type.banker, session)){
			editAccount(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
		}
	
	private void editAccount(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		Account account = (Account) session.getAttribute("account");
		String accountName = request.getParameter("clientAccountName");
		String accountType = request.getParameter("clientAccountType");
		String clientID = request.getParameter("clientID");
		String currency = request.getParameter("clientCurrency");
		Connection con = Model.getConnection(ds1);
		Model.editAccount(accountName, account.getAccountNumber(), account.getRegNo(), 
				accountType, clientID, account.getBalance(), currency, con);
		account = Model.getAccountInfo(account.getAccountNumber(), account.getRegNo(), con);

		session.setAttribute("account", account);
		Model.cleanUpConnection(con);
		response.sendRedirect(request.getContextPath() + "/banker/ViewClientAccount");
	}
}