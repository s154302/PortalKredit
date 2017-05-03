
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Controller;

@WebServlet("/demo")
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Demo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Resource(name = "jdbc/DB2")
	private DataSource ds1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().println("Hello, World!");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("userID") != null){
			try {
				response.getWriter().println("type: "+ session.getAttribute("type").toString() +" \n ");
				
				response.getWriter().println("Users:");
				Connection con = ds1.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM \"DTUGRP16\".\"CLIENT\"");
	
				while (rs.next()) {
					response.getWriter().println("Username: " + rs.getString("CLIENTID"));
					response.getWriter().println("Password: " + rs.getString("PASSWORD"));
				}
				response.getWriter().println(Controller.getList("Client", "Banker", 1, "First_name", ds1).toString());
				rs.close();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
