package Client;

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

import classes.Client;
import classes.Controller;

@WebServlet("/client/editInfo")
public class ClientEditInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ClientEditInfoServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		if(Controller.checkAuth(Controller.Type.client, session)){
			request.getRequestDispatcher("EditInfo.jsp").forward(request, response);
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
		
		if(Controller.checkAuth(Controller.Type.client, session)){
 
			
			editInfo(request, response, session);
			
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	//Needs to handle null values somehow.
	private void editInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException, ServletException{
		String clientID = (String)session.getAttribute("userID");
		String password = request.getParameter("password");
		String email = request.getParameter("email"); 
		String mobile = request.getParameter("mobile");  
		String street = request.getParameter("street"); 
		String postal = request.getParameter("postal");
		Connection con = Controller.getConnection(ds1);
		try {
			if(!request.getParameter("password").isEmpty()){
				Controller.changeClientPassword(clientID, password, con);
			}
			
			Controller.clientEditClient(clientID, email, mobile, street, postal, con);
			Client client = (Client)session.getAttribute("user");
			client.setEmail(email);
			client.setPhoneNo(mobile);
			client.setStreet(street);
			client.setPostal(postal);
			session.setAttribute("user", client);
			
			doGet(request,response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Controller.cleanUpConnection(con);
		}
	}
}
