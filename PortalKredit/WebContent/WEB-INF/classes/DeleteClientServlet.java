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

import classes.Admin;
import classes.Client;
import classes.Controller;

@WebServlet("/AdminDeleteClient")
public class DeleteClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DeleteClientServlet(){
		super();
	}
	@Resource(name = "jdbc/DB2")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		ArrayList<Client> clientList = Controller.getClientList(ds1);
		request.setAttribute("list", clientList);

		request.getRequestDispatcher("AdminDeleteClient.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String clientID = request.getParameter("username");
		
		Controller.deleteClient(clientID, ds1);
		
		doGet(request, response);
	}
	

}
