<%@ page import="carent.utils.Utility" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.CarBean" %>
<%@ page import="carent.model.UserBean" %>
<%@ page import="carent.model.CartBean" %>
<%@ page import="carent.model.CartItemBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Collection<?> carList = (Collection<?>) request.getAttribute("carList");
    if (carList==null) {
        Utility.print("carList era null");
        response.sendRedirect(response.encodeRedirectURL("search"));
        return;
    }
    UserBean utente = (UserBean) session.getAttribute("utente");
    CartBean cartBean = (CartBean) session.getAttribute("cart");

    String dataInizio = (String) request.getAttribute("start-date");
    String dataFine = (String) request.getAttribute("finish-date");
    String luogo = (String) request.getAttribute("pick-up-place");
    Utility.print("Sto per caricare la searchPage");
    Utility.print("dataInizio: "+dataInizio);
    Utility.print("dataFine: "+dataFine);
    Utility.print("luogo: "+luogo);
%>

<html lang="it">
<head>
    <title>Ricerca</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
            crossorigin="anonymous">
    <link href="css/searchStyle.css" rel="stylesheet" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link rel="icon" href="<%=application.getContextPath()+"/immagini/favicon.svg"%>" type="image/x-icon" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script src="<%=application.getContextPath()+"/scripts/searchPageScripts.js"%>"></script>
    <script src="<%=application.getContextPath()+"/scripts/sideBar.js"%>"></script>


</head>
<body>


<!--Navbar-->
<nav
        class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark navbar-collapse">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"> <img
                src="immagini/logo-carent.svg" alt="logo" id="logo">
        </a>


        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="#">Home</a> <a class="nav-link" href="#">Veicoli</a>
                <a class="nav-link" href="#">Contatti</a> <a class="nav-link"
                                                             href="#">Aiuto</a>


                <%
                    if (utente != null && utente.getRole().equals("userrole")) {
                %>
                <a href="<%=application.getContextPath()+"/user/page"%>">
                    <button type="button" class="btn btn-success"><%=utente.getName()%></button>
                        <%
 					} else {
 					%> <a href="access">
                    <button type="button" class="btn btn-success">Login</button>
                    <%}%>
                </a>
            </div>
        </div>
    </div>
</nav>

<% if (utente!=null && utente.getRole().equals("userrole")) {%>

<div id="left-section">

    <div id="shop-section">
        <%
            if (!cartBean.isEmpty()) {
        %>
        <h2 id="cart-label">Carrello <i class="fas fa-shopping-cart"></i></h2>
        <h3 id="total-label">Totale: <%=cartBean.getTotal()%> <i class="fas fa-euro-sign"></i></h3>
        <div id="buy-all">
            <input type="submit" value="Noleggia" id="checkout-button" onsubmit="servletpercheckout">
        </div>
        <%
                Collection<?> cart = (Collection<?>) cartBean.getCart();
                Iterator<?> it = cart.iterator();
                CartItemBean itemBean;
                while (it.hasNext()) {
                    itemBean=(CartItemBean) it.next();
        %>
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h4 class="card-title"><b><%=itemBean.getAuto().getMarca() + " " + itemBean.getAuto().getModello()%></b></h4>
                <p class="card-text">
                    <i class="fas fa-gas-pump"></i> <b><%=itemBean.getAuto().getAlimentazione()%></b> &nbsp&nbsp
                    <i class="fas fa-car"></i> <b><%=itemBean.getAuto().getPotenza()%> kWh</b> &nbsp&nbsp
                    <i class="fas fa-calendar-alt"></i> <b><%=itemBean.getAuto().getAnnoImmatricolazione()%></b>
                    <i class="fas fa-tachometer-alt"></i> <b><%=itemBean.getAuto().getChilometraggio()%> Km</b>
                </p>
                <div class="price-section">
                    <div class="price">
                        <h5><i class="fas fa-euro-sign"></i> <b><%=itemBean.getPrezzoTotale()%></b></h5>
                        <div id="delete-from-cart-div" data-targa=<%=itemBean.getAuto().getTarga()%>></div>
                    </div>
                </div>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <h2 id="cart-label">Carrello vuoto</h2>
        <%
            }
        %>
    </div>
</div>

<!--Slider Section-->
<div id="slider">
    <div id="slider-icon-div">
        <i id="slider-icon" class="fas fa-arrow-alt-circle-right"></i>
    </div>
</div>


<%
    }
%>
<div id="right-section">

    <div class="container" id="container1">
        <div class="row text-center" id="row1">
            <h2 style="color: white;">Effettua la ricerca e verifica la disponibilità</h2>
        </div>
        <form class="search-bar">
            <div class="row justify-content-center" id="row2">
                <div class="col-md-3">
                    <label>Località</label> <select id="pick-up-place" class="form-select"
                                                    aria-label="Default select example">
                    <option value="Localita" selected>Località</option>
                    <option value="Caserta">Caserta</option>
                    <option value="Milano">Milano</option>
                    <option value="Three">Three</option>
                </select>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label for="date">Data di ritiro:</label> <input type="date"
                                                                         class="form-control" id="pickup-date">
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-group">
                        <label for="date">Data di consegna:</label> <input type="date"
                                                                           class="form-control" id="release-date">
                    </div>
                </div>
                <div class="col-md-3">
                    <input type="button" value="CERCA LA TUA AUTO" id="search-button">
                </div>

            </div>
        </form>
    </div>

    <div id="car-list-div">

        <%
            Iterator<?> it = carList.iterator();
            CarBean bean;
            while (it.hasNext()) {
                bean = (CarBean) it.next();
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
                                if (utente!=null && utente.getRole().equals("userrole") && dataInizio!=null && dataFine!=null && luogo!=null) {
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
    </div>
</div>


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>

</body>
</html>