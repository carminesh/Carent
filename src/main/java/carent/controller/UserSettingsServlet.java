package carent.controller;

import carent.model.*;
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
import java.util.Collection;

/**
 * Servlet implementation class UserSettingsServlet
 */
@WebServlet("/user/settings")
public class UserSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserSettingsServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String changetype = (String) request.getParameter("changetype");
		Utility.print("changetype= "+changetype);
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
		UserModelDS usermodelds = new UserModelDS(ds);
		switch (changetype) {
		case "email":
			String newemail = (String) request.getParameter("newemail");
			String oldemail = (String) request.getParameter("oldemail");
			Utility.print("Vecchia email: "+oldemail+", nuova email: "+newemail);
			
			
			try {
				if (usermodelds.changeEmail(oldemail, newemail)) {
					response.getWriter().print(newemail);
					UserBean bean = new UserBean();
					HttpSession session = request.getSession(false);
					bean = (UserBean) session.getAttribute("utente");
					bean.setEmail(newemail);
					session.setAttribute("utente", bean);
				} else {
					response.getWriter().print("Email non modificata");
					response.setStatus(400);
				}
			} catch (SQLException e) {
				response.getWriter().print("Impossibile eseguire l'operazione");
				response.setStatus(400);
				e.printStackTrace();
			}
			
		break;
		
		
		case "pass":
				String currentemail = (String) request.getParameter("currentemail");
				String oldpassword = (String) request.getParameter("oldpass");
				String newpassword = (String) request.getParameter("newpass");
				Utility.print(currentemail+" "+oldpassword+" "+newpassword);
				try {

					if(oldpassword.equals(newpassword)) {
						response.getWriter().print("La password deve essere diversa");
						response.setStatus(400);
					} else if (!usermodelds.wrongPassword(currentemail, oldpassword)) {
						if (usermodelds.changePassword(currentemail, newpassword)) {
							response.getWriter().print("Password modificata con successo");
							UserBean bean = new UserBean();
							HttpSession session = request.getSession(false);
							bean = (UserBean) session.getAttribute("utente");
							bean.setPasswd(newpassword);
							session.setAttribute("utente", bean);
						} else {
							response.getWriter().print("Impossibile modificare la password");
							response.setStatus(400);
						}
					} else {
							response.getWriter().print("Password errata");
							response.setStatus(400);
					}

				} catch (SQLException e) {
					response.getWriter().print("Impossibile eseguire l'operazione");
					response.setStatus(400);
					e.printStackTrace();
				}
		break;
			case "rentload":
				RentModelDS rentmodelds = new RentModelDS(ds);
				Utility.print("Ho creato rentmodelds");
				String emailForRents = request.getParameter("email");
				Utility.print("Ho preso l'attributo email: "+emailForRents);
				try {
					Collection<RentBean> temp = rentmodelds.fetchRentsFromUser(emailForRents,3);
					request.setAttribute("earlyRents",temp);
					System.out.println(temp);
					Utility.print("Ho settato l'attributo earlyRents");
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/earlyRentsComponent.jsp");
					Utility.print("Sto per fare il dispatching");
					dispatcher.forward(request,response);
					return;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "addToCart":
				Utility.print("Provando a prelevare la sessione");
				HttpSession session = request.getSession(false);
				Utility.print("Sessione prelevata");
				if (session!=null) {
					Utility.print("La sessione non è null");
					CartBean cartbean = (CartBean) session.getAttribute("cart");
					if (cartbean!=null) {
						String plate = (String) request.getParameter("plate");
						String startdate = (String) request.getParameter("startdate");
						String finishdate = (String) request.getParameter("finishdate");
						String place = (String) request.getParameter("place");
						Utility.print("Sono in user settings");
						Utility.print("plate: "+plate);
						Utility.print("startdate: "+startdate);
						Utility.print("finishdate: "+finishdate);
						Utility.print("place: "+place);

						if (!(plate==null || plate.equals("") || startdate==null || startdate.equals("") || finishdate==null || finishdate
								.equals("") || place==null || place.equals("") || place.equals("Localita"))) {
							Utility.print("ok continuiamo");
							CartItemBean newItemInCart = new CartItemBean();
							CarBean tempCarforValues = new CarBean();
							CarModelDS carSearcher = new CarModelDS(ds);
							try {
								tempCarforValues = carSearcher.doRetrieveByKey(plate);
								newItemInCart.setAuto(tempCarforValues);
								newItemInCart.setDaData(startdate);
								newItemInCart.setaData(finishdate);
								newItemInCart.setPrezzoTotale(newItemInCart.calcolaPeriodo()*newItemInCart.getAuto().getPrezzo_gg());


								Utility.print("Provando ad inserire questo nuovo item nel carrello");
								Utility.print(newItemInCart.getAuto().toString());
								Utility.print(newItemInCart.getDaData());
								Utility.print(newItemInCart.getaData());
								System.out.println(newItemInCart.getPrezzoTotale());


								//In questo momento il cartitem è stato creato con successo

								if (!cartbean.isInCart(newItemInCart.getAuto().getTarga())) {
									Utility.print("L'elemento non è nel carrello");
									if (cartbean.add(newItemInCart)) {
										Utility.print("Elemento aggiunto nel carrello!!");
										/*
										RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/cartComponent.js");
										dispatcher.forward(request,response);
										return;
										*/
									} else {
										Utility.print("Impossibile aggiungere al carrello");
										response.getWriter().print("Impossibile aggiungere al carrello");
										response.setStatus(400);
									}
								} else {
									Utility.print("L'elemento è nel carrello");
									response.getWriter().print("Non puoi noleggiare due volte la stessa auto, riprova in un nuovo acquisto");
									response.setStatus(400);
								}

							} catch (SQLException e) {
								e.printStackTrace();
								response.getWriter().print("Impossibile completare l'operazione");
								response.setStatus(400);
							}
						} else {
							response.getWriter().print("Fornisci tutti i parametri");
							response.setStatus(400);
						}
					} else {
						response.getWriter().print("Apparentemente non esiste il carrello");
						response.setStatus(400);
					}
				} else {
					response.getWriter().print("Non sei loggato...");
					response.setStatus(400);
				}

				break;

		}


		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
