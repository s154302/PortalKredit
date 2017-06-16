

import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Model;

/**
 * Servlet implementation class AdminCreateBranch
 */
@WebServlet("/admin/AdminCreateBranch")
public class AdminCreateBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String regNo, bankName, postal, country, street, phone;
	
	 @Resource(name = "jdbc/exampleDS")
		private DataSource ds1;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model.adminCheckAuth("AdminCreateBranch.jsp",request,response);
		if(request.getAttribute("createBranchStatus") == null) {
			request.setAttribute("createBranchStatus", 0);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			Connection con = Model.getConnection(ds1);
			regNo = request.getParameter("branchRegno");
			bankName = request.getParameter("bankName");
			postal = request.getParameter("postal");
			country = request.getParameter("country");
			street = request.getParameter("street");
			phone = request.getParameter("phone");
			if(Model.getBranchInfo(regNo, con) != null){
				request.setAttribute("status", "This branch already exits");
				keepValues(request);
			}else{
				if(Model.createBranch(regNo, bankName, postal, country, street, phone, con)){
					request.setAttribute("createBranchStatus", 1);
				}else{
					request.setAttribute("createBranchStatus", -1);
					keepValues(request);
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
	
	private HttpServletRequest keepValues(HttpServletRequest request){
		request.setAttribute("regNo", regNo);
		request.setAttribute("bankName", bankName);
		request.setAttribute("postal", postal);
		request.setAttribute("country", country);
		request.setAttribute("street", street);
		request.setAttribute("phone", phone);
		return request;
	}

}
