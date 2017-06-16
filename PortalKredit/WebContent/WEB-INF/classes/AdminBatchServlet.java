
import java.io.IOException;
import java.sql.Connection;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import classes.Model;

/*
 * Servlet implementation class CreateAdminServlet
 */
@WebServlet("/AdminBatch")
public class AdminBatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (Model.checkAuth(Model.Type.admin, request.getSession())) {
			checkButtons(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Model.adminCheckAuth("AdminBatch.jsp", request, response);
	}

	private void checkButtons(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Connection con = Model.getConnection(ds1);
		// TODO change to include exchangerate
		if (request.getParameter("exchangeRate") != null) {
			Model.updateExchangeRates(con);
			request.setAttribute("status", "exchangeU");
		} else if (request.getParameter("dInterestRate") != null) {
			if (Model.calculateInterestRates(con)) {
				request.setAttribute("status", "dInterestRateS");
			} else {
				request.setAttribute("status", "dInterestRateF");
			}
		} else if (request.getParameter("yInterestRate") != null) {
			if (Model.giveAnualInterest(con)) {
				request.setAttribute("status", "yInterestRateS");
			} else {
				request.setAttribute("status", "yInterestRateU");
			}
		} else if (request.getParameter("backupTransactions") != null) {
			if (Model.backupTransactions(con)) {
				request.setAttribute("status", "backupTransactionsS");
			} else {
				request.setAttribute("status", "backupTransactionsF");
			}
		} else if (request.getParameter("insertExchangeRate") != null) {
			Model.insertExchangeRates(con);
			request.setAttribute("status", "exchangeI");

		}
		Model.cleanUpConnection(con);
		doGet(request, response);
	}

}

