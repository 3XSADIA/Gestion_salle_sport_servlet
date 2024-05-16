<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="<%= request.getContextPath() %>/template/style.css"
	rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

</head>
<body>
	<h1>CSS-Only Floating Labels</h1>
	<div class="container">
		
		<form method="post" class="form" action="Equipement">
		<input type="hidden" name="id" value="<c:out value="${equipement.id }"/>">
			<label for="nom" class="form__label">nom</label>
			 <input type="text"	placeholder="nom" class="form__input" name="nom" value="<c:out value="${equipement.nom }"/>"/> <br> 
			 <label	for="quantite" class="form__label">quantite</label>
			  <input type="number" placeholder="quantite" class="form__input" name="quantite" value="<c:out value="${equipement.quantite }"/>"/> 
				<input type="submit" value="envoyer">
		</form>
	</div>

</body>
</html>