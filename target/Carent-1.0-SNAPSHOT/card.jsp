<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,carent.model.*"%>

<%
	Collection<?> daVisualizzare = (Collection<?>) request.getAttribute("daVisualizzare");
	Iterator<?> it = daVisualizzare.iterator();
	CarBean bean;
	int cont=0;
	while (it.hasNext() && cont<4) {
		bean = (CarBean) it.next();
	%>
		<div class="col-sd-4 col-md-3">
			<div class="card card-body">
    			<img class="img-fluid" src="<%="/CarentNew/immagini/"+bean.getTarga()+".jpg" %>">
        		<h5 class="card-title"><%=bean.getMarca() + " " + bean.getModello() %></h5>
        		<p class="card-text">odio fabbri odio fabbri odio fabbri</p>
        		<a href="#" class="btn btn-success">Dettagli</a>
     		</div>
		</div>
	<%
		++cont;
	}
%>




