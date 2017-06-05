

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.BCrypt;
import classes.Controller;

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
		
		String adminID = request.getParameter("adminID");
		String adminPassword = request.getParameter("adminPassword");
		String hashed = BCrypt.hashpw(adminPassword, BCrypt.gensalt(14));
		
		Controller.createAdmin(adminID, hashed, ds1);

		response.sendRedirect(request.getContextPath() + "/admin/AdminCreateAdmin");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.admin, session)){
			request.getRequestDispatcher("AdminCreateAdmin.jsp").forward(request, response);
			
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

}
