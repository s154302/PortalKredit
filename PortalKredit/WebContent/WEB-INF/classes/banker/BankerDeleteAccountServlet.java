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

@WebServlet("/banker/DeleteAccount")
public class BankerDeleteAccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public BankerDeleteAccountServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	// Called from ViewClientAccount.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			
			request.getRequestDispatcher("DeleteAccount.jsp").forward(request, response);
			
		} else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		Connection con = Controller.getConnection(ds1);
		// Clicked button canceled
		if(request.getParameter("cancel") != null){
			response.sendRedirect(request.getContextPath() + "/banker/ViewClientAccount");
		
		// Clicked button confirm
		} else if(request.getParameter("confirm") != null){
			
			String password = request.getParameter("password");
			String bankerID = ((Banker)session.getAttribute("user")).getBankerID();
			Boolean control = Controller.authenticate(bankerID, password, con, session);
			
			// authenticating the banker ID
			if(control){
				
				Account account = (Account) session.getAttribute("account");
				String regNo = account.getRegNo();
				String accountNumber = account.getAccountNumber();
				
				// Deleting the account
				Boolean isDeleted = Controller.deleteAccount(regNo, accountNumber, con);
				
				if(isDeleted){
					response.sendRedirect(request.getContextPath() + "/banker/ViewSingleClient");
				
				} else {
					request.setAttribute("deleteStatus", "This account cannot be deleted");
					request.getRequestDispatcher("DeleteAccount.jsp").forward(request, response);
				}
			
				// If control did not accept
			} else {
				request.setAttribute("deleteStatus", "Wrong Password");
				request.getRequestDispatcher("DeleteAccount.jsp").forward(request, response);
			}
		}
		Controller.cleanUpConnection(con);
	}


}