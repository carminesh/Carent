<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="css/checkoutStyle.css" rel="stylesheet" />
    <link rel="icon" href="immagini/favicon.svg" type="image/x-icon" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="scripts/checkoutSwitch.js"></script>

</head>
<body>

<!--NavBar-->
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

            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title"><b>Peugeot 208</b></h4>
                    <p class="card-text">
                        <i class="fas fa-gas-pump"></i> <b>Diesel</b> &nbsp&nbsp
                        <i class="fas fa-car"></i> <b>75 kWh</b> &nbsp&nbsp
                        <i class="fas fa-calendar-alt"></i> <b>2019</b>
                        <i class="fas fa-tachometer-alt"></i> <b>13.000 Km</b>
                    </p>
                    <div class="price-section">
                        <div class="price">
                            <h5><i class="fas fa-euro-sign"></i> <b>14 al giorno</b></h5>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h4 class="card-title"><b>Fiat Panda</b></h4>
                    <p class="card-text">
                        <i class="fas fa-gas-pump"></i> <b>Benzina</b> &nbsp&nbsp
                        <i class="fas fa-car"></i> <b>50 kWh</b> &nbsp&nbsp
                        <i class="fas fa-calendar-alt"></i> <b>2001</b>
                        <i class="fas fa-tachometer-alt"></i> <b>143.400 Km</b>
                    </p>
                    <div class="price-section">
                        <div class="price">
                            <h5><i class="fas fa-euro-sign"></i> <b>5 al giorno</b></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="checkout-section">
            <h2 style="color: white;">Riepilogo: </h2>
            <div class="card" style="width: 18rem; height: 50%;">
                <div class="card-body">
                    <h4 class="card-title"><b>Luogo di ritiro: </b></h4>
                    <h6>Caserta</h6>
                    <h4 class="card-title"><b>Luogo di consegna: </b></h4>
                    <h6>Milano</h6>
                    <h4 class="card-title"><b>Data di ritiro: </b></h4>
                    <h6>26/08/2021</h6>
                    <h4 class="card-title"><b>Data di consegna: </b></h4>
                    <h6>12/09/2021</h6>
                    <h4 id="total-label" class="card-title"><b>Totale: 400</b> <i class="fas fa-euro-sign"></i></h4>
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


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>

