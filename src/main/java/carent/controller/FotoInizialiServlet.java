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

@WebServlet("/fotoiniziali")
public class FotoInizialiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public FotoInizialiServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utility.print("Ciaociaociaociaociao");
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CarModelDS carmodelds = new CarModelDS(ds);
		try {
			request.setAttribute("daVisualizzare",carmodelds.doRetrieveAll(null));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("card.jsp");
			dispatcher.forward(request,response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
