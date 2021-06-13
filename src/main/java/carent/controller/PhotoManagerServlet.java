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
 * Servlet implementation class PhotoManagerServlet
 */
@WebServlet("/admin/PhotoManagerServlet")
public class PhotoManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PhotoManagerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CarModelDS carmodelds = new CarModelDS(ds);
		try {
			request.setAttribute("veicoliPerGestione", carmodelds.doRetrieveAll(null));
		} catch (SQLException e) {
			Utility.print("ERRORE SQL: "+e);
			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/PhotoManager.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
