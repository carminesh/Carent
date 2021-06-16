package carent.controller;

import carent.model.*;
import carent.utils.Utility;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

//@WebServlet(name = "AdminActionServlet", value = "/AdminActionServlet")
@WebServlet("/admin/action")
public class AdminActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entrato in admin actions servlet");
        String actiontype = (String) request.getParameter("actiontype");
        DataSource ds = (DataSource) this.getServletContext().getAttribute("DataSource");
        RequestDispatcher dispatcher;
                switch (actiontype) {
            case "removecar":
                Utility.print("Provando ad eliminare il veicolo...");
                try {
                    String plateToDelete = (String) request.getParameter("plate");
                    if (plateToDelete==null || plateToDelete.equals("")) {
                        response.getWriter().print("Parametri invalidi");
                        response.setStatus(400);
                    } else {
                        CarModelDS carmodelds = new CarModelDS(ds);

                        //Controllare se tale targa esiste
                        if (carmodelds.plateExists(plateToDelete)) {

                            //Controllare se l'auto è coinvolta in noleggi

                            if (!carmodelds.hasRents(plateToDelete)) {
                                if (carmodelds.doDelete(plateToDelete)) {
                                    Utility.print("Veicolo eliminato con successo");
                                    response.getWriter().print("Veicolo eliminato con successo");
                                }
                                else {
                                    Utility.print("Impossibile eliminare il veicolo");
                                    response.getWriter().print("Impossibile eliminare il veicolo");
                                    response.setStatus(400);
                                }
                            } else {
                                Utility.print("Coinvolta in noleggi");
                                response.getWriter().print("Coinvolta in noleggi");
                                response.setStatus(400);
                            }
                        } else {
                            Utility.print("Targa non esistente");
                            response.getWriter().print("Targa non esistente");
                            response.setStatus(400);
                        }
                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eseguire l'operazione");
                    response.setStatus(400);
                    e.printStackTrace();
                }
                break;
            case "addcar":
                System.out.println("Provando ad aggiungere il veicolo...");
                try {
                    String newPlate = (String) request.getParameter("plate");
                    String newBrand = (String) request.getParameter("brand");
                    String newModel = (String) request.getParameter("model");
                    String newPowerString = (String) request.getParameter("power");
                    String newFuel = (String) request.getParameter("fuel");
                    String newYearString = (String) request.getParameter("year");
                    String newMileageString = (String) request.getParameter("mileage");
                    String newPriceString = (String) request.getParameter("price");
                    if (newPlate==null || newPlate.equals("") || newBrand==null || newBrand.equals("") || newModel==null || newModel.equals("") || newPowerString==null || newPowerString.equals("") || newFuel==null || newFuel.equals("") || newYearString==null || newYearString.equals("") || newMileageString==null || newMileageString.equals("") || newPriceString==null || newPriceString.equals("")) {
                        response.getWriter().print("Parametri invalidi");
                        response.setStatus(400);
                    } else {
                        CarModelDS carAdder = new CarModelDS(ds);

                        //Controllare se tale targa già esiste
                        if (!carAdder.plateExists(newPlate)) {
                            CarBean toAdd = new CarBean();
                            toAdd.setTarga(newPlate);
                            toAdd.setMarca(newBrand);
                            toAdd.setModello(newModel);
                            toAdd.setPotenza(Integer.parseInt(newPowerString));
                            toAdd.setAlimentazione(newFuel);
                            toAdd.setAnnoImmatricolazione(Integer.parseInt(newYearString));
                            toAdd.setChilometraggio(Integer.parseInt(newMileageString));
                            toAdd.setPrezzo_gg(Integer.parseInt(newPriceString));
                            if (carAdder.doSave(toAdd)) {
                                Utility.print("Veicolo aggiunto con successo");
                                response.getWriter().print("Veicolo aggiunto con successo");
                            } else {
                                Utility.print("Impossibile aggiungere il veicolo");
                                response.getWriter().print("Impossibile aggiungere il veicolo...");
                                response.setStatus(400);
                            }
                        } else {
                            Utility.print("Targa già esistente...");
                            response.getWriter().print("Targa gi&aacute; esistente");
                            response.setStatus(400);
                        }
                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eseguire l'operazione");
                    response.setStatus(400);
                }

                break;
            case "removeuser":
                System.out.println("Provando ad eliminare l'utente...");
                try {
                    String emailToDelete = (String) request.getParameter("email");
                    UserModelDS usermodelds = new UserModelDS(ds);

                    //Controllare se l'utente esiste
                    if (usermodelds.userExists(emailToDelete)) {

                        //Controllare se l'utente è coinvolto in noleggi

                        if (!usermodelds.hasRents(emailToDelete)) {

                            //Controllare se l'utente è admin

                            if (!usermodelds.isAdmin(emailToDelete)) {
                                if (usermodelds.removeUser(emailToDelete)) {
                                    Utility.print("Utente "+emailToDelete+" eliminato con successo!!");
                                    response.getWriter().print("Utente eliminato con successo!");
                                } else {
                                    Utility.print("Impossibile eliminare "+emailToDelete);
                                    response.getWriter().print("Impossibile eliminare l'utente");
                                    response.setStatus(400);
                                }
                            } else {
                                Utility.print("L'utente è admin!");
                                response.getWriter().print("Impossibile rimuovere un admin");
                                response.setStatus(400);
                            }
                        } else {
                            Utility.print("L'utente "+emailToDelete+" è coinvolto in noleggi");
                            response.getWriter().print("Coinvolto in noleggi");
                            response.setStatus(400);
                        }
                    } else {
                        Utility.print("L'utente"+emailToDelete+" non esiste...");
                        response.getWriter().print("L'utente non esiste");
                        response.setStatus(400);
                    }

                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eseguire l'operazione");
                    response.setStatus(400);
                    e.printStackTrace();
                }
                break;
            case "loadusers":
                Utility.print("Provando a caricare gli utenti...");
                UserModelDS userLoader = new UserModelDS(ds);
                try {
                    Collection<UserBean> userList = userLoader.fetchAllUsers();
                    request.setAttribute("userList",userList);
                    Utility.print("Ho settato l'attributo userList nella servlet");
                    Utility.print(userList.toString());
                    dispatcher = this.getServletContext().getRequestDispatcher("/userListComponent.jsp");
                    Utility.print("Eseguendo il dispatching");
                    dispatcher.forward(request,response);
                    return;
                } catch (SQLException e) {
                    Utility.print("Impossibile prelevare gli utenti...");
                    e.printStackTrace();
                }

                break;
            case "loadcars":
                Utility.print("Provando a caricare le auto...");
                CarModelDS carLoader = new CarModelDS(ds);
                try {
                    Collection<CarBean> carList = carLoader.doRetrieveAll(null);
                    request.setAttribute("carList",carList);
                    dispatcher = this.getServletContext().getRequestDispatcher("/carListComponent.jsp");
                    dispatcher.forward(request,response);
                    return;
                } catch (SQLException e) {
                    Utility.print("Impossibile prelevare le auto...");
                    e.printStackTrace();
                }
                break;
            case "loadrents":
                Utility.print("Provando a caricare i noleggi...");
                RentModelDS rentLoader = new RentModelDS(ds);
                try {
                    Collection<RentBean> rentList = rentLoader.fetchAllRents();
                    request.setAttribute("rentList",rentList);
                    Utility.print(rentList.toString());
                    dispatcher = this.getServletContext().getRequestDispatcher("/rentListComponent.jsp");
                    dispatcher.forward(request,response);
                    return;
                } catch (SQLException e) {
                    Utility.print("Impossibile prelevare i noleggi...");
                    e.printStackTrace();
                }
                break;
            case "removerent":
                RentModelDS rentmodelds = new RentModelDS(ds);
                try {
                    if (rentmodelds.rentExists(Integer.parseInt(request.getParameter("rentCode")))) {
                        if (rentmodelds.removeRent(Integer.parseInt(request.getParameter("rentCode")))) {
                            response.getWriter().print("Noleggio eliminato con successo");
                        } else {
                            response.getWriter().print("Impossibile eliminare il noleggio");
                            response.setStatus(400);
                        }
                    } else {
                        response.getWriter().print("Noleggio inesistente");
                        response.setStatus(400);
                    }
                } catch (SQLException e) {
                    response.getWriter().print("Impossibile eseguire l'operazione");
                    response.setStatus(400);
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
