

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Model;

/**
 * Servlet implementation class AdminCreateBranch
 */
@WebServlet("/admin/AdminDeleteBranch")
public class AdminDeleteBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 @Resource(name = "jdbc/exampleDS")
		private DataSource ds1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(Model.checkAuth(Model.Type.admin, session)){
			Connection con = Model.getConnection(ds1);
			session.setAttribute("branches", Model.getBranches(con));
			
			Model.cleanUpConnection(con);
			
			request.getRequestDispatcher("AdminDeleteBranch.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			String regNo = request.getParameter("regNo");
			Connection con = Model.getConnection(ds1);
			if(Model.checkForOpenAccounts(regNo, con)){
				request.setAttribute("status", "This branch still have open accounts");
			}else{
				if(Model.deleteBranch(regNo, con)){
					request.setAttribute("status", "Branch " + regNo + " was deleted successfully");
					
				}else{
					request.setAttribute("status", "Error on deleting branch " + regNo);
				}
			}
			Model.cleanUpConnection(con);
			doGet(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}

	}

}
