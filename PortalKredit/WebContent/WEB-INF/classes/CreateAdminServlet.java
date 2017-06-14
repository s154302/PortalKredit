

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import classes.Model;

/*
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/AdminCreateAdmin")
public class CreateAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateAdminServlet() {
        super();

    }
    @Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			createAdmin(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Model.adminCheckAuth("AdminCreateAdmin.jsp",request,response);
		if(request.getAttribute("createAdminStatus") == null) {
			request.setAttribute("createAdminStatus", 0);
		}
	}
	
	private void createAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String adminID = request.getParameter("adminID");
		String adminPassword = request.getParameter("adminPassword");
		String hashed = BCrypt.hashpw(adminPassword, BCrypt.gensalt(14));
		
		Connection con = Model.getConnection(ds1);
		boolean status = Model.createAdmin(adminID, hashed, con);
		Model.cleanUpConnection(con);
		if(status){
			request.setAttribute("createAdminStatus", 1);
		}
		else{
			request.setAttribute("createAdminStatus", -1);
		}
		doGet(request,response);
		
	}

}
