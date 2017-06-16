package banker;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Banker;
import classes.Client;
import classes.Model;

@WebServlet("/banker/ViewClients")
public class BankerViewClientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BankerViewClientServlet() {
		super();
	}

	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);

		if (Model.checkAuth(Model.Type.banker, session)) {
			Banker banker = (Banker) session.getAttribute("user");

			ArrayList<Client> clientList = banker.getClients();
			session.setAttribute("list", clientList);
			session.setAttribute("clientID", null);

			request.getRequestDispatcher("ViewClients.jsp").forward(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);

		if (Model.checkAuth(Model.Type.banker, session)) {
			viewClient(request, response, session);

		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}

	}

	private void viewClient(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		String search = request.getParameter("search-client");
		Connection con = Model.getConnection(ds1);
		
		if (request.getParameter("ViewUsername") != null) {
			String clientID = request.getParameter("ViewUsername");
			session.setAttribute("clientID", clientID);
			response.sendRedirect(request.getContextPath() + "/banker/ViewSingleClient");

		} else if (request.getParameter("EditUsername") != null) {
			String clientID = request.getParameter("EditUsername");
			session.setAttribute("clientID", clientID);
			session.setAttribute("client", (Client) Model.getClientInfo(clientID, con));

			Model.cleanUpConnection(con);
			response.sendRedirect(request.getContextPath() + "/banker/EditClient");

		} else if (search != null) {
			ArrayList<Client> clientList = Model.searchClient(request.getParameter("search-term"), con);
			request.setAttribute("list", clientList);
			Model.cleanUpConnection(con);
			doGet(request, response);
		}

	}

}
