<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
if (session.getAttribute("user") != null) {
	//RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	//dispatcher.forward(request, response);
	response.sendRedirect("Welcome.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/formLogin.css">
<title>Login</title>
</head>
<body>
	<div>
		<div id="heading">
			<h1>LOGIN</h1>
		</div>
		<div class="splitdiv">
			<div class="leftsplit">
				<h1>WELCOME TO QUIZGAME!</h1>
			</div>
			<div class="rightsplit">
				<form action="Login" method="post" class="form-login">

					<label id="error"></label>
					<div class="form-group">
						<input type="text" class="form-input" name="user"
							placeholder="Username">
					</div>
					<div class="form-group">
						<input type="password" class="form-input" name="pass"
							placeholder="Password">
					</div>
					<div>
						<input type="submit" class="form-submit" name="btn_login"
							onsubmit="event.preventDefault()" value="LOGIN">
					</div>
					<hr>
					<div>
						Do not have account yet? <input type="submit" class="form-submit"
							name="btn_signup" value="SIGN UP">
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var str = document.getElementById("error");
		if ("${error}" != "") {
			str.innerHTML = "${error}";
	<%session.removeAttribute("error");
session.invalidate();%>
		} else {
		}
	</script>
</body>
</html>