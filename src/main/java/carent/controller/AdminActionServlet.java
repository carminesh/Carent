package carent.controller;

import carent.model.CarModelDS;
import carent.model.UserModelDS;
import carent.model.CarBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

//@WebServlet(name = "AdminActionServlet", value = "/AdminActionServlet")
@WebServlet("/adminaction")
public class AdminActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrato in admin actions servlet");
        String actiontype = (String) request.getParameter("actiontype");
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        switch (actiontype) {
            case "removecar":
                System.out.println("Eliminando veicolo...");
                try {
                    String plateToDelete = (String) request.getParameter("plate");
                    if (plateToDelete==null || plateToDelete.equals("")) {
                        response.getWriter().print("Parametri invalidi");
                    } else {
                        CarModelDS carmodelds = new CarModelDS(ds);
                        if (carmodelds.doDelete(plateToDelete))
                            response.getWriter().print("Veicolo eliminato con successo");
                        else {
                            response.getWriter().print("Impossibile eliminare il veicolo");
                            response.setStatus(400);
                        }

                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eliminare il veicolo...");
                    response.setStatus(400);
                    e.printStackTrace();
                }
                break;
            case "addcar":
                System.out.println("Aggiungendo veicolo...");
                try {
                    String newPlate = (String) request.getParameter("plate");
                    String newBrand = (String) request.getParameter("brand");
                    String newModel = (String) request.getParameter("model");
                    String newPowerString = (String) request.getParameter("power");
                    String newFuel = (String) request.getParameter("fuel");
                    String newYearString = (String) request.getParameter("year");
                    String newMileageString = (String) request.getParameter("mileage");
                    if (newPlate==null || newPlate.equals("") || newBrand==null || newBrand.equals("") || newModel==null || newModel.equals("") || newPowerString==null || newPowerString.equals("") || newFuel==null || newFuel.equals("") || newYearString==null || newYearString.equals("") || newMileageString==null || newMileageString.equals("")) {
                        response.getWriter().print("Parametri invalidi");
                    } else {
                        CarModelDS carAdder = new CarModelDS(ds);
                        CarBean toAdd = new CarBean();
                        toAdd.setTarga(newPlate);
                        toAdd.setMarca(newBrand);
                        toAdd.setModello(newModel);
                        toAdd.setPotenza(Integer.parseInt(newPowerString));
                        toAdd.setAlimentazione(newFuel);
                        toAdd.setAnnoImmatricolazione(Integer.parseInt(newYearString));
                        toAdd.setChilometraggio(Integer.parseInt(newMileageString));
                        if (carAdder.doSave(toAdd)) {
                            response.getWriter().print("Veicolo aggiunto con successo");
                        } else {
                            response.getWriter().print("Impossibile aggiungere il veicolo...");
                            response.setStatus(400);
                        }
                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile aggiungere il veicolo...");
                    response.setStatus(400);
                }

                break;
            case "removeuser":
                System.out.println("Eliminando utente...");
                try {
                    String emailToDelete = (String) request.getParameter("email");
                    UserModelDS usermodelds = new UserModelDS(ds);
                    if (usermodelds.removeUser(emailToDelete)) {
                        response.getWriter().print("Utente eliminato con successo!");
                    } else {
                        response.getWriter().print("Impossibile eliminare l'utente");
                        response.setStatus(400);
                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eliminare l'utente");
                    response.setStatus(400);
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
