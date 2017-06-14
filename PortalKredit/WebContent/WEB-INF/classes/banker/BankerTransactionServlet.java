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

@WebServlet("/banker/Transaction")
public class BankerTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sendAcc, reciAcc, stramount, strsendReg, strreciReg, currency, strmessage, strreciMessage,
	password;
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Model.checkAuth(Model.Type.banker, session)){
		
		request.getRequestDispatcher("Transaction.jsp").forward(request, response);
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
		if(Model.checkAuth(Model.Type.banker, session)){
			payment(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		

		
		
	}
	
	//Used to keep the written input in the input fields
	private HttpServletRequest keepInputs(HttpServletRequest request){
		request.setAttribute("fromAccount", sendAcc);
		request.setAttribute("amount", stramount);
		request.setAttribute("currency", currency);
		request.setAttribute("message", strmessage);
		request.setAttribute("reciMessage", strreciMessage);
		request.setAttribute("reciReg", strreciReg);
		request.setAttribute("reciAcc", reciAcc);
		return request;
	}
	private void payment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		Banker banker = (Banker) session.getAttribute("user");
		sendAcc= request.getParameter("fromAccount");
		strsendReg = banker.getRegNo();
		reciAcc = request.getParameter("reciAcc");
		strreciReg = request.getParameter("reciReg");
		stramount = request.getParameter("amount");
		currency = request.getParameter("currency");
		strmessage = request.getParameter("message");
		strreciMessage = request.getParameter("reciMessage");
		password = request.getParameter("password");
		double amount = Double.parseDouble(stramount);
		
		Connection con = Model.getConnection(ds1);
		String userID = (String) session.getAttribute("userID");
		Boolean correctPw = Model.authenticate(userID, password, con, session);

		if(correctPw){
			Boolean status = Model.transaction(sendAcc, reciAcc, amount, strsendReg, strreciReg,
					currency, strmessage, strreciMessage, con);
			
			if(status){
				request.setAttribute("status", "Payment complete");
			}else{
				request.setAttribute("status", "Payment incomplete - somthing went wrong");
				request = keepInputs(request); 
				
			}
		}else{
			request.setAttribute("status", "Payment incomplete - Wrong password");
			request = keepInputs(request); 
		}
		
		Model.cleanUpConnection(con);
		request.getRequestDispatcher("Transaction.jsp").forward(request, response);
		
	}
}