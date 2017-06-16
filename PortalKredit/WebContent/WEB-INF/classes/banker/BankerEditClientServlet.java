package banker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import classes.Model;

@WebServlet("/banker/EditClient")
public class BankerEditClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BankerEditClientServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);

		if (Model.checkAuth(Model.Type.banker, session)) {
			request.getRequestDispatcher("EditClient.jsp").forward(request, response);
		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}
	}

	@Resource(name = "jdbc/exampleDS")
	private DataSource ds1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);

		if (Model.checkAuth(Model.Type.banker, session)) {
			editClient(request, response, session);

		} else {
			request.getSession().invalidate();
			response.sendRedirect("../index");
		}

	}

	private void editClient(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
		String clientID = (String) session.getAttribute("clientID");
		String firstName = request.getParameter("clientFirstName");
		String lastName = request.getParameter("clientLastName");
		String password = request.getParameter("clientPassword");
		String email = request.getParameter("clientEmail");
		String mobile = request.getParameter("clientTelephone");
		String street = request.getParameter("street");
		String bankerID = request.getParameter("clientBankerID");
		String postal = request.getParameter("clientCity");
		String country = request.getParameter("clientCountry");

		Connection con = Model.getConnection(ds1);
		try {
			if (!request.getParameter("clientPassword").isEmpty()) {
				Model.changeClientPassword(clientID, password, con);
			}

			Model.editClient(clientID, firstName, lastName, email, mobile, street, bankerID, postal, country, con);

			session.setAttribute("clientID", clientID);
			session.setAttribute("user", Model.getBankerInfo((String) session.getAttribute("userID"), con));
			request.setAttribute("editClientStatus", 1);
		} catch (Exception e) {

			request.setAttribute("editClientStatus", -1);
			e.printStackTrace();
		} finally {
			doGet(request, response);
			Model.cleanUpConnection(con);
		}
	}
}