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

import org.mindrot.jbcrypt.BCrypt;

import classes.Banker;
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
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
			createClient(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		

	}
	
	private void createClient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		request.getSession().setAttribute("clientID", null);
		
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

		Connection con = Controller.getConnection(ds1);
		
			boolean status = Controller.createClient(firstName, lastName, hashed, CPR, email, mobile, street, bankerID,
					postal, country, con);

			if(status){
				request.setAttribute("status", "Client was created");
			}
			else{
				request.setAttribute("status", "Client wasn't created due to an error");
			}
			Banker banker = (Banker) request.getSession().getAttribute("user");
			banker.setClients(Controller.getClients(banker.getBankerID(), con));
			request.getSession().setAttribute("user", banker);

			
			response.sendRedirect(request.getContextPath() + "/banker/ViewClients");
		
			Controller.cleanUpConnection(con);
		
	}
}