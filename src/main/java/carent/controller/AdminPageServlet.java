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
@WebServlet("/admin/AdminPageServlet")
public class AdminPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CarModelDS carModel = new CarModelDS(ds);
		try {
			
			request.setAttribute("veicoli", carModel.doRetrieveAll(request.getParameter("ordinamento")));
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error",e.getMessage());
			//In questo modo nella JSP, se esiste questa variabile scriveremo l'errore nella JSP
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AdminPage.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
