<%@page import="QuizzApp.Model.Exam"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Expires", "0"); //Proxies

ArrayList<Exam> le = new ArrayList<Exam>();
try {
	//check user login yet
	boolean check = false;
	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
		if (c.getName().equals("name"))
	check = true;
	}
	if (check == false)
		response.sendRedirect("index.jsp");
	le = (ArrayList<Exam>) request.getSession().getAttribute("listquizz");
} catch (Exception e) {
	System.out.println(e.getMessage());
}
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Trang cá nhân</title>
	<link rel="stylesheet" href="css/Welcome.css">
</head>

<body>
	<header>
		<div class="left-container">
			<a href="HomePage.html"><img src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg" height="50">
			</a>
		</div>
		<div class="nav-container">
			<ul>
				<li><a href="Welcome.html">Trang cá nhân</a></li>
				<li><a href="AddQuiz.html">Tạo quiz mới</a></li>
			</ul>
		</div>
		<div class="right-container">
			<div class="right-item">
				<input type="text" name="txtSearch" class="form-input" placeholder="Tìm kiếm">
			</div>
			<div class="right-item">
				Chào, Phuong<!--${name}
					<%=request.getSession().getAttribute("m").toString()%>-->
			</div>
			<div class="right-item">
				<a href="Logout">Đăng xuất</a>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="personal-info">
			<div class="info-item">
				<h4>Thông tin cá nhân </h4>
			</div>
			<div class="info-item">
				Phuong <!--${name}<%=request.getSession().getAttribute("m").toString()%>-->
			</div>
			<div class="info-item">
				maiphuongpham150@ gmail.com <!--<% %>-->
			</div>
			<div class="info-item">
				Tạo quiz mới <input type="button" class="btn-general btn-add" value="+">
			</div>
		</div>
		<div class="quizlist-container">
			<div class="quiz-container">
				<div class="quiz-title">
					<div class="title-text">
						<b>Quiz 1 <!--<% %>--> </b>
					</div>
					<div class="btn-group">
						<input type="button" class="btn-general btn-important" value="Chơi">
						<input type="submit" class="btn-general btn-other" value="Sửa">
					</div>
				</div>
				<div class="quiz-content">
					<p>Topic 1 <!--<% %>--></p>
					<p>12<!--<% %>--> câu hỏi</p>					
				</div>
			</div>
		</div>
	</div>
	
	<!--Hello ${name}
	<%=request.getSession().getAttribute("m").toString()%>
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
	<ul>List Quizz
	</ul>
	<%
	for (Exam i : le) {
	%>
	<li><%= i.getName() %>
		<form method="post" action="PlayGame?id=<%=i.getID()%>">
			<input type="submit" value="Create Room">
		</form></li>
	<%
	}
	%>
	<script type="text/javascript">
	console.log('<%=request.getSession().getAttribute("m").toString()%>');
	</script>-->
</body>

</html>