package Client;

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Banker;
import classes.Branch;
import classes.Controller;

@WebServlet("/client/contact")
public class ClientContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ClientContactServlet(){
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
			Connection con = Controller.getConnection(ds1);
			Banker banker = Controller.getAdvisor((String) session.getAttribute("userID"), con);
			
			session.setAttribute("banker", banker);
			if(banker != null){
				Branch branch = Controller.getBranchInfo(banker.getRegNo(), con);
				session.setAttribute("branch", branch);
			}

			Controller.cleanUpConnection(con);
			request.getRequestDispatcher("contact.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		if(Controller.checkAuth(Controller.Type.client, request.getSession())){
			
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}

		
		
	}
}