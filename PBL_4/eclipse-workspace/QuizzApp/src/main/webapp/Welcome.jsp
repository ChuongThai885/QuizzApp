<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Expires", "0"); //Proxies

//check user login yet
boolean check = false;
Cookie[] cookies = request.getCookies();
for (Cookie c : cookies) {
	if (c.getName().equals("name"))
		check = true;
}
if (check == false)
	response.sendRedirect("index.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
	Hello ${name}
	<br>
	<iframe width="560" height="315"
		src="https://www.youtube.com/embed/r_0JjYUe5jo?controls=0"
		title="YouTube video player" frameborder="0"
		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
		allowfullscreen></iframe>
	<br>
	<c:out value="Hello World"></c:out>
	<form action="Logout" method="get">
		<input type="submit" value="Logout">
	</form>
</body>
</html>