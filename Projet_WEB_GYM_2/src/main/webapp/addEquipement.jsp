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
    <header>Add equipements</header>
    <form method="post" class="form" action="Equipement">

      <input type="hidden" name="id" value="<c:out value=" ${equipement.id }" />">

      <div class="input-box">
        <label>Nom</label>
        <input type="text" placeholder="Enter le nom " required name="nom" value="<c:out value=" ${equipement.nom
          }" />"/>
      </div>
      <div class="input-box">
        <label>Quantite</label>
        <input type="text" placeholder="Enter le nom " required name="quantite" value="<c:out value="
          ${equipement.quantite }" />"/>
      </div>
      <button>Submit</button>
    </form>
  </section>
</body>
</html>