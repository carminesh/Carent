<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String pass = (String) request.getAttribute("pass");
    if (pass == null) {
        response.sendRedirect(response.encodeRedirectURL("access"));
        return;
    }

    String esito = (String) request.getAttribute("esito");
    if(esito == null) {
        System.out.println("Esito è null");
    } else {
        System.out.println("Esito non è null");
    }
    String error = (String) request.getAttribute("error");
    if(error == null) {
        System.out.println("Error è null");
    } else {
        System.out.println("Error non è null");
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carent- Noleggio Veicoli</title>

    <link href="css/loginStyle.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="scripts/loginSwitch.js"></script>

</head>
<body>

<!--Navbar-->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark navbar-collapse">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp">
            <img src="immagini/logo-carent.svg" alt="logo" id="logo">
        </a>


        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="#">Veicoli</a>
                <a class="nav-link" href="#">Contatti</a>
                <a class="nav-link" href="#">Aiuto</a>
            </div>
        </div>
    </div>
</nav>

<!--Login section-->
<div class="container">
    <div class="login-window">


        <div class="container justify-content-center">
            <div class="row justify-content-center">
                <form id="form-login" method="post" action="login">
                    <div class="row justify-content-center" id="row-email">
                        <div class="row text-center">
                            <div id="esito">
                                <% if (esito!=null) { %> <%=esito%> <% } %>
                            </div>

                            <div id="errore">
                                <% if (error!=null) { %> <%=error%> <% } %>
                            </div>
                            <h2 id="title-window">Accedi al servizio</h2>
                        </div>
                        <div class="col-sm-10 col-md-6" id="email-input">
                            <label for="exampleInputEmail1" class="form-label">Indirizzo
                                Email</label> <input type="email" name="email" class="form-control"
                                                     id="exampleInputEmail1" aria-describedby="emailHelp"
                                                     placeholder="Email" required>
                            <div id="emailHelp" class="form-text" style="font-size: 15px;">Non
                                condivideremo mai la tua email con terzi.
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center" id="row-password">
                        <div class="col-sm-10 col-md-6" id="password-input">
                            <label class="form-label">Password</label>
                            <input type="password" name="passwd" class="form-control"
                                   id="user-password" placeholder="Password" required>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        <button type="submit" class="btn btn-success" id="login-button">Login</button>
                        <div class="form-text" id="text-for-registration">
                            Non sei registrato? <a id="registration-label">Registrati</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>
</html>