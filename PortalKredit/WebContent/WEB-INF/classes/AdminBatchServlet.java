import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Controller;

/*
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/AdminBatch")
public class AdminBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(Controller.checkAuth(Controller.Type.admin, request.getSession())){
			checkButtons(request,response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Controller.adminCheckAuth("AdminBatch.jsp",request,response);
	}
	private void checkButtons(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Connection con = Controller.getConnection(ds1);
		if(request.getParameter("exchangeRate") != null) {
			Controller.updateExchangeRates(con);
			request.setAttribute("status", "Updated exchange rates");
		} else if(request.getParameter("dInterestRate") != null) {
			if(Controller.calculateInterestRates(con)){
				request.setAttribute("status", "Daily interest updated");
			}else{
				request.setAttribute("status", "Updating daily interest failed");
			}
		} else if(request.getParameter("yInterestRate") != null) {
			if(Controller.giveAnualInterest(con)){
				request.setAttribute("status", "Yearly interest updated");
			}else{
				request.setAttribute("status", "Updating yearly interest failed");
			}
		} else if(request.getParameter("backupTrsansactions") != null){
			if(Controller.backupTransactions(con)){
				request.setAttribute("status", "Transactions moved to backup");
			}else{
				request.setAttribute("status", "Backuping transactions failed");
			}		
		} else if(request.getParameter("insertExchangeRate") != null) {
			Controller.insertExchangeRates(con);
			request.setAttribute("status", "New Exchange rates inserted");

		}
		Controller.cleanUpConnection(con);
		doGet(request, response);
	}
	

}
