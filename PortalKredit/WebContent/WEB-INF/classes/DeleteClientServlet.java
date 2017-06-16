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
import classes.Model;

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
		
		Connection con= Model.getConnection(ds1);
		ArrayList<Client> clientList = Model.getClientList(con);
		request.setAttribute("list", clientList);

		Model.cleanUpConnection(con);
		Model.adminCheckAuth("AdminDeleteClient.jsp", request, response);
		if(request.getAttribute("deleteClientStatus") == null) {
			request.setAttribute("deleteClientStatus", 0);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
		
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
		Connection con= Model.getConnection(ds1);
		
		if(search != null) {
			ArrayList<Client> clientList = Model.searchClient(request.getParameter("search-term"), con);
			request.setAttribute("list", clientList);
			request.getRequestDispatcher("AdminDeleteClient.jsp").forward(request, response);
		}
		if(delete != null) {
			if(Model.deleteClient(delete, con)){
				request.setAttribute("deleteClientStatus", 1);
			}else{
				request.setAttribute("deleteClientStatus", -1);
			}
			request.setAttribute("deleteClient", delete);
			doGet(request, response);
		}
		Model.cleanUpConnection(con);
	}
	

}
