import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.BCrypt;
import classes.Controller;

@WebServlet("/AdminCreateClient")
public class CreateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CreateClientServlet() {
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
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
		
		try {
			Controller.createClient(firstName, lastName, hashed, CPR, email, mobile, street, bankerID, Integer.parseInt(postal), country, ds1);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/admin/AdminCreateClient");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.admin, session)){
			request.getRequestDispatcher("AdminCreateClient.jsp").forward(request, response);
			
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
}