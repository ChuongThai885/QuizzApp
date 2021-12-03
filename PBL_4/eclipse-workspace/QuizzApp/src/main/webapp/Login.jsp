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
    <title>Đăng nhập</title>
</head>

<body>
    <div>
        <div id="heading">
            <h1>ĐĂNG NHẬP</h1>
        </div>
        <div class="splitdiv">
            <div class="leftsplit">
                <h2>CHÀO BẠN!</h2>
            </div>
            <div class="rightsplit">
                <form action="Login" method="post" class="form-login">
                    <div class="form-group">
                        <input type="text" class="form-input" name="user" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-input" name="pass" placeholder="Mật khẩu">
                    </div>
                    <div class="btn-group">
                        <input type="submit" class="form-submit" name="btn_login" value="ĐĂNG NHẬP">
                    </div>
                    <div class="btn-group">
                        Chưa có tài khoản?
                        <a href="SignUp.jsp">ĐĂNG KÝ</a>
                    </div>
                    <div class="form-group">
                        Quay về 
                        <a href="HomePage.jsp">Trang chủ</a>
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