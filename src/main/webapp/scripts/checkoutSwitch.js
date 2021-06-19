$(document).ready(
    ()=>{
        $("#checkout-butt").click(
            ()=> {
                alert("Ma ciao");
                $.ajax (
                    {
                        url: "settings",
                        type: "POST",
                        data: {
                            changetype: "checkout"
                        },
                        success: function (data) {
                            alert(data);
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