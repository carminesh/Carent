$(document).ready(
	() => {
		$("#emailchangebutton").click(
			() => {

				var emailregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
				if ($("#newemail").val().match(emailregex)) {

					$.ajax ({
						url: "settings",
						type: "POST",
						data: {
							changetype: "email",
							newemail: $("#newemail").val(),
							oldemail: $("#currentemail").text()
						},
						success: function (data) {
							$("#currentemail").html(data);
							$("#result-change-email-div").html(createOutcome("Operazione eseguita", "success", "check-square"));
							setTimeout(() => $("#result-change-email-div").html(""), 1500);
						},
						error: function (xhr, ajaxOptions, thrownError) {
							$("#result-change-email-div").html(createOutcome(xhr.responseText, "unsuccess", "bomb"));
							setTimeout(() => $("#result-change-email-div").html(""), 1500);
						}
				
					})
				} else {
					$("#result-change-email-div").html(createOutcome("Email non valida", "unsuccess", "bomb"));
					setTimeout(() => $("#result-change-email-div").html(""), 1500);
				}
			}
		);

		$("#passchangebutton").click(
			() => {
				var passwordregex = /^[0-9a-zA-Z]+$/
				if ($("#newpass").val().match(passwordregex) && $("#oldpass").val().match(passwordregex)) {
					$.ajax({
						url: "settings",
						type: "POST",
						data: {
							changetype: "pass",
							currentemail: $("#currentemail").text(),
							oldpass: $("#oldpass").val(),
							newpass: $("#newpass").val()
						},
						success: function (data) { //"data" è la risposta della servlet
							$("#result-change-psswd-div").html(createOutcome(data, "success", "check-square"));
							setTimeout(() => $("#result-change-psswd-div").html(""), 1500);
						},
						error: function (xhr, ajaxOptions, thrownError) {
							$("#result-change-psswd-div").html(createOutcome(xhr.responseText, "unsuccess", "bomb"));
							setTimeout(() => $("#result-change-psswd-div").html(""), 1500);
						},
					})
				} else {
					$("#result-change-psswd-div").html(createOutcome("Caratteri non validi", "unsuccess", "bomb"));
					setTimeout(() => $("#result-change-psswd-div").html(""), 1500);
				}
			}
		);

		$.ajax(
			{
				url: "settings",
				type: "POST",
				data: {
					changetype: "rentload",
					email: $("#currentemail").text()
				},
				success: function (data) {
					$("#early-rents-list").html(data)
				},
				error: function () {
					alert("Errore nel caricamento degli ultimi noleggi");
				}
			}
		)
	}
)

function createOutcome(data, type, icon) {
	return "<div id=\"alert-"+type+"\">\n" +
		"    <div>\n" +
		"        <i id=\"icon\" class=\"fas fa-"+icon+"\"></i>\n" +
		"        <label id=\"result-label\">"+data+"</label>\n" +
		"    </div>\n" +
		"</div>";
}
