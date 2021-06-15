<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="carent.model.UserBean" %>
<%@ page import="carent.utils.Utility" %><%
    Utility.print("Sono entrato nella component");
    Collection<?> list = (Collection<?>) request.getAttribute("userList");
    Utility.print("lista caricata");
    Iterator<?> it = list.iterator();
    Utility.print("iteratore creato");
    UserBean bean;
%>
<table>
    <thead>
        <tr>
            <th> Codice Utente </th>
            <th> Email </th>
            <th> Ruolo </th>
            <th> Nome </th>
            <th> Cognome </th>
            <th> Telefono </th>
        </tr>
    </thead>

    <%
    while (it.hasNext()) {
        bean = (UserBean) it.next();
    %>
            <tr>
                <td> <%=bean.getUserCode()%> </td>
                <td> <%=bean.getEmail()%> </td>
                <td> <%=bean.getRole()%> </td>
                <td> <%=bean.getName()%> </td>
                <td> <%=bean.getSurname()%> </td>
                <td> <%=bean.getPhone()%> </td>
            </tr>
    <%
    }
    %>
        </table>
