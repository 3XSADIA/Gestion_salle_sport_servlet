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
<body><section class="container">
    <header>Edit client</header>
    <form class="form" method="post" action="Clients">
        <input type="hidden" name="id" value="<c:out value=" ${client.id }" />"/>
        <div class="input-box">
            <label>Nom</label>
            <input type="text" name="nom" placeholder="Enter le nom" required value="<c:out value=" ${client.nom}" />"/>
        </div>
        <div class="input-box">
            <label>Prenom</label>
            <input type="text" name="prenom" placeholder="Enter le prenom" required value="<c:out value=" ${client.prenom}" />"/>
        </div>
        <div class="column">
            <div class="input-box">
                <label>NumTelephone</label>
                <input type="text" name="NumTelephone" placeholder="Enter numero de telephone" required value="<c:out value=" ${client.numTelephone}" />"/>
            </div>
            <div class="input-box">
                <label>Date inscription</label>
                <input type="date" name="dateInscription" placeholder="Enter la date d'inscription" required value="<c:out value='${client.dateInscription}' />">
            </div>
        </div>
       <label>Cours</label>
        
      <div class="select-box">
            <select name="Cours">
                <option value="" selected disabled>Select a course</option>
                <c:forEach var="course" items="${courses}">
                    <option value="${course}" ${course == client.cours ? 'selected' : ''}>${course}</option>
                </c:forEach>
            </select>
        </div>
        <button>Submit</button>
    </form>
</section>

</body>

</html>
</html>