package Client;

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

import classes.Controller;

@WebServlet("/client/payments")
public class ClientPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String sendAcc, reciAcc, stramount, strsendReg, strreciReg, currency, strmessage, strreciMessage,
	fullAcc, password;
	
	
	public ClientPaymentsServlet(){
		super();
	}
	
	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		if(Controller.checkAuth(Controller.Type.client, session)){
		
		request.getRequestDispatcher("payments.jsp").forward(request, response);
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
		if(Controller.checkAuth(Controller.Type.client, session)){
			payment(request, response, session);
		}
		else{
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
		

		
		
	}
	
	//Used to keep the written input in the input fields
	private HttpServletRequest keepInputs(HttpServletRequest request){	
		request.setAttribute("amount", stramount);
		request.setAttribute("currency", currency);
		request.setAttribute("message", strmessage);
		request.setAttribute("reciMessage", strreciMessage);
		request.setAttribute("reciReg", strreciReg);
		request.setAttribute("reciAcc", reciAcc);
		return request;
	}
	private void payment(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException{
		fullAcc = request.getParameter("senderAcc");
		String[] fullAccSplit = fullAcc.split("[.]");
		strsendReg = fullAccSplit[0];
		sendAcc = fullAccSplit[1];
		reciAcc = request.getParameter("reciAcc");
		strreciReg = request.getParameter("reciReg");
		stramount = request.getParameter("amount");
		currency = request.getParameter("currency");
		strmessage = request.getParameter("message");
		strreciMessage = request.getParameter("reciMessage");
		password = request.getParameter("password");
		String sendReg = strsendReg;
		String reciReg = strreciReg;
		double amount = Double.parseDouble(stramount);
		
		Connection con = Controller.getConnection(ds1);
		String userID = (String) session.getAttribute("userID");
		Boolean correctPw = Controller.authenticate(userID, password, con, session);

		if(correctPw){
			Boolean status = Controller.transaction(sendAcc, reciAcc, amount, sendReg, reciReg,
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
		
		Controller.cleanUpConnection(con);
		request.getRequestDispatcher("payments.jsp").forward(request, response);
		
	}
}