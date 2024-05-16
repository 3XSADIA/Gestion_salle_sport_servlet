<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/template/css/style.css"
	rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
  <section class="container">
    <header>Add paiement</header>
    <form method="post" class="form" action="Paiement">
      <input type="hidden" name="id" value="<c:out value=" ${paiement.id }" />">
      <div class="column">
        <div class="input-box">
          <label>Date paiement</label>
          <input type="date" placeholder="Enter la date de paiement" required />
        </div>
        <div class="input-box">
          <label>Mois payé</label>
          <input type="text" placeholder="Enter le mois payé" required name="moispaye" value="<c:out value="
            ${paiement.moispaye }" />"/>
        </div>
      </div>
      <div class="input-box">
        <label>Montant</label>
        <input type="number" placeholder="Enter le montant" required name="montantpaye" value="<c:out value="
          ${paiement.montantpaye }" />"/>
      </div>
      <div class="input-box address">
        <label>Id_client</label>

        <div class="select-box" name="id_client" value="<c:out value=" ${paiement.id_client }" />"/>
        <select>
          <option hidden>Entrer id_client</option>
          <option>America</option>
        </select>
      </div>

      </div>

      <button>Submit</button>
    </form>
  </section>
</body>
</html>