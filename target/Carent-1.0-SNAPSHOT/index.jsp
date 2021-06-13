<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,carent.model.*"%>
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
<link href="css/style.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl"
	crossorigin="anonymous">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="scripts/carLoader.js"></script>

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
						<button type="button" class="btn btn-success">Login</button>
					</a>
				</div>
			</div>
		</div>
	</nav>


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
					if (user == null) {
					%>
						<a href="access">
						<button type="button" class="btn btn-success">Login</button> <%
 					} else {
 					%> <a href="userpage">
						<button type="button" class="btn btn-success"><%=user.getName()%></button>
					<%}%>
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
				Un modo facile e veloce per fittare un'auto.<br> Scegli tra pi�
				di 10 unici modelli, dall'auto per tutti i giorni, all'auto pi�
				sportiva ed estroversa.<br> Puoi trovare l'auto perfetta per
				te. Vedi cosa abbiamo da offrirti.
			</p>
		</div>
		<div id="top-image" class="carousel slide" data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="immagini/sfondooo.svg" class="d-block w-100"
						alt="wallpaper">
				</div>
			</div>
		</div>
	</div>
	<!--Search-section-->
	<div class="container" id="container1">
		<div class="row text-center" id="row1">
			<h2>Effettua la ricerca e verifica la disponibilit�</h2>
		</div>
		<form class="search-bar">
			<div class="row justify-content-center" id="row2">
				<div class="col-md-3">
					<label>Localit�</label> <select class="form-select"
						aria-label="Default select example">
						<option selected>Localit�</option>
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
		<label style="color: white; padding-left: 5px;">*Si consiglia
			di prenotare la vettura 48h prima</label>
	</div>

	<!--Card-carousel-->
	<div class="container-fluid">
		<div class="row text-center" id="row3">
			<h2>Il nostro parco auto</h2>
			<p>Scegli tra una vasta gamma di modelli a tua disposizione</p>

		</div>
		<div id="carouselExampleControls" class="carousel slide"
			data-bs-ride="carousel">
			<div class="row justify-content-center" id="card-row">
				<div class="col-sm-12">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="container">
								<div class="row justify-content-center riga"></div>
							</div>
						</div>
						<div class="carousel-item">
							<div class="container">
								<div class="row justify-content-center">
									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="immagini/peugeot.svg">
											<h5 class="card-title">Peugeot 208</h5>
											<p class="card-text">Some quick example text the bulk of
												the card's content.</p>
											<a href="#" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="immagini/opel.svg">
											<h5 class="card-title">Opel Grandland</h5>
											<p class="card-text">Some quick example text the bulk of
												the card's content.</p>
											<a href="#" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="immagini/polo.svg">
											<h5 class="card-title">Volswagen Polo</h5>
											<p class="card-text">Some quick example text the bulk of
												the card's content.</p>
											<a href="#" class="btn btn-success">Dettagli</a>
										</div>
									</div>

									<div class="col-sd-4 col-md-3">
										<div class="card card-body">
											<img class="img-fluid" src="immagini/skoda.svg">
											<h5 class="card-title">Skoda Octavia</h5>
											<p class="card-text">Some quick example text the bulk of
												the card's content.</p>
											<a href="#" class="btn btn-success">Dettagli</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<!--Footer-->
	<div class="footer-div">
		<footer class="bg-dark text-center text-lg-start" id="footer">

			<div class="container p-4">

				<div class="row">

					<div class="col-lg-6 col-md-12 mb-4 mb-md-0">
						<h5 class="text-uppercase" style="color: #00bf85;">Carent -
							Noleggio Veicoli</h5>

						<p>Lorem ipsum dolor sit amet consectetur, adipisicing elit.
							Iste atque ea quis molestias. Fugiat pariatur maxime quis culpa
							corporis vitae repudiandae aliquam voluptatem veniam, est atque
							cumque eum delectus sint!</p>

					</div>



					<div class="col-lg-3 col-md-6 mb-4 mb-md-0">
						<h5 class="text-uppercase mb-0" style="color: #00bf85;">Autori
							Sito</h5>

						<ul class="list-unstyled">
							<li><a href="#!">Carmine Fabbri</a></li>
							<li><a href="#!">Dario Trinchese</a></li>

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