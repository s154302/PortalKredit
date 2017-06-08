import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Client;
import classes.Controller;

@WebServlet("/AdminDeleteClient")
public class DeleteClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DeleteClientServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con= Controller.getConnection(ds1);
		ArrayList<Client> clientList = Controller.getClientList(con);
		request.setAttribute("list", clientList);

		Controller.cleanUpConnection(con);
		Controller.adminCheckAuth("AdminDeleteClient.jsp", request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Controller.checkAuth(Controller.Type.admin, request.getSession())){
		
			deleteClient(request,response);
		
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
	}
	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String search = request.getParameter("search-client");
		String delete = request.getParameter("username");
		Connection con= Controller.getConnection(ds1);
		
		if(search != null) {
			ArrayList<Client> clientList = Controller.searchClient(request.getParameter("search-term"), con);
			request.setAttribute("list", clientList);
			request.getRequestDispatcher("AdminDeleteClient.jsp").forward(request, response);
		}
		if(delete != null) {
			Controller.deleteClient(delete, con);
			doGet(request, response);
		}
		Controller.cleanUpConnection(con);
	}
	

}
