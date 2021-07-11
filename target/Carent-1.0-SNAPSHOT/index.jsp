<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,carent.model.*"%>
<%@ page import="carent.utils.Utility" %>
<!DOCTYPE html>

<%
UserBean user = (UserBean) session.getAttribute("utente");
String pass = (String) request.getAttribute("pass");
if (pass==null) {
	response.sendRedirect(response.encodeRedirectURL("home"));
}
%>



<html lang="it">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Carent - Noleggio Veicoli</title>
	<link href="<%=application.getContextPath()+"/css/style.css"%>" rel="stylesheet" />
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
		crossorigin="anonymous">
	<link rel="icon" href="<%=application.getContextPath()+"/immagini/favicon.svg"%>" type="image/x-icon" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
		  integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
		  crossorigin="anonymous" referrerpolicy="no-referrer"/>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>

	<!--Navbar-->

	<nav
		class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark navbar-collapse">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp"> <img
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
					<a class="nav-link" href="<%=application.getContextPath()+"/search"%>">Veicoli</a>
					<a class="nav-link" href="#authors">Contatti</a>


					<%
					if (user == null) {
					%>
						<a href="<%=application.getContextPath()+"/access"%>">
						<button id="login-butt" type="button" class="btn btn-success">Login</button> <%
 					} else if (user.getRole().equals("userrole")){
 					%> <a href="<%=application.getContextPath()+"/user/page"%>">
						<button id="login-butt" type="button" class="btn btn-success"><%=user.getName()%></button>
					<%} else { %>
						<a href="<%=application.getContextPath()+"/admin/page"%>">
						<button id="login-butt" type="button" class="btn btn-success"><%=user.getName()%></button>
					<%
					}
					%>
					</a>
				</div>
			</div>
		</div>
	</nav>




	<!--First-section-->
	<div class="mid-top">
		<div class="row text-center">
			<h2>Prendi subito le tue chiavi</h2>
			<p>
				Un modo facile e veloce per fittare un'auto.<br> Scegli tra più
				di 10 unici modelli, dall'auto per tutti i giorni, all'auto più
				sportiva ed estroversa.<br> Puoi trovare l'auto perfetta per
				te. Vedi cosa abbiamo da offrirti.
			</p>
		</div>
		<div id="top-image" class="carousel slide" data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="<%=application.getContextPath()+"/immagini/sfondooo.svg"%>" class="d-block w-100"
						alt="wallpaper">
				</div>
			</div>
		</div>
	</div>
	<!--Search-section-->
	<div class="container" id="container1">
		<div class="row text-center" id="row1">
			<h2>Effettua la ricerca e verifica la disponibilità</h2>
		</div>
		<form class="search-bar" method="POST" action="<%=application.getContextPath()+"/search"%>" onsubmit="checkFields()">
			<div class="row justify-content-center" id="row2">
				<div class="col-md-3">
					<label>Località</label> <select name="pick-up-place" class="form-select"
						aria-label="Default select example" id="pickup-place">
						<option value="Localita"selected>Località</option>
						<option value="Caserta">Caserta</option>
						<option value="Milano">Milano</option>
						<option value="Three">Three</option>
					</select>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="date">Data di ritiro:</label> <input type="date"
							class="form-control" id="pickup-date" name="start-date">
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group">
						<label for="date">Data di consegna:</label> <input type="date"
							class="form-control" id="release-date" name="finish-date">
					</div>
				</div>
				<div class="col-md-3">
					<input type="submit" value="CERCA AUTO" id="search-button">
				</div>
				<div id="search-error">
					<%
						String error = (String) request.getAttribute("error");
						if (error!=null) {
					%>
					<h5 id="data-label">Periodo non valido</h5>
					<%
						}
					%>
				</div>
			</div>
		</form>
		<label style="color: white; padding-left: 5px;">*Si consiglia di prenotare la vettura 48h prima</label>
	</div>

	<!--Card-carousel-->
	<div class="container-fluid">
		<div class="row text-center" id="row3">
			<h2>Il nostro parco auto</h2>
			<p>Scegli tra una vasta gamma di modelli a tua disposizione</p>

		</div>
		<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">

			<div class="row justify-content-center" id="card-row">
				<div class="col-sm-12">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="container">
								<div class="row justify-content-center">

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/bmw.svg"%>">
											<h5 class="card-title">Bmw i12</h5>
											<p class="card-text">«Freude am Fahren». Premi "Dettagli" per maggiori inf...</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/smart.svg"%>">
											<h5 class="card-title">Smart forTwo</h5>
											<p class="card-text">«Open your mind». Premi "Dettagli" per maggiori informazioni</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/fiat.svg"%>">
											<h5 class="card-title">Fiat 500</h5>
											<p class="card-text">«Ogni Fiat è 500% Fiat». Premi "Dettagli" per maggiori inf...</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/mercedes.svg"%>">
											<h5 class="card-title">Mercedes C</h5>
											<p class="card-text">«The best or nothing». Premi "Dettagli" per maggiori inf...</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>


								</div>
							</div>
						</div>
						<div class="carousel-item">
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/peugeot.svg"%>">
											<h5 class="card-title">Peugeot 208</h5>
											<p class="card-text">«Lions of our time». Premi "Dettagli" per maggiori inf...</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/opel.svg"%>">
											<h5 class="card-title">Opel Grandland</h5>
											<p class="card-text">«The future is everyone's». Premi "Dettagli" per maggiori inf...</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/polo.svg"%>">
											<h5 class="card-title">Volswagen Polo</h5>
											<p class="card-text">«Das Auto». Premi "Dettagli" per maggiori informazioni</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="<%=application.getContextPath()+"/immagini/skoda.svg"%>">
											<h5 class="card-title">Skoda Octavia</h5>
											<p class="card-text">«Simply clever». Premi "Dettagli" per maggiori informazioni</p>
											<a href="<%=application.getContextPath()+"/search"%>" class="btn btn-success">Dettagli</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls"  data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

	</div>

	<!--Footer-->
	<div class="footer-div">
		<footer class="bg-dark text-center text-lg-start" id="footer">

			<div class="container p-4">

				<div class="row">

					<div class="col-lg-6 col-md-12 mb-4 mb-md-0">
						<h5 class="text-uppercase" style="color: #00bf85;">Carent -
							Noleggio Veicoli</h5>

						<p id="footer-text">
							Carent è una piattaforma per il noleggio di auto a breve e lungo termine.
							Si propone come un sito web per il noleggio di autoveicoli dedicato a tutti.
						</p>

					</div>


					<div class="col-lg-3 col-md-6 mb-4 mb-md-0" id="authors">
						<h5 class="text-uppercase mb-0" style="color: #00bf85;">Autori Sito</h5>

						<ul class="list-unstyled">
							<li><a href="#!">Carmine Fabbri</a></li>
							<li><a href="#!">Dario Trinchese</a></li>

						</ul>
					</div>

					<div class="col-lg-3 col-md-6 mb-4 mb-md-0" id="link">
						<h5 class="text-uppercase mb-0" style="color: #00bf85;">Link utili</h5>

						<ul class="list-unstyled" id="link-list">
							<li><a href="https://github.com/carminesh/Carent/commits/master"> <i style="font-size: 40px;" class="fab fa-github-square"></i> Github</a></li>
						</ul>
					</div>
				</div>
			</div>

		</footer>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
		crossorigin="anonymous"></script>
</body>
</html>