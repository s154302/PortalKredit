package Client;

import java.io.IOException;
import java.sql.Clob;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Account;
import classes.Client;
import classes.Controller;

@WebServlet("/client/payments")
public class ClientPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		
		String sendAcc, reciAcc, stramount, strsendReg, strreciReg, currency, strmessage, strreciMessage, fullAcc;
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
		int sendReg = Integer.parseInt(strsendReg);
		int reciReg = Integer.parseInt(strreciReg);
		double amount = Double.parseDouble(stramount);
		
		Boolean status = Controller.transaction(sendAcc, reciAcc, amount, sendReg, reciReg,
				currency, strmessage, strreciMessage, ds1);
		
		if(status){
			request.setAttribute("status", "Payment complete");
		}else{
			request.setAttribute("status", "Payment incomplete - somthing went wrong");
			request.setAttribute("amount", stramount);
			request.setAttribute("currency", currency);
			request.setAttribute("message", strmessage);
			request.setAttribute("reciMessage", strreciMessage);
			request.setAttribute("reciReg", strreciReg);
			request.setAttribute("reciAcc", reciAcc);
			
		}
		
		request.getRequestDispatcher("payments.jsp").forward(request, response);
		
	}
}