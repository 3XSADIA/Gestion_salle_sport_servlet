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
    <header>Add cours</header>
    <form action="Cours" class="form" method="post">
      <input type="hidden" name="id" value="<c:out value=" ${cours.id }" />">
      <div class="input-box">
        <label>Label</label>
        <input type="text" placeholder="Enter le nom de cour" required name="label" value="<c:out value=" ${cours.label
          }" />"/>
      </div>

      </div>
      </div>
      <div class="input-box address">
        <label>Equipements</label>

        <div class="select-box">
          <select name="equipements" value="<c:out value=" ${cours.equipements }" />"/>
          <option hidden>Entrer l'quipements</option>
          <option>America</option>
          </select>
        </div>

      </div>
      <button>Submit</button>
    </form>
  </section>
</body>
</html>