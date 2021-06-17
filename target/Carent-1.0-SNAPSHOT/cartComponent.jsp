<%@ page import="carent.model.CartBean" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.CartItemBean" %>
<%@ page import="carent.utils.Utility" %>
<%
    Utility.print("Sono entrato in cartComponent.jsp");
    CartBean cartBean = (CartBean) session.getAttribute("cart");
    if (!cartBean.isEmpty()) {
%>
<h2 id="cart-label">Carrello <i class="fas fa-shopping-cart"></i></h2>
<h3 id="total-label">Totale: <%=cartBean.getTotal()%> <i class="fas fa-euro-sign"></i></h3>
<div id="buy-all">
    <form method="POST" action="<%=application.getContextPath() + "/user/checkout"%>">
        <input type="submit" value="Noleggia" id="checkout-button">
    </form>
</div>
<%
    Collection<?> cart = (Collection<?>) cartBean.getCart();
    Iterator<?> it = cart.iterator();
    CartItemBean itemBean;
    while (it.hasNext()) {
        itemBean=(CartItemBean) it.next();
%>
<div class="card" style="width: 18rem;">
    <div class="card-body">
        <h4 class="card-title"><b><%=itemBean.getAuto().getMarca() + " " + itemBean.getAuto().getModello()%></b></h4>
        <p class="card-text">
            <i class="fas fa-gas-pump"></i> <b><%=itemBean.getAuto().getAlimentazione()%></b> &nbsp&nbsp
            <i class="fas fa-car"></i> <b><%=itemBean.getAuto().getPotenza()%> kWh</b> &nbsp&nbsp
            <i class="fas fa-calendar-alt"></i> <b><%=itemBean.getAuto().getAnnoImmatricolazione()%></b>
            <i class="fas fa-tachometer-alt"></i> <b><%=itemBean.getAuto().getChilometraggio()%> Km</b>
        </p>
        <div class="price-section">
            <div class="price">
                <h5><i class="fas fa-euro-sign"></i> <b><%=itemBean.getPrezzoTotale()%></b></h5>
                <div class="delete-from-cart" data-targa="<%=itemBean.getAuto().getTarga()%>"><button>Cancella</button></div>
            </div>
        </div>
    </div>
</div>
<%
    }
} else {
%>
<h2 id="cart-label">Carrello vuoto</h2>
<%
    }
%>
<script>
    $(".delete-from-cart").click (
        function () {
            alert("Sono entrato nello script per DELETE");
            alert($(this).attr("data-targa"));
            $.ajax(
                {
                    url: "user/settings",
                    type: "POST",
                    data: {
                        changetype: "removeFromCart",
                        plate: $(this).attr("data-targa")
                    },
                    success: function (data) {
                        alert("Servlet addToCart terminata con successo");
                        $("#shop-section").html(data);
                    },
                    error: function (xhr,ajaxOptions, thrownError) {
                        alert(xhr.responseText);
                    }
                }
            )
        }
    )
</script>
