<%@page import="QuizzApp.Model.User_Infor"%>
<%@page import="QuizzApp.Model.Exam"%>
<%@page import="QuizzApp.Service.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
response.setHeader("Pragma", "no-cache"); //HTTP 1.0
response.setHeader("Expires", "0"); //Proxies
User_Infor u = (User_Infor) request.getSession().getAttribute("user");
ArrayList<Exam> le = new ArrayList<Exam>();
ArrayList<Integer> numques = new ArrayList<Integer>();

try {
	//check user login yet
	boolean check = false;
	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
		if (c.getName().equals("name")) {
	check = true;
		}
	}
	if (check == false)
		response.sendRedirect("HomePage.jsp");
	le = (ArrayList<Exam>) request.getAttribute("listquizz");	
	numques = (ArrayList<Integer>) request.getAttribute("numberQues");
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
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
</head>

<body>
	<header>
		<div class="left-container">
			<a href="HomePage.html"><img
				src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg"
				height="50"> </a>
		</div>
		<div class="nav-container">
			<ul>
				<li><a href="Login">Trang cá nhân</a></li>
				<li><a href="AddQuizController">Tạo quiz mới</a></li>
			</ul>
		</div>
		<div class="right-container">
			<div class="right-item">
				<input type="text" name="txtSearch" class="form-input"
					placeholder="Tìm kiếm">
			</div>
			<div class="right-item">
				Chào,
				<%=u.getName()%><!--${name}-->
			</div>
			<div class="right-item">
				<a href="Logout">Đăng xuất</a>
			</div>
		</div>
	</header>
	<div class="content">
		<div class="personal-info">
			<div class="info-item">
				<h4>Thông tin cá nhân</h4>
			</div>
			<div class="info-item">
				<%=u.getName()%>
			</div>
			<div class="info-item">
				<%=u.getEmail()%>
			</div>
			<div class="info-item">
				Tạo quiz mới <input type="button" onclick="location.href='AddQuizController';" class="btn-general btn-add" value="+">
			</div>
		</div>
		<div class="quizlist-container">
		<%int n = (int) request.getAttribute("numberQuiz"); %>
		Bạn hiện có <%=n %> quiz
			<%			
			for (int i=0; i<le.size(); i++) {
				try
				{
					le.get(i).getName();
				}
				catch(Exception e1)
				{
					response.sendRedirect("index.jsp");
				}
				
				//n = new QuizzService().Get_Number_Of_Question(e.getID());
			%>
			
			<div class="quiz-container">
				<div class="quiz-title">
					<div class="title-text">
						<b><%=le.get(i).getName()%></b>
					</div>
					<div class="btn-group">
						<form method="post" action="PlayGame?id=<%=le.get(i).getID()%>">
							<input type="submit" class="btn-general btn-important"
								value="Chơi">
						</form>						
						<input type="button" onclick="location.href='UpdateQuizController?id=<%=le.get(i).getID()%>';" 
						class="btn-general btn-other" value="Sửa">					
						<button type="button" class="btn-general btn-del" onclick="location.href='DelQuizController?id=<%=le.get(i).getID() %>';">
						<i class="fas fa-trash-alt"></i></button>										
					</div>
				</div>
				<div class="quiz-content">
					<p><%=le.get(i).getTopic()%></p>
					<p><%=numques.get(i) %> câu hỏi
					</p>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>

</html>