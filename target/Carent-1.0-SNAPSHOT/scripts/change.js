$(document).ready(
	() => {
		$("#emailchangebutton").click(
			() => {
				alert("Hai inserito "+$("#newemail").val());
				var emailregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
				if ($("#newemail").val().match(emailregex)) {
					$.ajax ({
						"url": "change",
						"type": "POST",
						"data": {
							"changetype": "email",
							"newemail": $("#newemail").val(),
							"oldemail": $("#currentemail").text()
						},
						"success": function (data) {
							$("#currentemail").html(data);
						},
						"error": function () {
							alert("Impossibile modificare la email");
						}
				
					})
				} else {
					alert("Inserisci una nuova email valida!");
				}
			}
		);

		$("#passchangebutton").click(
			() => {
				var passwordregex = /^[0-9a-zA-Z]+$/
				if ($("#newpass").val().match(passwordregex) && $("#oldpass").val().match(passwordregex)) {
					$.ajax({
						"url": "change",
						"type": "POST",
						"data": {
							"changetype": "pass",
							"currentemail": $("#currentemail").text(),
							"oldpass": $("#oldpass").val(),
							"newpass": $("#newpass").val()
						},
						"success": function (data) { //"data" è la risposta della servlet
							$("#successopass").html(data);
						}
					})
				} else {
					alert("Inserisci una password alfanumerica!");
				}
			}
		);
	}
)
