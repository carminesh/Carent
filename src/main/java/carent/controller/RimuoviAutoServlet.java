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
 * Servlet implementation class RimuoviAutoServlet
 */
@WebServlet("/admin/RimuoviAutoServlet")
public class RimuoviAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RimuoviAutoServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utility.print("Provando a cancellare il veicolo");
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		String targa = (String) request.getParameter("targa");
		RequestDispatcher dispatcher = null;
		if (targa==null || targa.equals("")) {
			Utility.print("La targa non va bene");
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/AdminPageServlet");
			dispatcher.forward(request, response);
			return;
		}
		CarModelDS carmodelds = new CarModelDS(ds);
		try {
			carmodelds.doDelete(targa);
		} catch (SQLException e) {
			Utility.print("ERRORE SQL: "+e);
			e.printStackTrace();
		}
		Utility.print("Less gooo");
		dispatcher = this.getServletContext().getRequestDispatcher("/admin/AdminPageServlet");
		dispatcher.forward(request, response);
		return;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
