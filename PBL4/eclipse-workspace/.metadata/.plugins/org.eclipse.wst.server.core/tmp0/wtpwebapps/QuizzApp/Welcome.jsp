<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setHeader("Expires", "0"); //Proxies

	if (session.getAttribute("user") == null) {
		//RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		//dispatcher.forward(request, response);
		response.sendRedirect("index.jsp");
	}
	%>
	hello <%= session.getAttribute("user") %>> , have a good time
	<iframe width="560" height="315"
		src="https://www.youtube-nocookie.com/embed/dQw4w9WgXcQ"
		title="YouTube video player" frameborder="0"
		allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
		allowfullscreen></iframe>
	<form action="Logout" method="post">
	<input type="submit" value="Logout">
	</form>
</body>
</html>