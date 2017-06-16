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

@WebServlet("/banker/ViewSingleClient")
public class BankerViewSingleClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerViewSingleClientServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		if(Model.checkAuth(Model.Type.banker,session)){
			Connection con = Model.getConnection(ds1);
			String clientID = (String) session.getAttribute("clientID");
			Client client = Model.getClientInfo(clientID, con);
				
			client.setAccounts(Model.getAccounts(client.getClientID(), con));
			session.setAttribute("accounts", client.getAccounts());
			session.setAttribute("clientName", client.getFirstName());
			
			Model.cleanUpConnection(con);
			request.getRequestDispatcher("ViewSingleClient.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		if(Model.checkAuth(Model.Type.banker, session)){
			viewSingleClient(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
		
		
	}
	private void viewSingleClient(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		
		String accountNumber = request.getParameter("accountNumber");
		String regNo = request.getParameter("regNo");
		Connection con = Model.getConnection(ds1);
		Account account = Model.getAccountInfo(accountNumber, regNo, con);
		session.setAttribute("account", account);
		Model.cleanUpConnection(con);
		
		response.sendRedirect(request.getContextPath() + "/banker/ViewClientAccount");
		
	}
}
