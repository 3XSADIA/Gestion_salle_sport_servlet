<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%= request.getContextPath() %>/template/css/style1.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Login</title>
</head>
<body>

<div class="container">
    <div class="brand-logo"></div>
    <div class="brand-title">3XS</div>
    <form action="index" method="get">
        <div class="inputs">
            <label>Username</label>
            <input type="text" name="username" placeholder="username" /> <!-- Ajoutez le nom de l'input -->
            <label>PASSWORD</label>
            <input type="password" name="password" placeholder="Min 6 charaters long" /> <!-- Ajoutez le nom de l'input -->
            <button type="submit">LOGIN</button>
        </div>
    </form>
    
    <%-- Vérifiez les informations d'identification lors de la soumission du formulaire --%>
    <% String username = request.getParameter("username");
       String password = request.getParameter("password");
       if ("admin".equals(username) && "admin".equals(password)) {
           // Redirection vers la page index si les informations d'identification sont correctes
           response.sendRedirect("index");
       } else if (username != null && password != null) {
           // Affichez un message d'erreur si les informations d'identification sont incorrectes
    %>
           <p style="color: red;">Nom d'utilisateur ou mot de passe incorrect</p>
    <% } %>
</div>

</body>
</html>
