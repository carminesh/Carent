<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.CarBean" %>
<%@ page import="carent.utils.Utility" %><%
    Utility.print("Sono entrato nella component");
    Collection<?> list = (Collection<?>) request.getAttribute("carList");
    Utility.print("lista caricata");
    Iterator<?> it = list.iterator();
    Utility.print("iteratore creato");
    CarBean bean;
%>
<table>
    <thead>
        <tr>
            <th> Targa </th>
            <th> Marca </th>
            <th> Modello </th>
            <th> Alimentazione </th>
            <th> Chilometraggio </th>
            <th> Potenza </th>
            <th> Anno Immatricolazione </th>
        </tr>
    </thead>
    <%
        while (it.hasNext()) {
            bean = (CarBean) it.next();
    %>
    <tr>
        <td> <%=bean.getTarga()%> </td>
        <td> <%=bean.getMarca()%> </td>
        <td> <%=bean.getModello()%> </td>
        <td> <%=bean.getAlimentazione()%> </td>
        <td> <%=bean.getChilometraggio()%> </td>
        <td> <%=bean.getPotenza()%> </td>
        <td> <%=bean.getAnnoImmatricolazione()%> </td>
    </tr>
    <%
        }
    %>
</table>

