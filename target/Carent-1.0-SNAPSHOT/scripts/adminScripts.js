$(document).ready (
    () => {
        $("#delete-car-button").click (
            () => {
                alert("Sono entrato nello script Cancella Veicolo");
                var targaRegex = /^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$/
                if ($("#deleted-car").val().match(targaRegex)) {
                    alert("La targa è corretta!!");
                    $.ajax (
                        {
                                url: "adminaction",
                                type: "POST",
                                data: {
                                    actiontype: "removecar",
                                    plate: $("#deleted-car").val()
                                },
                                success: function (data) {
                                    $("#esito-div").html(data)
                                },
                                error: function () {
                                    alert("Errore nella chiamata AJAX");
                                    $("#esito-div").html("Impossibile rimuovere il veicolo");
                            }
                        }
                    )
                } else {
                    alert("La targa non è corretta!!");
                }
            }
        );

        $("#delete-user-button").click (
            () => {
                alert("Sono entrato nello script Cancella utente");
                var emailregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                if ($("#deleted-user").val().match(emailregex)) {
                    $.ajax (
                        {
                            url: "adminaction",
                            type: "POST",
                            data : {
                                actiontype: "removeuser",
                                email: $("#deleted-user").val()
                            },
                            success: function (data) {
                                alert("Servlet terminata!");
                                $("#esito-div").html(data);
                            },
                            error: function () {
                                alert("Errore nella chiamata AJAX");
                                $("#esito-div").html("Impossibile eliminare l'utente");
                            }
                        }
                    )

                } else {
                    alert("Indirizzo email non valido!!");
                }
            }
        );

        $("#add-car-button").click (
            () => {
                alert("Sono entrato nello script Aggiungi veicolo");
                var targaRegex = /^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$/
                var textAndNumbersRegex = /^[a-zA-Z0-9 ]+$/
                var textRegex = /^[a-zA-Z]+$/
                var numRegex = /^[0-9]+$/
                var yearRegex = /^[0-9]{4}$/
                if ($("#add-car-plate").val().match(targaRegex)) {
                    if ($("#add-car-brand").val().match(textRegex)) {
                        if ($("#add-car-model").val().match(textAndNumbersRegex)) {
                            if ($("#add-car-power").val().match(numRegex)) {
                                if ($("#add-car-fuel").val().match(textRegex)) {
                                    if($("#add-car-year").val().match(yearRegex)) {
                                        if ($("#add-car-mileage").val().match(numRegex)) {
                                            $.ajax(
                                                {
                                                    url: "adminaction",
                                                    type: "POST",
                                                    data: {
                                                        actiontype: "addcar",
                                                        plate: $("#add-car-plate").val(),
                                                        brand: $("#add-car-plate").val(),
                                                        model: $("#add-car-model").val(),
                                                        power: $("#add-car-power").val(),
                                                        fuel: $("#add-car-fuel").val(),
                                                        year: $("#add-car-year").val(),
                                                        mileage: $("#add-car-mileage").val()
                                                    },
                                                    success: function(data) {
                                                        alert("Servlet terminata");
                                                        $("#esito-div").html(data);
                                                    },
                                                    error: function () {
                                                        alert("Errore nella chiamata AJAX...");
                                                        $("#esito-div").html("Impossibile aggiungere il veicolo...");
                                                    }
                                                }
                                            )
                                        } else {
                                            alert("Chilometraggio non valido!!");
                                        }
                                    } else {
                                        alert("Anno non valido!!")
                                    }
                                } else {
                                    alert("Alimentazione non valida!!");
                                }
                            } else {
                                alert("Potenza non valida!!");
                            }
                        } else {
                            alert("Modello non valido!!");
                        }
                    } else {
                        alert("Marca non valida!!");
                    }
                } else {
                    alert("Targa non valida!");
                }
            }
        )
    }
);

function targaValida() {
    alert("Validando la targa...");
    var targaRegex = /^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$/
    if ($("#add-plate").val().match(targaRegex))
        return true;
    else {
        alert("Inserisci una targa valida!!");
        return false;
    }
}