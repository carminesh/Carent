package carent.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import carent.model.*;
import carent.utils.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = (String) request.getParameter("email");
		String passwd = (String) request.getParameter("passwd");
		String nome = (String) request.getParameter("name");
		String cognome = (String) request.getParameter("surname");
		String phone = (String) request.getParameter("phonenumber");

		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		UserModelDS usermodelds = new UserModelDS(ds);
		RequestDispatcher dispatcher = null;

		request.setAttribute("pass", "enabled");

		try {
			if (!(email==null || email.equals("") || passwd==null || passwd.equals("") || nome==null || nome.equals("") || cognome==null || cognome.equals("")) || phone==null || phone.equals("")) {
				if (!usermodelds.userExists(email)) {
					Utility.print("Posso registrare!");
					usermodelds.registerUser(email,passwd,nome,cognome,phone);
					request.setAttribute("esito","Registrazione effettuata con successo");
				} else {
					request.setAttribute("error", "L'utente è già esistente!");
				}
			} else {
				request.setAttribute("error", "Compila tutti i campi!");
			}
			dispatcher = this.getServletContext().getRequestDispatcher("/loginPage.jsp");
			dispatcher.forward(request, response);
			return;
		} catch (SQLException e) {
			Utility.print("ERRORE SQL: "+e);
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}