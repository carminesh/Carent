package carent.controller;

import carent.model.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MainPageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(false);
		if (session==null) {
			dispatcher = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			dispatcher.forward(request, response);
			return;
		}
			
		UserBean utente = (UserBean) session.getAttribute("utente");
		if (utente==null) {
			dispatcher = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
		} else {
			dispatcher = this.getServletContext().getRequestDispatcher("/welcomePage.jsp");
		}
		dispatcher.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
