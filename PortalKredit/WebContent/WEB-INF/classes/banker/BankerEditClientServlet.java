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

import classes.Model;

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
		
		if(Model.checkAuth(Model.Type.banker, session)){
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
		
		if(Model.checkAuth(Model.Type.banker, session)){
			editClient(request, response, session);
			
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
		
		

	}
	
	private void editClient(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
		String clientID = (String) session.getAttribute("clientID");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String email = request.getParameter("email"); 
		String mobile = request.getParameter("mobile");  
		String street = request.getParameter("street"); 
		String bankerID = request.getParameter("bankerID"); 
		String postal = request.getParameter("postal");
		String country = request.getParameter("country"); 
		
		Connection con = Model.getConnection(ds1);
		try {
			
			if(!request.getParameter("password").isEmpty()){
				Model.changeClientPassword(clientID, password, con);
			}
			
			Model.editClient(clientID, firstName, lastName, email,
					mobile, street, bankerID, postal, country, con);

			
			session.setAttribute("clientID", clientID);
			response.sendRedirect(request.getContextPath() + "/banker/ViewClients");
			session.setAttribute("user", Model.getBankerInfo((String) session.getAttribute("userID"), con));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Model.cleanUpConnection(con);
		}
	}
}