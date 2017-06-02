package banker;
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

import classes.Client;
import classes.Controller;
import classes.Banker;

@WebServlet("/banker/ViewClients")
public class BankerViewClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public BankerViewClientServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		Banker banker = (Banker) session.getAttribute("user");
		
		ArrayList<Client> clientList = banker.getClients();
		session.setAttribute("list", clientList);

		request.getRequestDispatcher("ViewClients.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		String clientID = request.getParameter("username");
		
		session.setAttribute("ClientID", clientID);
		response.sendRedirect(request.getContextPath() + "/banker/ViewSingleClient.jsp");
		
		doGet(request, response);
	}
	

}
