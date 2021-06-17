$(document).ready(
    () => {
        $("#left-section").toggle();

        $("#slider-icon").click (
            () => {
                if(!$("#left-section").is(":visible")) {
                    $("#slider-icon").removeClass("fa-arrow-alt-circle-right");
                    $("#slider-icon").addClass("fa-arrow-alt-circle-left");
                    $("#left-section").fadeIn(0.4);
                } else {
                    $("#slider-icon").removeClass("fa-arrow-alt-circle-left");
                    $("#slider-icon").addClass("fa-arrow-alt-circle-right");
                    $("#left-section").fadeOut(0.4);
                }
            }
        );
    }
)