<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Cookie[] cookies = request.getCookies();
for (Cookie c : cookies) {
	if (c.getName().equals("val"))
		response.sendRedirect("Welcome.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
</head>
<body>
	<h1>SIGN UP</h1>
	<form action="SignUp" method="post">
		<P>
			<input type="text" name="user" placeholder="Username">
		</P>
		<P>
			<input type="text" name="email" placeholder="Email">
		</P>
		<P>
			<input type="password" name="pass" placeholder="Password">
		</P>
		<P>
			<input type="password" name="pass_again"
				placeholder="Nhắc lại password">
		</P>
		<p>
			<input type="submit" name="btn_signup" value="SIGN UP">
		</p>
	</form>
</body>
</html>