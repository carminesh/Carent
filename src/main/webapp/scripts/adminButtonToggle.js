$(document).ready (
    () => {
        $("#form-container-delete-car").toggle();
        $("#form-container-delete-user").toggle();
        $("#form-container-add-car").toggle();
        $("#form-container-add-car-image").toggle();

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
    }
)