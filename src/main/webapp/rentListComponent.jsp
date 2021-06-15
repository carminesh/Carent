<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.RentBean" %>
<%@ page import="carent.utils.Utility" %><%
    Utility.print("Sono entrato nella component");
    Collection<?> list = (Collection<?>) request.getAttribute("rentList");
    Utility.print("lista caricata");
    Iterator<?> it = list.iterator();
    Utility.print("iteratore creato");
    RentBean bean;
%>
<table>
    <thead>
        <tr>
            <th> Codice Noleggio </th>
            <th> Codice Utente </th>
            <th> Targa </th>
            <th> Data inizio </th>
            <th> Data fine </th>
            <th> Prezzo </th>
        </tr>
    </thead>
    <%
        while (it.hasNext()) {
            bean = (RentBean) it.next();
    %>
    <tr>
        <td> <%=bean.getRentCode()%></td>
        <td> <%=bean.getUserCode()%> </td>
        <td> <%=bean.getTarga()%> </td>
        <td> <%=bean.getDaData()%> </td>
        <td> <%=bean.getaData()%> </td>
        <td> <%=bean.getPrezzo()%> </td>
    </tr>
    <%
        }
    %>
</table>
