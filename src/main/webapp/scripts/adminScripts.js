$(document).ready (
    () => {
        $("#delete-car-button").click (
            () => {
                var targaRegex = /^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$/
                if ($("#deleted-car").val().match(targaRegex)) {

                    $.ajax (
                        {
                            url: "action",
                            type: "POST",
                            data: {
                                actiontype: "removecar",
                                plate: $("#deleted-car").val()
                            },
                            success: function (data) {
                                $("#result-car-removal-div").html(createOutcome(data, "success", "check-square"));
                                setTimeout(() => $("#result-car-removal-div").html(""), 1500);
                                load("loadcars","#table-container2");
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                                $("#result-car-removal-div").html(createOutcome(xhr.responseText, "unsuccess", "bomb"));
                                setTimeout(() => $("#result-car-removal-div").html(""), 1500);
                            }
                        }
                    )
                } else {
                    $("#result-car-removal-div").html(createOutcome("Operazione non riuscita", "unsuccess", "bomb"));
                    setTimeout(() => $("#result-car-removal-div").html(""), 1500);
                }
            }
        );

        $("#delete-user-button").click (
            () => {
                var emailregex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                if ($("#deleted-user").val().match(emailregex)) {


                    $.ajax (
                        {
                            url: "action",
                            type: "POST",
                            data : {
                                actiontype: "removeuser",
                                email: $("#deleted-user").val()
                            },
                            success: function (data) {
                                $("#result-user-div").html(createOutcome(data, "success", "check-square"))
                                setTimeout(() => $("#result-user-div").html(""), 1500);
                                load("loadusers","#table-container1");
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                                $("#result-user-div").html(createOutcome(xhr.responseText, "unsuccess", "bomb"))
                                setTimeout(() => $("#result-user-div").html(""), 1500);
                            }
                        }
                    )

                } else {
                    $("#result-user-div").html(createOutcome("Email non valida", "unsuccess", "bomb"))
                    setTimeout(() => $("#result-user-div").html(""), 1500);
                }
            }
        );

        $("#delete-rent-button").click (
            () => {
                var rentRegex = /^[0-9]+$/
                if ($("#deleted-rent").val().match(rentRegex)) {
                    $.ajax(
                        {
                            url: "action",
                            type: "POST",
                            data: {
                                actiontype: "removerent",
                                rentCode: $("#deleted-rent").val()
                            },
                            success: function (data) {
                                $("#result-delete-rent-image-div").html(createOutcome(data, "success", "check-square"))
                                setTimeout(() => $("#result-delete-rent-image-div").html(""), 1500);
                                load("loadrents","#table-container3");
                            },
                            error: function (xhr, ajaxOptions, thrownError) {
                                $("#result-delete-rent-image-div").html(createOutcome(xhr.responseText, "unsuccess", "bomb"))
                                setTimeout(() => $("#result-delete-rent-image-div").html(""), 1500);
                            }

                        }
                    )
                } else {
                    $("#result-delete-rent-image-div").html(createOutcome("Codice non valido", "unsuccess", "bomb"))
                    setTimeout(() => $("#result-delete-rent-image-div").html(""), 1500);
                }
            }
        )

        $("#add-car-button").click (
            () => {
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
                                            if ($("#add-car-price").val().match(numRegex)) {
                                                $.ajax(
                                                    {
                                                        url: "action",
                                                        type: "POST",
                                                        data: {
                                                            actiontype: "addcar",
                                                            plate: $("#add-car-plate").val(),
                                                            brand: $("#add-car-brand").val(),
                                                            model: $("#add-car-model").val(),
                                                            power: $("#add-car-power").val(),
                                                            fuel: $("#add-car-fuel").val(),
                                                            year: $("#add-car-year").val(),
                                                            mileage: $("#add-car-mileage").val(),
                                                            price: $("#add-car-price").val()
                                                        },
                                                        success: function(data) {
                                                            $("#result-car-add-div").html(createOutcome(data,"success", "check-square"))
                                                            setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                                            load("loadcars","#table-container2");
                                                        },
                                                        error: function (xhr, ajaxOptions, thrownError) {
                                                            $("#result-car-add-div").html(createOutcome(xhr.responseText,"unsuccess", "bomb"))
                                                            setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                                        }
                                                    }
                                                )
                                            } else {
                                                $("#result-car-add-div").html(createOutcome("Prezzo non valido","unsuccess", "bomb"))
                                                setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                            }
                                        } else {
                                            $("#result-car-add-div").html(createOutcome("Chilometraggio non valido","unsuccess", "bomb"))
                                            setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                        }
                                    } else {
                                        $("#result-car-add-div").html(createOutcome("Anno non valido","unsuccess", "bomb"))
                                        setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                    }
                                } else {
                                    $("#result-car-add-div").html(createOutcome("Alimentazione non valida","unsuccess", "bomb"))
                                    setTimeout(() => $("#result-car-add-div").html(""), 1500);
                                }
                            } else {
                                $("#result-car-add-div").html(createOutcome("Potenza non valida","unsuccess", "bomb"))
                                setTimeout(() => $("#result-car-add-div").html(""), 1500);
                            }
                        } else {
                            $("#result-car-add-div").html(createOutcome("Modello non valido","unsuccess", "bomb"))
                            setTimeout(() => $("#result-car-add-div").html(""), 1500);
                        }
                    } else {
                        $("#result-car-add-div").html(createOutcome("Marca non valida","unsuccess", "bomb"))
                        setTimeout(() => $("#result-car-add-div").html(""), 1500);
                    }
                } else {
                    $("#result-car-add-div").html(createOutcome("Targa non valida","unsuccess", "bomb"))
                    setTimeout(() => $("#result-car-add-div").html(""), 1500);
                }
            }
        )

        load("loadusers","#table-container1");
        load("loadcars","#table-container2");
        load("loadrents","#table-container3");
    }
);

function createOutcome(data, type, icon) {
    return "<div id=\"alert-"+type+"\">\n" +
        "    <div>\n" +
        "        <i class=\"fas fa-"+icon+"\"></i>\n" +
        "        <label id=\"result-label\">"+data+"</label>\n" +
        "    </div>\n" +
        "</div>";
}

function targaValida() {
    var targaRegex = /^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$/
    if ($("#add-plate").val().match(targaRegex)) {
        $("#result-add-car-image-div").load("successfulOperationComponent.jsp");
        setTimeout(() => $("#result-add-car-image-div").html(""), 1500);
        return true;
    } else {
        $("#result-add-car-image-div").load("unsuccessfulOperationComponent.jsp");
        setTimeout(() => $("#result-add-car-image-div").html(""), 1500);
        return false;
    }
}

function load (loaditem,destinationDiv) {
    $.ajax  (
        {
            url: "action",
            type: "POST",
            data: {
                actiontype: loaditem
            },
            success: function(data) {
                $(destinationDiv).html(data);
            },
            error: function () {
            }
        }
    )
}

