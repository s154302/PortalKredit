import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Controller;

@WebServlet("/admin/CreateClientServlet")
public class CreateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CreateClientServlet() {
		super();
	}
	
	@Resource(name = "jdbc/DB2")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String firstName = request.getParameter("clientFirstName");
		String lastName = request.getParameter("clientLastName");
		String password = request.getParameter("clientPassword");
		String stringCPR = request.getParameter("clientCPR");
		String email = request.getParameter("clientEmail");
		String mobile = request.getParameter("clientTelephone");
		String street = request.getParameter("street");
		String bankerID = request.getParameter("clientBankerID");
		String postal = request.getParameter("clientCity");
		String country = request.getParameter("clientCountry");
		
		BigDecimal CPR = new BigDecimal(stringCPR);
		
		PrintWriter out = response.getWriter();
		Controller.createClient(firstName, lastName, password, CPR, email, mobile, street, bankerID, Integer.parseInt(postal), country, ds1);
		response.sendRedirect(request.getContextPath() + "/admin/AdminCreateClient.jsp");
	}
}