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
import classes.Controller;

@WebServlet("/AdminDeleteAdmin")
public class DeleteAdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DeleteAdminServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Admin> adminList = Controller.getAdminList(ds1);
		request.setAttribute("list", adminList);
		Controller.adminCheckAuth("AdminDeleteAdmin.jsp",request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String adminID = request.getParameter("username");
		
		Controller.deleteAdmin(adminID, ds1);
		
		doGet(request, response);
	}
	

}
