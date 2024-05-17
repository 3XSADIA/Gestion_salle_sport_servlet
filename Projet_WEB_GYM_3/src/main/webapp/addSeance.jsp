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
    <header>Add seance</header>
    <form method="post" class="form" action="Seance">
      <input type="hidden" name="id" value="<c:out value=" ${seance.id }" />">
         <div class="select-box">
        <select name="id_coach" value="<c:out value=" ${seance.id_coach }" />"/>
          <option hidden>Entrer Id_coach</option>
          <option>America</option>
        </select>
      </div>
      <div class="input-box">
        <label>Horaire</label>
        <input type="text" placeholder="Enter le horaire " required name="horaire" value="<c:out value="
          ${seance.horaire }" />"/>
      </div>
      <div class="select-box" >
        <select name="id_cours" value="<c:out value=" ${seance.id_cours }" />"/>
          <option hidden>Entrer id_cours</option>
          <option>America</option>
        </select>
      </div>
      
     
      <button>Submit</button>
    </form>
  </section>
</body>
</html>