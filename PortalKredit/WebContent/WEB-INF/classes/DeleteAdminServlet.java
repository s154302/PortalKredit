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
import classes.Model;

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
		
		//TODO - Is Auth checked correctly here?
		Connection con = Model.getConnection(ds1);
		ArrayList<Admin> adminList = Model.getAdminList(con);
		Model.cleanUpConnection(con);
		request.setAttribute("list", adminList);
		Model.adminCheckAuth("AdminDeleteAdmin.jsp",request,response);
		if(request.getAttribute("deleteAdminStatus") == null) {
			request.setAttribute("deleteAdminStatus", 0);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			deleteAdmin(request,response);
		
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminID = request.getParameter("username");
		
		Connection con = Model.getConnection(ds1);
		if(Model.deleteAdmin(adminID, con)){
			request.setAttribute("deleteAdminStatus", 1);
		}else{
			request.setAttribute("deleteAdminStatus", -1);
		}
		request.setAttribute("deleteAdmin", adminID);
		Model.cleanUpConnection(con);
		doGet(request, response);
		
	}
	

}
