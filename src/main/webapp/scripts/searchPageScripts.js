$(document).ready (
    () => {
        $("#search-button").click (
            () => {
                alert($("#pick-up-place").val());
                alert($("#pickup-date").val());
                alert($("#release-date").val());
                $.ajax(
                    {
                        url: "search",
                        type: "POST",
                        data: {
                            actiontype: "searchCars",
                            pickupplace: $("#pick-up-place").val(),
                            startdate: $("#pickup-date").val(),
                            finishdate: $("#release-date").val()
                        },
                        success: function (data) {
                            $("#car-list-div").html(data);
                        },
                        error: function (xhr,ajaxOptions, thrownError) {
                            alert(xhr.responseText);
                        }
                    }
                );
            }
        );

        $(".buy-button").click (
            function () {
                alert($(this).attr("data-targa"));
                alert($(this).attr("data-start"));
                alert($(this).attr("data-finish"));
                alert($(this).attr("data-luogo"));
                $.ajax (
                    {
                        url: "user/settings",
                        type: "POST",
                        data: {
                            changetype: "addToCart",
                            plate: $(this).attr("data-targa"),
                            startdate: $(this).attr("data-start"),
                            finishdate: $(this).attr("data-finish"),
                            place: $(this).attr("data-luogo")
                        },
                        success: function (data) {
                            alert("Servlet addToCart terminata con successo");
                        },
                        error: function (xhr,ajaxOptions, thrownError) {
                            alert(xhr.responseText);
                        }
                    }
                )
            }
        )
    }
)
