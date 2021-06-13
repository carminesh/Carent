package carent.controller;

import carent.model.CartItemBean;
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
 * Servlet implementation class aggiungiAlCarrelloServlet
 */
@WebServlet("/user/AggiungiAlCarrelloServlet")
public class AggiungiAlCarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AggiungiAlCarrelloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		RequestDispatcher dispatcher = null;
		
		
		
		String userCodeString = request.getParameter("userCode");
		Integer userCode = Integer.parseInt(userCodeString);
		Utility.print("userCode: "+userCode);
		
		String targa = (String) request.getParameter("targa");
		Utility.print("targa: "+targa);
		
		String daData = (String) request.getParameter("daData");
		Utility.print("daData: "+daData);
		
		String aData = (String) request.getParameter("aData");
		Utility.print("aData: "+aData);
		
		if (userCode==null || targa==null || daData==null || aData==null) {
			Utility.print("Qualche parametro era null");
			request.setAttribute("error", "Impossibile aggiungere al carrello, parametri non validi");
			dispatcher = this.getServletContext().getRequestDispatcher("/user/MostraAutoDisponibiliServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		CartItemBean itemToAdd = new CartItemBean();
		itemToAdd.setUserCode(userCode);
		itemToAdd.setTarga(targa);
		itemToAdd.setDaData(daData);
		itemToAdd.setaData(aData);
		
		CartModelDS cartmodelds = new CartModelDS(ds);
		try {
			Utility.print("Ci sto provando...");
			cartmodelds.addToCart(itemToAdd);
			dispatcher = this.getServletContext().getRequestDispatcher("/user/MostraAutoDisponibiliServlet");
			dispatcher.forward(request, response);
			return;
		} catch (SQLException e) {
			request.setAttribute("error", e.getMessage());
			Utility.print(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
