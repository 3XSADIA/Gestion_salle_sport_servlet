<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<%= request.getContextPath() %>/template/css/style1.css"
	rel="stylesheet" type="text/css">
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
    <input type="email" placeholder="username" />
    <label>PASSWORD</label>
    <input type="password" placeholder="Min 6 charaters long" />
    <button type="submit">LOGIN</button>
  </div>
  </form>
    

</div>
  </body>
</html>