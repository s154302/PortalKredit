package banker;
import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/banker/CreateClient")
public class BankerCreateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BankerCreateClientServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			Banker banker = (Banker) session.getAttribute("user");
			session.setAttribute("username", banker.getBankerID());
			request.getRequestDispatcher("CreateClient.jsp").forward(request, response);
		
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
		
		session.setAttribute("clientID", null);
		
		String firstName = request.getParameter("clientFirstName");
		String lastName = request.getParameter("clientLastName");
		String password = request.getParameter("clientPassword");
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(14));
		String CPR = request.getParameter("clientCPR");
		String email = request.getParameter("clientEmail");
		String mobile = request.getParameter("clientTelephone");
		String street = request.getParameter("clientStreet");
		String bankerID = request.getParameter("clientBankerID");
		String postal = request.getParameter("clientCity");
		String country = request.getParameter("clientCountry");
		
		Controller.createClient(firstName, lastName, hashed, CPR, email, mobile, street, bankerID, Integer.parseInt(postal), country, ds1);
		
		Banker banker = (Banker) session.getAttribute("user");
		banker.setClients(Controller.getClients(banker.getBankerID(), ds1));
		session.setAttribute("user", banker);
		
		response.sendRedirect(request.getContextPath() + "/banker/ViewClients");
	}
}