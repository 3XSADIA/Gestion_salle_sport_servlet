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
		
		<form method="post" class="form" action="Cours">
		<input type="hidden" name="id" value="<c:out value="${cours.id }"/>">
			<label for="label" class="form__label">label</label>
			 <input type="text"	placeholder="label" class="form__input" name="label" value="<c:out value="${cours.label }"/>"/> <br> 
			 <label	for="equipements" class="form__label">equipements</label>
			  <input type="text" placeholder="equipements" class="form__input" name="equipements" value="<c:out value="${cours.equipements }"/>"/> 
				<input type="submit" value="envoyer">
		</form>
	</div>

</body>
</html>