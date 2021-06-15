package carent.controller;

import carent.model.UserBean;
import carent.utils.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/user/page")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPageServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utility.print("sono entrato nella servlet userpage");
		request.setAttribute("fromServlet", "example");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
		//Filtro utente
		
		HttpSession session = request.getSession(false);
		if (session==null) {
			Utility.print("ridirezionando verso index.jsp 1");
			dispatcher.forward(request, response);
			return;
		} else {
			UserBean bean = (UserBean) session.getAttribute("utente");
			Utility.print(bean.toString());
			
			if (bean==null || !bean.getRole().equals("userrole")) {
				Utility.print("ridirezionando verso index.jsp 3");
				Utility.print(bean.getRole());
				dispatcher.forward(request, response);
				return;
			}
		}
		
		Utility.print("ridirezionando verso userpage.jsp");
		dispatcher = this.getServletContext().getRequestDispatcher("/userPage.jsp");
		dispatcher.forward(request, response);
		return;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
