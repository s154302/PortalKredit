package banker;

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

import classes.Banker;
import classes.Controller;

@WebServlet("/banker/Deposit")
public class BankerDepositServlet extends HttpServlet {
	//Copied from clientPayment
	private static final long serialVersionUID = 1L;
	String accountNumber, amount, currency, userId, password;
		
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		if(Controller.checkAuth(Controller.Type.banker, session)){
		
		request.getRequestDispatcher("Deposit.jsp").forward(request, response);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		Connection con = Controller.getConnection(ds1);
		if(Controller.checkAuth(Controller.Type.banker, session)){
			Banker banker = (Banker)session.getAttribute("user");
			accountNumber = request.getParameter("reciAcc");
			amount = request.getParameter("amount");
			currency = request.getParameter("currency");
			userId = (String) session.getAttribute("userID");
			password = request.getParameter("password");
			
			Boolean correctPw = Controller.authenticate(userId, password, con, session);
			
			if(correctPw){
				if(Controller.deposit(accountNumber, banker.getRegNo(), con, Double.parseDouble(amount), currency)){
					request.setAttribute("status", "Sussces");
				}else{
					request.setAttribute("status", "Error somthing went wrong");
					request = keepInputs(request);
				}
			}else{
				request.setAttribute("status", "Wrong password");
				request = keepInputs(request);
			}
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		Controller.cleanUpConnection(con);
		request.getRequestDispatcher("Deposit.jsp").forward(request, response);
		
	}
	
	private HttpServletRequest keepInputs(HttpServletRequest request){	
		request.setAttribute("amount", amount);
		request.setAttribute("currency", currency);
		request.setAttribute("reciAcc", accountNumber);
		return request;
	}
}