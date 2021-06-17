<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="carent.model.CarBean" %>
<%@ page import="carent.model.UserBean" %>
<%@ page import="carent.utils.Utility" %><%
    Utility.print("Sto refreshando le auto");
    Collection<?> carList = (Collection<?>) request.getAttribute("carListRefreshed");
    Iterator<?> it = carList.iterator();
    UserBean utente = (UserBean) session.getAttribute("utente");
    String dataInizio = (String) request.getAttribute("start-date-refreshed");
    String dataFine = (String) request.getAttribute("finish-date-refreshed");
    String luogo = (String) request.getAttribute("pick-up-place");
    CarBean bean;
    while (it.hasNext()) {
        bean = (CarBean) it.next();
        Utility.print(bean.toString());
%>
<div data-targa="<%=bean.getTarga()%>" class="card mb-3" style="width: 800px;">
    <div class="row g-0">
        <div class="col-md-4">
            <img class="img-car" src="<%=application.getContextPath()+"/immagini/"+bean.getTarga()+".jpg"%>" alt="...">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h4 class="card-title"><b><%=bean.getMarca()+" "+bean.getModello()%></b></h4>
                <p class="card-text">
                    <i class="fas fa-gas-pump"></i> <b><%=bean.getAlimentazione()%></b> &nbsp&nbsp
                    <i class="fas fa-car"></i> <b><%=bean.getPotenza()%> kWh</b> &nbsp&nbsp
                    <i class="fas fa-calendar-alt"></i> <b><%=bean.getAnnoImmatricolazione()%></b> &nbsp&nbsp
                    <i class="fas fa-tachometer-alt"></i> <b><%=bean.getChilometraggio()%> Km</b>
                <div class="price-section">
                    <div class="price">
                        <h5><i class="fas fa-euro-sign"></i> <b><%=bean.getPrezzo_gg()%> al giorno</b></h5>
                    </div>
                    <%
                        if (utente!=null && utente.getRole().equals("userrole") && dataInizio!=null && dataFine!=null) {
                    %>
                    <input type="submit" value="Aggiungi al carrello" data-luogo="<%=luogo%>" data-start="<%=dataInizio%>" data-finish="<%=dataFine%>" data-targa="<%=bean.getTarga()%>" class="buy-button">
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</div>
<%
    }
%>
<script> $(document).ready (
    () => {
        $(".buy-button").click (
            function () {
                alert($(this).attr("data-targa"));
                alert($(this).attr("data-start"));
                alert($(this).attr("data-finish"));
                alert($(this).attr("data-luogo"));
                $.ajax (
                    {
                        url: "user/settings",
                        type: "POST",
                        data: {
                            changetype: "addToCart",
                            plate: $(this).attr("data-targa"),
                            startdate: $(this).attr("data-start"),
                            finishdate: $(this).attr("data-finish"),
                            place: $(this).attr("data-luogo")
                        },
                        success: function (data) {
                            alert("Servlet addToCart terminata con successo")
                            //Aggiorni il carrello
                        },
                        error: function (xhr,ajaxOptions, thrownError) {
                            alert(xhr.responseText);
                        }
                    }
                )
            }
        )

    }
)</script>
