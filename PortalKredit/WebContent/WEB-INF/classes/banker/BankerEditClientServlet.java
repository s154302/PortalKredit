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

import classes.Account;
import classes.BCrypt;
import classes.Banker;
import classes.Client;
import classes.Controller;

@WebServlet("/banker/EditClient")
public class BankerEditClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerEditClientServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			request.getRequestDispatcher("EditClient.jsp").forward(request, response);
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
		
		String clientID = (String) session.getAttribute("clientID");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String newClientID = request.getParameter("clientID"); 
		String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(14)); 
		String email = request.getParameter("email"); 
		String mobile = request.getParameter("mobile");  
		String street = request.getParameter("street"); 
		String bankerID = request.getParameter("bankerID"); 
		int postal = Integer.parseInt(request.getParameter("postal"));
		String country = request.getParameter("country"); 
		
		try {
			Controller.editClient(clientID, newClientID, firstName, lastName, password, email,
					mobile, street, bankerID, postal, country, ds1);
			
			session.setAttribute("clientID", clientID);
			response.sendRedirect(request.getContextPath() + "/banker/ViewClient");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}