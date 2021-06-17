$(document).ready(
    ()=>{
        $("#checkout-butt").click(
            ()=> $(".container").load("checkoutComponent.jsp"))
    }
);