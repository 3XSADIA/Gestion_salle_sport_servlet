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
		
		<form method="post" class="form" action="Client">
		<input type="hidden" name="id" value="<c:out value="${client.id }"/>">
			<label for="nom" class="form__label">nom</label>
			 <input type="text"	placeholder="nom" class="form__input" name="nom" value="<c:out value="${client.nom }"/>"/> <br> 
			 <label	for="prenom" class="form__label">prenom</label>
			  <input type="text" placeholder="prenom" class="form__input" name="prenom" value="<c:out value="${client.prenom }"/>"/> 
			  <label for="dateInscription" class="form__label">dateInscription</label>
			 <input type="text"	placeholder="dateInscription" class="form__input" name="dateInscription" value="<c:out value="${client.dateInscription }"/>"/> <br> 
			 <label	for="NumTelephone" class="form__label">NumTelephone</label>
			  <input type="text" placeholder="NumTelephone" class="form__input" name="NumTelephone" value="<c:out value="${client.NumTelephone }"/>"/> 
			  <label for="Cours" class="form__label">Cours</label>
			  <input type="text" placeholder="Cours" class="form__input" name="Cours" value="<c:out value="${client.Cours }"/>"/> 
				<input type="submit" value="envoyer">
		</form>
	</div>

</body>
</html>