package carent.controller;

import carent.model.UserModelDS;
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
 * Servlet implementation class ModificaPassword
 */
@WebServlet("/user/ModificaPasswordUtente")
public class ModificaPasswordUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModificaPasswordUtente() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String vecchiapass = (String) request.getParameter("vecchiapass");
		String nuovapass = (String) request.getParameter("nuovapass");
		
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		UserModelDS usermodelds = new UserModelDS(ds);
		try {
			if (!usermodelds.wrongPassword(email, vecchiapass)) {
				usermodelds.changePassword(email, nuovapass);
				request.setAttribute("esito", "Password modificata con successo!!");
			} else {
				request.setAttribute("error", "Password errata, impossibile modificare la password");
			}
		} catch (SQLException e) {
			Utility.print("ERRORE SQL: "+e);
			request.setAttribute("error", e.getMessage());
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/UserPage.jsp");
		dispatcher.forward(request, response);
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
