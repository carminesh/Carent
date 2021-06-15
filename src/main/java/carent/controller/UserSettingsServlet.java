package carent.controller;

import carent.model.RentBean;
import carent.model.RentModelDS;
import carent.model.UserBean;
import carent.model.UserModelDS;
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

		}


		
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
