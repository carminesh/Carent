<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.RentBean" %>
<%@ page import="carent.utils.Utility" %><%
    Collection<?> lista = (Collection<?>) request.getAttribute("earlyRents");
    Iterator<?> it = lista.iterator();
    int cont = 0;
    while (it.hasNext() || cont<3) {
        Utility.print("sono entrato qui");
        RentBean bean = null;
        if (it.hasNext()) {
            Utility.print("it ha un next");
            bean = (RentBean) it.next();
            Utility.print(bean.toString());
        } else {
            Utility.print("it non ha un next");
        }
%>
<li class="list-group-item d-flex justify-content-between align-items-start">
    <div class="ms-2 me-auto">
        <div class="fw-bold"><%
            if (bean!=null) {
                Utility.print("Settando la targa");
        %>
            <%=bean.getTarga()%>
            <% } else {
                Utility.print("Nessuna targa...");
            %>
            Nessun noleggio recente
            <%
                }
            %></div>
        <%
            if (bean!=null) {
                Utility.print("Settando le date");
        %>
        dal <%=bean.getDaData()%> al <%=bean.getaData()%>
        <%
        } else {
            Utility.print("Nessuna data...");
        %>
        Nessun noleggio recente
        <%
            }
        %>
    </div>
    <span class="badge bg-primary rounded-pill">
                <%
                    if (bean!=null) {
                        Utility.print("Settando il prezzo");
                %>
                <%=bean.getPrezzo()%> euro
                <%
                    } else {
                        Utility.print("Nessun prezzo");
                    }
                %>
            </span>
</li>
<%
        ++cont;
    }
%>
