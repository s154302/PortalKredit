package Client;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Controller;

@WebServlet("/client/frontpage")
public class ClientFrontpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ClientFrontpageServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.client, session)){
		request.getRequestDispatcher("frontpage.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		
	}
}