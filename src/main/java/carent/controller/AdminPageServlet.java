package carent.controller;

import carent.model.CarModelDS;
import carent.utils.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AdminPageServlet
 */
@WebServlet("/admin/page")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminPageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utility.print("Sono entrato nella servlet AdminPage");
		request.setAttribute("pass", "enabled");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/adminPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
