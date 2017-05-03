

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
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");
		boolean st = false;

		//TODO: Move the method to an utility class and call it authenticate
		st = Controller.authenticate(userID, password, ds1, session);
		
		if(st) {
			// TODO: overvej at sætte attributter til henholdsvis clientID, bankerID og adminID
			session.setAttribute("userID", userID);
			switch((Controller.Type)session.getAttribute("type")){
			case client:
				response.sendRedirect(request.getContextPath() + "/welcome.jsp");
				break;
			case banker:
				response.sendRedirect(request.getContextPath() + "/banktest123");
				break;
			case admin:
				response.sendRedirect(request.getContextPath() + "/admin/AdminControl.jsp");
				break;
			default:
				break;
			}
			
		} else {
			response.sendRedirect(request.getContextPath());
		}
		
		
	}

}
