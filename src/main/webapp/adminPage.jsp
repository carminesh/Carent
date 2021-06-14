<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.*,carent.model.*" %>
<!DOCTYPE html>

<%
    UserBean user = (UserBean) session.getAttribute("admin");
    if (user == null) {
        response.sendRedirect(response.encodeRedirectURL("/CRStorage/loginPage.jsp"));
    }
%>


<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carent - Admin Panel</title>
    <link href="css/adminStyle.css" rel="stylesheet"/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
          integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="scripts/adminScripts.js"></script>
    <script src="scripts/adminButtonToggle.js"></script>

</head>

<body>

    <div class="container-fluid" id="welcome-container">
        <img src="immagini/logo-carent.svg" alt="logo" id="logo">
        <h2 id="title-role">Admin ${admin.getName()} - Pannello di Controllo</h2>
        <form action="logout" method="GET">
            <button type="submit" id="logout-button" class="btn btn-success"><i class="fas fa-sign-out-alt"></i></button>
        </form>
    </div>


    <div class="container-fluid" id="upper-container">
        <div class="row  align-items-start-center" id="row1">

            <div class="col-lg-3 col-md-6 col-sm-12" id="column1">
                <button type="button" class="btn btn-success" id="delete-car">
                Elimina Veicolo <i class="fas fa-trash-alt"></i>
                </button>

                <div id="form-container-delete-car">
                    <div class="form">
                        <input type="text" id="deleted-car" placeholder="Targa del veicolo da rimuovere">
                        <br>
                        <button type="button" class="btn btn-success" id="delete-car-button">Rimuovi</button>
                    </div>
                </div>

            </div>



            <div class="col-lg-3 col-md-6 col-sm-12" id="column2">
                <button type="button" class="btn btn-success" id="delete-user">
                    Elimina Utente <i class="fas fa-user-slash"></i>
                </button>

                <div id="form-container-delete-user">
                    <div class="form">
                        <input type="email" id="deleted-user" placeholder="Email dell'utente da rimuovere">
                        <br>
                        <button type="button" class="btn btn-success" id="delete-user-button">Rimuovi</button>
                    </div>
                </div>

            </div>


            <div class="col-lg-3 col-md-6 col-sm-12" id="column3">
                <button type="button" class="btn btn-success" id="add-car">
                    Aggiungi Veicolo <i class="fas fa-plus"></i>
                </button>

                <div id="form-container-add-car">
                    <div class="form">
                        <input type="text" id="add-car-plate" placeholder="Targa veicolo">
                        <br>
                        <input type="text" id="add-car-brand" placeholder="Marca veicolo">
                        <br>
                        <input type="text" id="add-car-model" placeholder="Modello veicolo">
                        <br>
                        <input type="text" id="add-car-power" placeholder="Potenza veicolo">
                        <br>
                        <input type="text" id="add-car-fuel" placeholder="Alimentazione veicolo">
                        <br>
                        <input type="text" id="add-car-year" placeholder="Anno immatricolazione veicolo">
                        <br>
                        <input type="text" id="add-car-mileage" placeholder="Chilometraggio veicolo">
                        <br>

                        <button type="button" class="btn btn-success" id="add-car-button">Aggiungi</button>
                    </div>
                </div>

            </div>


            <div class="col-lg-3 col-md-6 col-sm-12" id="column4">
                <button type="button" class="btn btn-success" id="add-car-image">
                    Aggiungi foto veicolo <i class="fas fa-file-image"></i>
                </button>

                <div id="form-container-add-car-image">
                    <div class="form">
                        <form enctype="multipart/form-data" action="imgupload" method="POST" onsubmit="return targaValida()">
                            <input type="file" id="img-form" name="car-image" accept=".jpg" required>
                            <br>
                            <input type="text" id="add-plate" name="targaPerFoto" placeholder="Targa veicolo" required>
                            <br>
                            <input type="submit" id="add-photo-button" value="Aggiungi">
                        </form>
                    </div>
                </div>

            </div>

        </div>
    </div>

</body>
</html>