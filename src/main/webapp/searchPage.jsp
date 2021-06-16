<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>



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
                                                                 href="#">Aiuto</a> <a href="loginPage.jsp">
                    <button id="login-button" type="button" class="btn btn-success">Login</button>
                </a>
                </div>
            </div>
        </div>
    </nav>



    <div id="left-section">

        <div id="shop-section">
            <h3 id="cart-label">Carrello <i class="fas fa-shopping-cart"></i></h3>
            <div id="buy-all">
                <input type="submit" value="Noleggia" id="checkout-button">
            </div>

            <div class="card border-success mb-3" style="max-width: 18rem;">
                <div class="card-header">Header</div>
                <div class="card-body text-success">
                    <h5 class="card-title">Success card title</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                </div>
            </div>

            <div class="card border-success mb-3" style="max-width: 18rem;">
                <div class="card-header">Header</div>
                <div class="card-body text-success">
                    <h5 class="card-title">Success card title</h5>
                    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                </div>
            </div>

        </div>

    </div>

    <div id="right-section">

        <div class="container" id="container1">
            <div class="row text-center" id="row1">
                <h2 style="color: white;">Effettua la ricerca e verifica la disponibilità</h2>
            </div>
            <form class="search-bar">
                <div class="row justify-content-center" id="row2">
                    <div class="col-md-3">
                        <label>Località</label> <select class="form-select"
                                                        aria-label="Default select example">
                        <option selected>Località</option>
                        <option value="1">Caserta</option>
                        <option value="2">Milano</option>
                        <option value="3">Three</option>
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
                                                                               class="form-control" id="pickup-date">
                        </div>
                    </div>
                    <div class="col-md-3">
                        <input type="submit" value="CERCA LA TUA AUTO" id="search-button">
                    </div>

                </div>
            </form>
        </div>

        <div class="card mb-3" style="width: 800px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img class="img-car" src="immagini/peugeot.svg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h4 class="card-title"><b>Peugeout 208</b></h4>
                        <p class="card-text">
                            <i class="fas fa-gas-pump"></i> <b>Diesel</b> &nbsp&nbsp
                            <i class="fas fa-car"></i> <b>60 kWh</b> &nbsp&nbsp
                            <i class="fas fa-calendar-alt"></i> <b>2020</b> &nbsp&nbsp
                            <i class="fas fa-tachometer-alt"></i> <b>23.500 Km</b>

                        <div class="price-section">
                            <div class="price">
                               <h5><i class="fas fa-euro-sign"></i> <b>15.00 al giorno</b></h5>
                            </div>
                            <input type="submit" value="Aggiungi al carrello" class="buy-button">


                        </div>


                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3" style="width: 800px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img class="img-car" src="immagini/mercedes.svg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3" style="width: 800px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img class="img-car" src="immagini/opel.svg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mb-3" style="width: 800px;">
            <div class="row g-0">
                <div class="col-md-4">
                    <img class="img-car" src="immagini/skoda.svg" alt="...">
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Card title</h5>
                        <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                </div>
            </div>
        </div>


    </div>



</body>
</html>
