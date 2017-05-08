import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Controller;

@WebServlet("/admin/CreateClientServlet")
public class CreateClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CreateClientServlet() {
		super();
	}
	
	@Resource(name = "jdbc/DB2")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Controller.generateID(ds1);
		response.sendRedirect(request.getContextPath() + "/admin/AdminCreateClient.jsp");
	}
}