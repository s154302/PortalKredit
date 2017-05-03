

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Controller;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Resource(name = "jdbc/DB2")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);	
		PrintWriter out = response.getWriter();
		String clientID = request.getParameter("userID");
		String password = request.getParameter("password");
		boolean st = false;

		//TODO: Move the method to an utility class and call it authenticate
		st = Controller.authenticate(clientID, password, ds1);
		
		if(st) {
			
			session.setAttribute("clientID", clientID);
			System.out.print(session.getAttribute("clientID"));
			response.sendRedirect(request.getContextPath() + "/demo");
		} else {
			response.sendRedirect(request.getContextPath());
		}
		
		
	}

}
