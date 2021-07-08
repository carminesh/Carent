$(document).ready (
    () => {
        $("#search-button").click (
            () => {
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
                $.ajax (
                    {
                        url: "user/action",
                        type: "POST",
                        data: {
                            changetype: "addToCart",
                            plate: $(this).attr("data-targa"),
                            startdate: $(this).attr("data-start"),
                            finishdate: $(this).attr("data-finish"),
                            place: $(this).attr("data-luogo")
                        },
                        success: function (data) {
                            $("#shop-section").html(data);
                        },
                        error: function (xhr,ajaxOptions, thrownError) {
                            alert(xhr.responseText);
                        }
                    }
                )
            }
        )

        $(".delete-from-cart").click (
            function () {
                $.ajax(
                    {
                        url: "user/action",
                        type: "POST",
                        data: {
                            changetype: "removeFromCart",
                            plate: $(this).attr("data-targa")
                        },
                        success: function (data) {
                            $("#shop-section").html(data);
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
