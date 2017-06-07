

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

import org.mindrot.jbcrypt.BCrypt;

import classes.Controller;

/**
 * Servlet implementation class CreateBankerServlet
 */
@WebServlet("/AdminCreateBanker")
public class CreateBankerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateBankerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Resource(name = "jdbc/exampleDS")
    private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Controller.checkAuth(Controller.Type.admin, request.getSession())){
			createBanker(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Controller.adminCheckAuth("AdminCreateBanker.jsp",request,response);

	}
	private void createBanker(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String firstName = request.getParameter("bankerFirstName");
		String lastName = request.getParameter("bankerLastName");
		String password = request.getParameter("bankerPassword");
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(14));
		String email = request.getParameter("bankerEmail");
		String telephone = request.getParameter("bankerTelephone");
		String regno = request.getParameter("bankerReg");
		
		Controller.createBanker(firstName, lastName, hashed, email, telephone, regno, ds1);
		response.sendRedirect(request.getContextPath() + "/admin/AdminCreateBanker");
		
	}

}
