<%@ page import="carent.utils.Utility" %>
<%@ page import="carent.model.CartBean" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.CartItemBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String fromServlet = (String) request.getAttribute("fromServlet");
    if (fromServlet==null) {
        response.sendRedirect(response.encodeRedirectURL("user/checkout"));
        return;
    }
    Utility.print("Sono passato per la servlet user checkout");

    CartBean cartBean = (CartBean) session.getAttribute("cart");
    if (cartBean==null) {
        response.sendRedirect(response.encodeRedirectURL(application.getContextPath()+"/home"));
        return;
    } else {
        if (cartBean.isEmpty()) {
            response.sendRedirect(response.encodeRedirectURL(application.getContextPath()+"/home"));
            return;
        }
    }

    Utility.print("Sono sopravvissuto");

%>
<html lang="it">
<head>
    <title>Checkout</title>

    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
            crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="<%=application.getContextPath()+"/css/checkoutStyle.css"%>" rel="stylesheet" />
    <link rel="icon" href="<%=application.getContextPath()+"/immagini/favicon.svg"%>" type="image/x-icon" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="<%=application.getContextPath()+"/scripts/checkoutSwitch.js"%>"></script>

</head>
<body>

<!--NavBar-->
<nav
        class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark navbar-collapse">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=application.getContextPath()+"/index.jsp"%>"> <img
                src="<%=application.getContextPath()+"/immagini/logo-carent.svg"%>" alt="logo" id="logo">
        </a>


        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="#">Home</a> <a class="nav-link" href="<%=application.getContextPath()+"/home"%>">Veicoli</a>
                <a class="nav-link" href="#">Contatti</a>
                <a class="nav-link" href="#">Aiuto</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <h1 style="color: #00996b">Checkout Noleggio</h1>
    <div id="checkout-window">

        <div id="card-section">

            <h2 style="color: white;">Auto selezionate: </h2>
            <%
                Iterator<?> it = cartBean.getCart().iterator();
                CartItemBean cartItemBean;
                while (it.hasNext()) {
                    cartItemBean = (CartItemBean) it.next();
            %>
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title"><b><%=cartItemBean.getAuto().getMarca() + " " + cartItemBean.getAuto().getModello()%></b></h4>
                    <p class="card-text">
                        <i class="fas fa-gas-pump"></i> <b><%=cartItemBean.getAuto().getAlimentazione()%></b> &nbsp&nbsp
                        <i class="fas fa-car"></i> <b><%=cartItemBean.getAuto().getPotenza()%> kWh</b> &nbsp&nbsp
                        <i class="fas fa-calendar-alt"></i> <b><%=cartItemBean.getAuto().getAnnoImmatricolazione()%></b>
                        <i class="fas fa-tachometer-alt"></i> <b><%=cartItemBean.getAuto().getChilometraggio()%> Km</b>
                        <i class=""><b><%=cartItemBean.getDaData()%></b></i>
                        <i class=""><b><%=cartItemBean.getaData()%></b></i>
                    </p>
                    <div class="price-section">
                        <div class="price">
                            <h5><i class="fas fa-euro-sign"></i> <b><%=cartItemBean.getPrezzoTotale()%></b></h5>
                        </div>
                    </div>
                </div>
            </div>
            <%
                }
            %>

        <div id="checkout-section">
            <h2 style="color: white;">Riepilogo: </h2>
            <div class="card" style="width: 18rem; height: 50%;">
                <div class="card-body">
                    <h4 class="card-title"><b>Periodi: </b></h4>
                    <%
                        it = cartBean.getCart().iterator();
                        long periodoTotale = 0;
                        while (it.hasNext()) {
                            cartItemBean = (CartItemBean) it.next();
                     %>
                    <h6><%=cartItemBean.getAuto().getMarca() + " " + cartItemBean.getAuto().getModello()%>: <%=cartItemBean.calcolaPeriodo()%> giorni</h6>
                    <%
                            periodoTotale+=cartItemBean.calcolaPeriodo();
                        }
                    %>
                    <h4 class="card-title"><b>Periodo totale: </b></h4>
                    <h6><%=periodoTotale%> giorni </h6>
                    <h4 class="card-title"><b>Prezzi: </b></h4>
                    <%
                        it = cartBean.getCart().iterator();
                        while (it.hasNext()) {
                            cartItemBean = (CartItemBean) it.next();
                    %>
                    <h6><%=cartItemBean.getAuto().getMarca() + " " + cartItemBean.getAuto().getModello()%>: €<%=cartItemBean.getPrezzoTotale()%></h6>
                    <%
                            periodoTotale+=cartItemBean.calcolaPeriodo();
                        }
                    %>
                    <h4 id="total-label" class="card-title"><b>Totale: <%=cartBean.getTotal()%></b> <i class="fas fa-euro-sign"></i></h4>
                </div>

                <button type="button" class="btn btn-success" id="checkout-butt">
                    Paga
                </button>

            </div>

        </div>

        <div id="img-section">
            <img src="<%=application.getContextPath()+"/immagini/payIcon.svg"%>" alt="Payment icon" width="300" height="300">
        </div>

        </div>


    </div>
</div>


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>

