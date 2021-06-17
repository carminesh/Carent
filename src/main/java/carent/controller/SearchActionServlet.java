package carent.controller;

import carent.model.CarModelDS;
import carent.utils.Utility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/search")
public class SearchActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utility.print("Sono entrato nella servlet");
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        CarModelDS carmodelds = new CarModelDS(ds);
        String actiontype = (String) request.getParameter("actiontype");
        if (actiontype==null) actiontype="classicSearch";
        String dataInizio,dataFine,pickUpPlace;
        switch (actiontype) {
            case "searchCars":
                pickUpPlace = (String) request.getParameter("pickupplace");
                dataInizio = (String) request.getParameter("startdate");
                dataFine = (String) request.getParameter("finishdate");
                Utility.print("CASO searchCars");
                Utility.print("data Inizio: "+dataInizio);
                Utility.print("data Fine: "+dataFine);
                Utility.print("luogo: "+pickUpPlace);
                try {
                    if (!(pickUpPlace==null || pickUpPlace.equals("Localita") || pickUpPlace.equals("") || dataInizio==null || dataInizio.equals("") || dataFine==null || dataFine.equals(""))) {
                        Date start = Date.valueOf(dataInizio);
                        Date finish = Date.valueOf(dataFine);
                        if (finish.after(start)) {
                            Utility.print("Tutto apposto");
                            request.setAttribute("carListRefreshed",carmodelds.doRetrieveAvailableInPeriod(dataInizio,dataFine));
                            request.setAttribute("start-date-refreshed",dataInizio);
                            request.setAttribute("finish-date-refreshed",dataFine);
                            request.setAttribute("pick-up-place",pickUpPlace);
                            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/searchPageComponent.jsp");
                            dispatcher.forward(request,response);
                            return;
                        } else {
                            response.getWriter().print("Periodo non valido!!");
                            response.setStatus(400);
                        }
                    } else {
                        response.getWriter().print("Compila tutti i parametri");
                        response.setStatus(400);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.getWriter().print("Impossibile completare l'operazione");
                    response.setStatus(400);
                }

                break;
            case "classicSearch":
                Utility.print("Caricamento classico");
                pickUpPlace = (String) request.getParameter("pick-up-place");
                dataInizio = (String) request.getParameter("start-date");
                dataFine = (String) request.getParameter("finish-date");
                Utility.print("data Inizio: "+dataInizio);
                Utility.print("data Fine: "+dataFine);
                Utility.print("luogo: "+pickUpPlace);

                if (pickUpPlace==null || dataInizio==null || dataFine==null || pickUpPlace.equals("") || pickUpPlace.equals("Localita") || dataInizio.equals("") || dataInizio.equals("")) {
                    Utility.print("Preleva tutto");
                    try {
                        request.setAttribute("carList",carmodelds.doRetrieveAll(null));
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/searchPage.jsp");
                        dispatcher.forward(request,response);
                        return;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.setStatus(500);
                    }
                } else {
                    Utility.print("Preleva da "+dataInizio+" a "+dataFine);
                    try {
                        request.setAttribute("carList",carmodelds.doRetrieveAvailableInPeriod(dataInizio,dataFine));
                        request.setAttribute("pick-up-place",pickUpPlace);
                        request.setAttribute("start-date",dataInizio);
                        request.setAttribute("finish-date",dataFine);
                        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/searchPage.jsp");
                        dispatcher.forward(request,response);
                        return;
                    } catch (SQLException e) {
                        e.printStackTrace();
                        response.setStatus(400);
                    }
                }
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
