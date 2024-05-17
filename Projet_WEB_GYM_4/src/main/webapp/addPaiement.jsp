<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/template/css/style.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
  <section class="container">
    <header>Ajouter/Editer paiement</header>
    <form method="post" class="form" action="Paiements">
      <input type="hidden" name="id" value="<c:out value="${paiement.id}" />">
      <div class="column">
        <div class="input-box">
          <label>Date paiement</label>
          <input type="date" placeholder="Enter la date de paiement" required name="date_paiement" value="<c:out value="${paiement.date_paiement}" />"/>
        </div>
        <div class="input-box">
          <label>Mois payé</label>
          <input type="text" placeholder="Enter le mois payé" required name="moispaye" value="<c:out value="${paiement.moispaye}" />"/>
        </div>
      </div>
      <div class="input-box">
        <label>Montant</label>
        <input type="number" placeholder="Enter le montant" required name="montantpaye" value="<c:out value="${paiement.montantpaye}" />"/>
      </div>
      <div class="input-box">
        <label>Client</label>
        <div class="select-box">
          <select name="id_client">
            <option value="" selected disabled>Selectionner un client</option>
            <c:forEach var="client" items="${clients}">
              <option value="${client.id}" ${client.id == paiement.id_client ? 'selected' : ''}>${client.nom}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <br>
      <button type="submit">Submit</button>
    </form>
  </section>
</body>
</html>
