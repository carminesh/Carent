package carent.controller;

import carent.model.CartBean;
import carent.model.UserBean;
import carent.model.UserModelDS;
import carent.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServletDBAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		UserModelDS usermodelds = new UserModelDS(ds);
		
		try {
			if (usermodelds.userExists(email)) {
				if (!usermodelds.wrongPassword(email, passwd)) {
					UserBean userbean = usermodelds.fetchUser(email);
					if(userbean.getRole().equals("userrole")) {
						HttpSession session = request.getSession(true);
						session.setAttribute("utente", userbean);
						session.setAttribute("cart",new CartBean());
						session.setMaxInactiveInterval(2*60*60);
						System.out.print("Login come UTENTE");
						getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
						return;
					} else {
						HttpSession session = request.getSession(true);
						session.setAttribute("utente", userbean);
						session.setMaxInactiveInterval(2*60*60);
						System.out.print("Login come ADMIN");
						getServletContext().getRequestDispatcher("/adminPage.jsp").forward(request, response);
						return;
					}
				} else {
					request.setAttribute("error", "Password errata, riprovare");
					HttpSession session = request.getSession(true);
					session.setAttribute("utente",null);
				}
			} else {
				request.setAttribute("error", "L'utente non esiste!");
				HttpSession session = request.getSession(true);
				session.setAttribute("utente",null);
			}
			
			getServletContext().getRequestDispatcher("/loginPage.jsp").forward(request, response);
			return;
		} catch (SQLException e) {
			Utility.print(e);
			request.setAttribute("error",e.getMessage());
		}
	}

}
