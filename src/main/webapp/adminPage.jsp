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
    <link

</head>

<body>


    <h2 id="title-role">Admin ${admin.getName()} - Pannello di Controllo</h2>

    <div class="container-fluid" id="button-container">
        <div class="row  align-items-start-center" id="row1">

            <div class="col-4" id="column1">
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



            <div class="col-4" id="column2">
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


            <div class="col-4" id="column3">
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


        </div>
    </div>

</body>
</html>