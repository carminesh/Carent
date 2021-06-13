package carent.controller;

import carent.model.CarBean;
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


@WebServlet("/admin/ModificaAutoServlet")
public class ModificaAutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ModificaAutoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		
		Utility.print("Provando ad effettuare la modifica...");
		
		String targa = (String) request.getParameter("targa");
		String marca = (String) request.getParameter("marca");
		String modello = (String) request.getParameter("modello");
		String annoString  = (String) request.getParameter("annoimm");
		String potenzaString = (String) request.getParameter("potenza");
		String chilometraggioString = (String) request.getParameter("chilometraggio");
		String alimentazione = (String) request.getParameter("alimentazione");
		
		if (targa==null || marca==null || modello==null || annoString==null || potenzaString==null || chilometraggioString==null || alimentazione==null) {
			request.setAttribute("error","Inserisci correttamente i dati dell'auto da modificare!");
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/AdminPageServlet");
			dispatcher.forward(request, response);
			return;
		}
		int anno = Integer.parseInt(annoString);
		int potenza = Integer.parseInt(potenzaString);
		int chilometraggio = Integer.parseInt(chilometraggioString);
		
		if (targa.length()!=7 || marca.equals("") || modello.equals("") || alimentazione.equals("") || anno<1960 || potenza<40 || chilometraggio<0) {
			request.setAttribute("error","Inserisci correttamente i dati dell'auto da modificare!");
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/AdminPageServlet");
			dispatcher.forward(request, response);
			return;
		}
		
		CarBean nuovo = new CarBean();
		nuovo.setTarga(targa);
		nuovo.setMarca(marca);
		nuovo.setModello(modello);
		nuovo.setChilometraggio(chilometraggio);
		nuovo.setAnnoImmatricolazione(anno);
		nuovo.setAlimentazione(alimentazione);
		nuovo.setPotenza(potenza);
		
		
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		CarModelDS carmodelds = new CarModelDS(ds);
		try {
			carmodelds.doUpdate(nuovo);
		} catch (SQLException e) {
			request.setAttribute("error","Impossibile modificare i dati dell'auto");
			Utility.print("ERRORE SQL: "+e);
			e.printStackTrace();
		}
		
		dispatcher = this.getServletContext().getRequestDispatcher("/admin/AdminPageServlet");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}