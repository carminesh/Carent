$(document).ready(
    ()=>{
        $("#checkout-butt").click(
            ()=> {
                $.ajax (
                    {
                        url: "action",
                        type: "POST",
                        data: {
                            changetype: "checkout"
                        },
                        success: function (data) {
                            $(".container").load("/Carent_war_exploded/successCheckoutComponent.jsp")
                        },
                        error: function (xhr,ajaxOptions,thrownError) {
                            alert("Errore AJAX");
                            alert(xhr.responseText);
                        }
                    }
                );
            }
        )
    }
);