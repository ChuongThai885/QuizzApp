<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Cookie[] cookies = request.getCookies();
for (Cookie c : cookies) {
	if (c.getName().equals("name"))
		response.sendRedirect("Welcome.jsp");
}
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="stylesheet" href="css/formSignUp.css">
</head>
<body>
    <h1>ĐĂNG KÝ</h1>
    <form class="form-signup">
        <div class="form-group">
            <input type="text" name="user" class="form-input" placeholder="Email">
        </div>
        <div class="form-group">
            <input type="password" name="pass" class="form-input" placeholder="Mật khẩu">
        </div>
        <div class="form-group">
            <input type="password" name="pass_again" class="form-input" placeholder="Nhắc lại mật khẩu">
        </div>  
        <div class="btn-container">
            <input type="submit" name="btn_signup" class="form-submit" value="ĐĂNG KÝ">
        </div>    
        <p></p>
    </form>
</body>
</html>