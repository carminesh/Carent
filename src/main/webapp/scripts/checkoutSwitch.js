$(document).ready(
    ()=>{
        $("#checkout-butt").click(
            ()=> $(".container").load("successCheckoutComponent.jsp"))
    }
);