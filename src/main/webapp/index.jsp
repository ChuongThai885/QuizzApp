<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
try {
	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
		if (c.getName().equals("name"))
	response.sendRedirect("Welcome.jsp");
	}
} catch (Exception e) {
	System.out.println(e.getMessage());
}
%>
<html>

<head>
<meta charset="UTF-8">
<title>Quiz App</title>
<link rel="stylesheet" href="css/HomePage.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>

<body>
	<!--header-->
	<header>
		<div class="left-container">
			<a href=""><img
				src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg"
				height="60"> </a>
		</div>
		<div class="nav-container">
			<ul>
				<li><a href="">Cách chơi</a></li>
				<li><a href="">Mẫu quiz mới</a></li>
			</ul>
		</div>
		<div class="right-container">
			<div class="btn-container">
				<a href="SignUp.jsp">Đăng ký</a>
				<!--Dẫn tới trang nhập code để chơi-->
			</div>
			<div class="btn-container">
				<a href="">Vào game</a>
				<!--Dẫn tới trang đăng ký-->
			</div>
		</div>
	</header>
	<div class="content">
		<!--nội dung-->
		<div class="introduction-container">
			<div class="text-container">
				<h1>Quiz App</h1>
				<h3>Học, ôn tập, vui chơi cùng Quiz App</h3>
				<div class="group-button">
					<div class="main-btn-container">
						<a href="SignUp.jsp">Bắt đầu </a>
						<!--Dẫn tới đăng ký-->
					</div>
					<div class="main-btn-container">
						<a href="Login.jsp">Đăng nhập</a>
						<!--Dẫn tới đăng nhập-->
					</div>
				</div>
			</div>
			<div class="picture-container">
				<img
					src="https://stjohns.wrexhamparish.org.uk/wp-content/uploads/2017/11/QuizGeobuuk-547x381.png"
					width="500px">
			</div>
		</div>
		<div class="usage-container">
			<div class="usage-title">
				<h2>Sử dụng Quiz App thật dễ dàng</h2>
			</div>
			<!--hướng dẫn sử dụng-->
			<div class="usage-content-container">
				<div class="step-container">
					<div class="step-num">1</div>
					<div class="step-title">Tạo bộ câu hỏi</div>
					<div>
						<img
							src="https://cdn.riddle.com/website/assets/homepage/img/illo-quiz-types.webp"
							width="280">
					</div>
					<!--hình ảnh minh hoạ-->
				</div>
				<div class="step-container">
					<div class="step-num">2</div>
					<div class="step-title">Người chơi tham gia qua mã pin</div>
					<div>
						<img
							src="https://loquiz.com/wpmainpage/wp-content/uploads/2020/02/Quiz-game-700x467.jpg"
							width="280">
					</div>
					<!--hình ảnh minh hoạ-->
				</div>
				<div class="step-container">
					<div class="step-num">3</div>
					<div class="step-title">Kết quả xếp hạng</div>
					<div>
						<img
							src="https://cdn1.participoll.com/wp-content/uploads/2013/01/27015509/feature3-300x196.png"
							width="280">
					</div>
					<!--hình ảnh minh hoạ-->
				</div>
			</div>
		</div>
	</div>
</body>

</html>