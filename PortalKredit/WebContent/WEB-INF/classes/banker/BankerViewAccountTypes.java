package banker;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.AccountType;
import classes.Model;

@WebServlet("/banker/BankerViewAccountTypes")
public class BankerViewAccountTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Resource(name = "jdbc/exampleDS")
	private DataSource ds1;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Model.checkAuth(Model.Type.banker, request.getSession())){
			HttpSession session = request.getSession();
			Connection con = Model.getConnection(ds1);
			ArrayList<AccountType> accountTypes = Model.getAccountTypes(con);
			if(request.getAttribute("deleteAccountTypeStatus") == null) {
				request.setAttribute("deleteAccountTypeStatus", 0);
			}
			session.setAttribute("accountTypes", accountTypes);
			Model.cleanUpConnection(con);
			request.getRequestDispatcher("BankerViewAccountTypes.jsp").forward(request, response);
		}else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Model.checkAuth(Model.Type.banker, request.getSession())){
			Connection con = Model.getConnection(ds1);
			String accountType = request.getParameter("accountType");
			if(Model.deleteAccountType(accountType, con)){
				request.setAttribute("deleteAccountTypeStatus", 1);
			}else{
				request.setAttribute("deleteAccountTypeStatus", -1);
			}
			request.getRequestDispatcher("BankerViewAccountTypes.jsp").forward(request, response);
		}else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
}
