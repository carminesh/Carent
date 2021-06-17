$(document).ready(
    () => {
        $("#left-section").toggle();

        $("#slider-icon").click (
            () => {
                if(!$("#left-section").is(":visible")) {
                    $("#slider-icon").removeClass("fa-arrow-alt-circle-right");
                    $("#slider-icon").addClass("fa-arrow-alt-circle-left");
                    $("#slider").css("left", "20%");
                    $("#left-section").fadeIn(0.4);
                } else {
                    $("#slider-icon").removeClass("fa-arrow-alt-circle-left");
                    $("#slider-icon").addClass("fa-arrow-alt-circle-right");
                    $("#slider").css("left", "0px");
                    $("#left-section").fadeOut(0.4);
                }
            }
        );
    }
)