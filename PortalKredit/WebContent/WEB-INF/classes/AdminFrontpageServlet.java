import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Controller;

/*
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/AdminFrontpage")
public class AdminFrontpageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminFrontpageServlet() {
		super();

	}

	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (Controller.checkAuth(Controller.Type.admin, session)) {
			request.getRequestDispatcher("AdminControl.jsp").forward(request, response);

		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

}
