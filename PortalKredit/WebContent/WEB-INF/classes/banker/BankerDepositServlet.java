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
import classes.Model;

@WebServlet("/banker/Deposit")
public class BankerDepositServlet extends HttpServlet {
	//Copied from clientPayment
	private static final long serialVersionUID = 1L;
	String accountNumber, strAmount, currency, userId, password, reciAccCurrency, regNo;
		
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		if(Model.checkAuth(Model.Type.banker, session)){
		if(request.getAttribute("status") == null) {
			request.setAttribute("status", 0);
		}
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
		Connection con = Model.getConnection(ds1);
		if(Model.checkAuth(Model.Type.banker, session)){
			Banker banker = (Banker)session.getAttribute("user");
			accountNumber = request.getParameter("reciAcc");
			regNo = banker.getRegNo();
			strAmount = request.getParameter("amount");
			currency = request.getParameter("currency");
			userId = (String) session.getAttribute("userID");
			password = request.getParameter("password");
			Boolean correctPw = Model.authenticate(userId, password, con, session);
			
			
			
			if(correctPw){
				if(Model.deposit(accountNumber, regNo, con, Double.parseDouble(strAmount), currency)){
					request.setAttribute("status", 1);
				}else{
					request.setAttribute("status", -1);
					request = keepInputs(request);
				}
			}else{
				request.setAttribute("status", -2);
				request = keepInputs(request);
			}
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		
		Model.cleanUpConnection(con);
		request.getRequestDispatcher("Deposit.jsp").forward(request, response);
		
	}
	
	private HttpServletRequest keepInputs(HttpServletRequest request){	
		request.setAttribute("amount", strAmount);
		request.setAttribute("currency", currency);
		request.setAttribute("reciAcc", accountNumber);
		return request;
	}
}