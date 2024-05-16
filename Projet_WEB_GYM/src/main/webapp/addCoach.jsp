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
		
		<form method="post" class="form" action="Coach">
		<input type="hidden" name="id" value="<c:out value="${coach.id }"/>">
			<label for="nom" class="form__label">nom</label>
			 <input type="text"	placeholder="nom" class="form__input" name="nom" value="<c:out value="${coach.nom }"/>"/> <br> 
			 <label	for="quantite" class="form__label">prenom</label>
			  <input type="text" placeholder="prenom" class="form__input" name="prenom" value="<c:out value="${coach.prenom }"/>"/> 
			  <label for="nom" class="form__label">num_telephone</label>
			 <input type="text"	placeholder="num_telephone" class="form__input" name="num_telephone" value="<c:out value="${coach.num_telephone }"/>"/> <br> 
			 <label	for="quantite" class="form__label">Cours</label>
			  <input type="text" placeholder="Cours" class="form__input" name="Cours" value="<c:out value="${coach.Cours }"/>"/> 
			   <label	for="Salaire" class="form__label">Salaire</label>
			  	 <input type="text" placeholder="Salaire" class="form__input" name="Salaire" value="<c:out value="${coach.Salaire }"/>"/> 
			  	
				<input type="submit" value="envoyer">
		</form>
	</div>

</body>
</html>