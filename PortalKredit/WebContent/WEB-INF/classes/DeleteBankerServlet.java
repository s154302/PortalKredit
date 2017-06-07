import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Admin;
import classes.Banker;
import classes.Controller;

@WebServlet("/AdminDeleteBanker")
public class DeleteBankerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public DeleteBankerServlet(){
		super();
	}
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Banker> bankerList = Controller.getBankerList(ds1);
		request.setAttribute("list", bankerList);
		Controller.adminCheckAuth("AdminDeleteBanker.jsp",request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Controller.checkAuth(Controller.Type.admin, request.getSession())){
			deleteBanker(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	private void deleteBanker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String bankerID = request.getParameter("username");
		Controller.deleteBanker(bankerID, ds1);
		
		doGet(request, response);
	}

}
