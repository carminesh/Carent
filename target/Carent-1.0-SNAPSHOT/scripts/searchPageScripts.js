$(document).ready (
    () => {
        $("#search-button").click (
            $.ajax (
                {
                    url: "cars",
                    type: "POST",
                    data : {
                        actiontype: "loadCars",
                        dataInizio: $("#pickup-date").val(),
                        dataFine: $("#release-date").val(),
                        luogo:  $("#luogo").val()
                    },
                    success: function (data) {
                        $("#carList").load(data)
                    },
                    error: function () {
                        alert("Impossibile caricare i veicoli")
                    }
                }
            )
        )

        $(".buy-button").click (
            function () {
                alert("Sono entrato ausdasd");
                alert($(this).attr("data-targa"));
                /*
                Da continuare domani,
                da creare anche il caricamento asincrono di nuove auto
                $.ajax (
                    {

                    }
                )
                */
            }
        )

    }
)
