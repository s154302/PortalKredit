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

import classes.Banker;
import classes.Model;

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
		
		Connection con = Model.getConnection(ds1);
		ArrayList<Banker> bankerList = Model.getBankerList(con);
		Model.cleanUpConnection(con);
		request.setAttribute("list", bankerList);
		
		Model.adminCheckAuth("AdminDeleteBanker.jsp",request,response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		if(Model.checkAuth(Model.Type.admin, request.getSession())){
			deleteBanker(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	private void deleteBanker(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String bankerID = request.getParameter("username");
		Connection con = Model.getConnection(ds1);
		if(Model.deleteBanker(bankerID, con)){
			request.setAttribute("status", bankerID + " was deleted");
		}else{
			request.setAttribute("status", bankerID + " couldn't be deleted.");
		}
		Model.cleanUpConnection(con);
		doGet(request, response);
	}

}
