$(document).ready (
    () => {
        $("#form-container-delete-car").toggle();
        $("#form-container-delete-user").toggle();
        $("#form-container-add-car").toggle();
        $("#form-container-add-car-image").toggle();
        $("#table-container1").toggle();
        $("#table-container2").toggle();
        $("#table-container3").toggle();
        $("#form-container-delete-rent").toggle();

        $("#delete-car").click (
            () => {
                if(!$("#form-container-delete-car").is(":visible"))
                    $("#form-container-delete-car").fadeIn();
                else
                    $("#form-container-delete-car").fadeOut(0.4);
            }
        );

        $("#delete-user").click (
            () => {
                if(!$("#form-container-delete-user").is(":visible"))
                    $("#form-container-delete-user").fadeIn();
                else
                    $("#form-container-delete-user").fadeOut(0.4);
            }
        );

        $("#add-car").click (
            () => {
                if(!$("#form-container-add-car").is(":visible"))
                    $("#form-container-add-car").fadeIn();
                else
                    $("#form-container-add-car").fadeOut(0.4);
            }
        );

        $("#add-car-image").click (
            () => {
                if(!$("#form-container-add-car-image").is(":visible"))
                    $("#form-container-add-car-image").fadeIn();
                else
                    $("#form-container-add-car-image").fadeOut(0.4);
            }
        );

        $("#user-list-button").click (
            () => {
                if (!$("#table-container1").is(":visible")) {
                    $("#table-container2").fadeOut(0.4);
                    $("#table-container3").fadeOut(0.4);
                    $("#table-container1").fadeIn();
                } else
                    $("#table-container1").fadeOut(0.4);
            }
        );

        $("#car-list-button").click (
            () => {
                if (!$("#table-container2").is(":visible")) {
                    $("#table-container1").fadeOut(0.4);
                    $("#table-container3").fadeOut(0.4);
                    $("#table-container2").fadeIn();
                } else
                    $("#table-container2").fadeOut(0.4);
            }
        );

        $("#rent-list-button").click (
            () => {
                if (!$("#table-container3").is(":visible")) {
                    $("#table-container1").fadeOut(0.4);
                    $("#table-container2").fadeOut(0.4);
                    $("#table-container3").fadeIn();
                } else
                    $("#table-container3").fadeOut(0.4);
            }
        );

        $("#rent-delete-button").click (
            () => {
                if (!$("#form-container-delete-rent").is(":visible")) {
                    $("#form-container-delete-rent").fadeIn();
                } else
                    $("#form-container-delete-rent").fadeOut(0.4);
            }
        );
    }
)