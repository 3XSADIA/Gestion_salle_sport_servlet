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
    <header>Add/Edit Seance</header>
    <form method="post" class="form" action="SeanceServlet">
      <input type="hidden" name="id" value="<c:out value="${Seance.id}" />">
      
      <label>Coach</label>
      <div class="select-box">
        <select name="id_coach">
          <option value="" selected disabled>Select a coach</option>
          <c:forEach var="coach" items="${coachs}">
            <option value="${coach.id}" ${coach.id == Seance.id_coach ? 'selected' : ''}>${coach.nom}</option>
          </c:forEach>
        </select>
      </div><br>
      
      <label>Cours</label>
      <div class="select-box">
        <select name="id_cours">
          <option value="" selected disabled>Select a course</option>
          <c:forEach var="course" items="${courses}">
            <option value="${course.id}" ${course.id == Seance.id_cours ? 'selected' : ''}>${course.label}</option>
          </c:forEach>
        </select>
      </div>

      <div class="input-box">
        <label>Horaire</label>
        <input type="text" placeholder="Enter le horaire" required name="horaire" value="<c:out value="${Seance.horaire}" />"/>
      </div>
      
      <button>Submit</button>
    </form>
  </section>
</body>
</html>
