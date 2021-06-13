package carent.controller;

import carent.model.CartModelDS;
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
 * Servlet implementation class RimuoviDalCarrelloServlet
 */
@WebServlet("/user/RimuoviDalCarrelloServlet")
public class RimuoviDalCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RimuoviDalCarrelloServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codiceItemDaRimuovereString = (String) request.getParameter("codiceItemDaRimuovere");
		Utility.print("codiceItemDaRimuovereString: "+codiceItemDaRimuovereString);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user/MostraAutoDisponibiliServlet");
		
		if (codiceItemDaRimuovereString==null) {
			Utility.print("codiceItemDaRimuovere era null...");
			dispatcher.forward(request, response);
			return;
		}
		
		int codiceItemDaRimuovere = Integer.parseInt(codiceItemDaRimuovereString);
		Utility.print("codiceItemDaRimuovere: "+codiceItemDaRimuovere);
		
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CartModelDS cartmodelds = new CartModelDS(ds);
		try {
			cartmodelds.removeFromCart(codiceItemDaRimuovere);
		} catch (SQLException e) {
			request.setAttribute("error", "Impossibile rimuovere l'elemento dal carrello!! Errore SQL");
			Utility.print(e);
		}
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
