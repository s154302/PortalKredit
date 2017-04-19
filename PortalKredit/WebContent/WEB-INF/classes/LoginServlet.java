

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			try {
				response.getWriter().println("Users:");
				Connection con = ds1.getConnection();
				PreparedStatement ps = con.prepareStatement("SELECT * FROM \"DTUGRP16\".\"USER\" WHERE \"USERID\"=? AND \"PASSWORD\"=?");
				
				ps.setString(1, userID);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				st = rs.next();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		if(st) {
			
			session.setAttribute("userID", userID);
			System.out.print(session.getAttribute("userID"));
			response.sendRedirect(request.getContextPath() + "/demo");
		} else {
			response.sendRedirect(request.getContextPath());
		}
		
		
	}

}
