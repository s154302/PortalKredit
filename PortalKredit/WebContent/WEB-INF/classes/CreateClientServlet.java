import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import classes.Model;

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
		
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			createClient(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Model.adminCheckAuth("AdminCreateClient.jsp",request,response);
		if(request.getAttribute("createClientStatus") == null) {
			request.setAttribute("createClientStatus", 0);
		}

	}
	private void createClient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
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
		
		Connection con = Model.getConnection(ds1);
		
		boolean status = Model.createClient(firstName, lastName, hashed, CPR, email, mobile, street, bankerID,
					postal, country, con);
		if(status){
			request.setAttribute("createClientStatus", 1);
		}else{
			request.setAttribute("createClientStatus", -1);
		}
		Model.cleanUpConnection(con);
		
		doGet(request,response);
		
	}
}