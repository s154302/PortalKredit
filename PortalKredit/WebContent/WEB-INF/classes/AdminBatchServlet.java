import java.io.IOException;

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
       
    public AdminBatchServlet() {
        super();

    }
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
	private void checkButtons(HttpServletRequest request, HttpServletResponse response) throws IOException{
		if(!request.getParameter("exchangeRate").equals(null)) {
			Controller.updateExchangeRates(ds1);
			System.out.println("Exchange");
		} else if(!request.getParameter("dInterestRate").equals(null)) {
			Controller.calculateInterestRates(ds1);
			System.out.println("dInterest");
		} else if(!request.getParameter("yInterestRate").equals(null)) {
			Controller.giveAnualInterest(ds1);
			System.out.println("yInterest");
		}
	}
	

}
