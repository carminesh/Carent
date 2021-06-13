package carent.controller;

import carent.model.CarModelDS;
import carent.model.CartModelDS;
import carent.model.UserBean;
import carent.utils.Utility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class MostraAutoDisponibiliServlet
 */
@WebServlet("/user/MostraAutoDisponibiliServlet")
public class MostraAutoDisponibiliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MostraAutoDisponibiliServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CarModelDS carmodelds = new CarModelDS(ds);
		CartModelDS cartmodelds = new CartModelDS(ds);
		
		
		/*
			Vedo prima nella request
			Se nella request sono attivi, li sovrascrivo a quelli di sessione
				Chiamo quindi doRetrieveAvailableInPeriod
			
			Se nella request non sono attivi ma sono attivi quelli in sessione
				Chiamo quindi doRetrieveAvailableInPeriod
				
			Se nella request non sono attivi e neanche in sessione
				Chiamo quindi doRetrieveAll
	
		 */
		
		
		String daDataRequest = (String) request.getParameter("daData");
		String aDataRequest = (String) request.getParameter("aData");
		HttpSession session = request.getSession(false);
		String daDataSession = (String) session.getAttribute("daData");
		String aDataSession = (String) session.getAttribute("aData");
		
		UserBean utente = (UserBean) session.getAttribute("utente");
		int userCode = utente.getUserCode();
		
		try {
			if (daDataRequest!=null && aDataRequest!=null) {
				
				//Va fatto un controllo sulla correttezza del periodo
				
				//Poi ci penso
				
				
				session.setAttribute("daData", daDataRequest);
				session.setAttribute("aData", aDataRequest);
				request.setAttribute("availableCarList", carmodelds.doRetrieveAvailableInPeriod(daDataRequest, aDataRequest));
				request.setAttribute("cart", cartmodelds.doRetrieveCartForUser(userCode));
				request.setAttribute("carrelloDisponibile", "true");
			} else {
				if (daDataSession!=null && aDataSession!=null) {
					request.setAttribute("availableCarList", carmodelds.doRetrieveAvailableInPeriod(daDataSession,aDataSession));
					request.setAttribute("cart", cartmodelds.doRetrieveCartForUser(userCode));
					request.setAttribute("carrelloDisponibile", "true");
				} else {
					request.setAttribute("availableCarList", carmodelds.doRetrieveAll(null));
					request.setAttribute("carrelloDisponibile", "false");
					request.setAttribute("cart", cartmodelds.doRetrieveCartForUser(userCode));
				}
			}
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			Utility.print(e);
		}
		
		Utility.print("daDataRequest: "+daDataRequest);
		Utility.print("aaDataRequest: "+aDataRequest);
		Utility.print("daDataSession: "+session.getAttribute("daData"));
		Utility.print("aaDataRequest: "+session.getAttribute("aData"));
		
		Utility.print("Ridirezionando verso AutoDisponibili.jsp");
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/AutoDisponibili.jsp");
		dispatcher.forward(request, response);
		return;
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
