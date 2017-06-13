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
		
		//TODO - bliver der tjekekr Auth rigtigt her ? 
		Connection con = Controller.getConnection(ds1);
		ArrayList<Admin> adminList = Controller.getAdminList(con);
		Controller.cleanUpConnection(con);
		request.setAttribute("list", adminList);
		Controller.adminCheckAuth("AdminDeleteAdmin.jsp",request,response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if(Controller.checkAuth(Controller.Type.admin, request.getSession())){
			deleteAdmin(request,response);
		
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminID = request.getParameter("username");
		
		Connection con = Controller.getConnection(ds1);
		if(Controller.deleteAdmin(adminID, con)){
			request.setAttribute("status", adminID + " was deleted.");
		}else{
			request.setAttribute("status", adminID + "couldn't be deleted.");
		}
		Controller.cleanUpConnection(con);
		doGet(request, response);
		
	}
	

}
