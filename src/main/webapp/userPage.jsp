<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.*,carent.model.*, carent.utils.*" %>
<!DOCTYPE html>

<%

    String fromServlet = (String) request.getAttribute("fromServlet");
    if (fromServlet == null) {
        Utility.print("Devi prima passare per la servlet");
        response.sendRedirect(response.encodeRedirectURL("user/page"));
        return;
    } else {
        Utility.print("Sei passato per la servlet");
    }

    UserBean user = (UserBean) session.getAttribute("utente");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Profilo di <%=user.getName()%></title>
    <link href="<%=application.getContextPath()+"/css/userStyle.css"%>" rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
            crossorigin="anonymous">
    <link rel="icon" href="<%=application.getContextPath()+"/immagini/favicon.svg"%>" type="image/x-icon" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=application.getContextPath()+"/scripts/change.js"%>"></script>
    <script src="<%=application.getContextPath()+"/scripts/userButtonToggle.js"%>"></script>
</head>
<body>

<!--Navbar-->

<nav
        class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark navbar-collapse">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=application.getContextPath()+"/home"%>"> <img
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
                <a class="nav-link" href="<%=application.getContextPath()+"/home"%>">Home</a> <a class="nav-link" href="<%=application.getContextPath()+"/search"%>" >Veicoli</a>
                <a class="nav-link" href="<%=application.getContextPath()+"/index.jsp#authors"%>">Contatti</a>
                <form action="<%=application.getContextPath()+"/logout"%>" method="GET">
                    <input id="logout-button" type="submit" value="Logout" class="btn btn-success">
                </form>
            </div>
        </div>
    </div>
</nav>

<div class="container-fluid" id="info-container">

    <div class="row  align-items-start-center" id="row1">
        <div class="col-lg-4 col-md-12 col-sm-12" id="column1">
            <figure class="figure">
                <img src="<%=application.getContextPath()+"/immagini/avatar.svg"%>" class="figure-img img-fluid rounded"
                     alt="profile-icon">
            </figure>
        </div>

        <div class="col-lg-4 col-md-12 col-sm-12" id="column2">
            <div class="row text-start" id="info-label">
                <h2>Dati personali <i class="fas fa-address-card"></i></h2>
            </div>
            <div id="infos">
                <h4>Nome: <span id="name"><%=user.getName() %></span></h4>
                <h4>Cognome: <span id="surname"><%=user.getSurname() %> </span></h4>
                <h4>Email: <span id="currentemail"><%=user.getEmail() %></span></h4>
                <h4>Telefono: <span id="telefono"><%=user.getPhone() %></span></h4>
            </div>
        </div>

        <div class="col-lg-4 col-md-12 col-sm-12" id="column3">
            <div class="row text-start" id="rent-label">
                <h2>Ultimi Noleggi <i class="fas fa-car"></i></h2>

                <ol class="list-group list-group-numbered" id="early-rents-list">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">Subheading</div>
                            Cras justo odio
                        </div>
                        <span class="badge bg-primary rounded-pill">14</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">Subheading</div>
                            Cras justo odio
                        </div>
                        <span class="badge bg-primary rounded-pill">14</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <div class="fw-bold">Subheading</div>
                            Cras justo odio
                        </div>
                        <span class="badge bg-primary rounded-pill">14</span>
                    </li>
                </ol>
            </div>
        </div>

        <div class="row align-items-start-center" id="row2">

            <div class="col-12" id="column4">
                <h2 id="settings-label">Impostazioni <i class="fas fa-cog"></i></h2>
                <div id="button-email">
                    <button type="button" class="btn btn-success" id="modificaemailButton">Modifica
                        Email
                    </button>
                </div>

                <div id="container-form-email">
                    <div class="form-email">
                        <input type="email" id="newemail" placeholder="Nuova email">
                        <div class="result-div">
                            <button type="button" class="btn btn-success" id="emailchangebutton">Cambia</button>
                            <div id="result-change-email-div"></div>
                        </div>
                    </div>


                </div>

                <div id="button-password">
                    <button type="button" class="btn btn-success" id="modificapassButton">Modifica
                        Password
                    </button>
                </div>

                <div id="container-form-password">
                    <div id="form-password">
                        <input type="password" id="oldpass" placeholder="Vecchia password">
                        <br>
                        <input type="password" id="newpass" placeholder="Nuova password">
                        <div class="result-div">
                            <button type="button" class="btn btn-success" id="passchangebutton">Cambia</button>
                            <div id="result-change-psswd-div"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>


<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous">

</script>

</body>
</html>