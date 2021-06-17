package carent.controller;

import carent.model.CarModelDS;
import carent.utils.Utility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/search")
public class SearchActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utility.print("Sono entrato nella servlet");
        String dataInizio = (String) request.getParameter("start-date");
        String dataFine = (String) request.getParameter("finish-date");
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        CarModelDS carmodelds = new CarModelDS(ds);
        if (dataInizio==null || dataFine==null || dataInizio.equals("") || dataInizio.equals("")) {
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
