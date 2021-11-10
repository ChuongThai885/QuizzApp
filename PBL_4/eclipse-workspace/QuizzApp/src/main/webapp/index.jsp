<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
try {
	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
		if (c.getName().equals("val"))
	response.sendRedirect("Welcome.jsp");
	}
} catch (Exception e) {
	System.out.println(e.getMessage());
}
%>
<!DOCTYPE html>
<html>

<head>
<title>Quiz App</title>
</head>

<body>
	<div>
		<!--header-->
		<a href=""><img src=""></a>
		<!--logo-->
		<ul>
			<!--danh mục trên header (tự chế)-->
			<li><a href="">How to play?</a></li>
			<li><a href="">New template</a></li>
		</ul>
		<div>
			<a href="">Vào game</a>
			<!--Dẫn tới trang nhập code để chơi-->
			<a href="SignUp.jsp">Đăng ký</a>
			<!--Dẫn tới trang đăng ký-->
		</div>
	</div>
	<div>
		<!--nội dung-->
		<div>
			<h1>Quizz App</h1>
			<h3>Vài dòng giới thiệu, slogan...</h3>
			<button type="button" onclick="window.location.href='SignUp.jsp'">Tạo
				một bộ câu hỏi</button>
			<!--Dẫn tới đăng ký-->
			<button type="button" onclick="window.location.href='Login.jsp'">Đăng
				nhập</button>
			<!--Dẫn tới đăng nhập-->
		</div>
		<div>
			<!--thêm vài nội dung màu mè-->
			<h2>Bắt đầu</h2>
			<!--hướng dẫn sử dụng-->
			<div>
				<div>1</div>
				<div>Tạo bộ câu hỏi</div>
				<div>
					<img src="">
				</div>
				<!--hình ảnh minh hoạ-->
			</div>
			<div>
				<div>2</div>
				<div>Người chơi tham gia trả lời</div>
				<div>
					<img src="">
				</div>
				<!--hình ảnh minh hoạ-->
			</div>
			<div>
				<div>3</div>
				<div>Kết quả xếp hạng</div>
				<div>
					<img src="">
				</div>
				<!--hình ảnh minh hoạ-->
			</div>
		</div>
	</div>
</body>

</html>