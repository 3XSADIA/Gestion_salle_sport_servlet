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
    <header>Ajouter coach</header>
    <form action="Coach" method="post" class="form">
      <input type="hidden" name="id" value="<c:out value=" ${coach.id }" />">
      <div class="input-box">
        <label>Nom</label>
        <input type="text" placeholder="Enter le nom" name="nom" required value="<c:out value=" ${coach.nom }" />"/>
      </div>
      <div class="input-box">
        <label>Prenom</label>
        <input type="text" placeholder="Enter le prenom" name="prenom" required value="<c:out value=" ${coach.prenom
          }" />"/>
      </div>
      <div class="column">
        <div class="input-box">
          <label>NumTelephone</label>
          <input type="number" name="num_telephone" placeholder="Enter numero de telephone" required
            value="<c:out value=" ${coach.num_telephone }" />"/>
        </div>
      </div>
      <div class="input-box address">
        <label>Cours</label>

        <div class="select-box">
          <select name="Cours" value="<c:out value=" ${coach.cours }" />"/>
          <option hidden="">Cours</option>
          <option>America</option>
          </select>
        </div>
        <div class="input-box">
          <label>Salaire</label>
          <input type="text" placeholder="Enter le salaire" name="Salaire" required value="<c:out value="
            ${coach.salaire }" />"/>
        </div>
      </div>
      <button>Submit</button>
    </form>
  </section>


</body>
</html>