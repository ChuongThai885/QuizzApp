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
int n;
try {
	//le = new QuizzService().Get_All_Exam(u.getEmail());
	//check user login yet
	boolean check = false;
	Cookie[] cookies = request.getCookies();
	for (Cookie c : cookies) {
		if (c.getName().equals("name")) {
	check = true;
		}
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
			<a href="HomePage.html"><img
				src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg"
				height="50"> </a>
		</div>
		<div class="nav-container">
			<ul>
				<li><a href="Welcome.jsp">Trang cá nhân</a></li>
				<li><a href="AddQuiz.jsp">Tạo quiz mới</a></li>
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
				Tạo quiz mới <input type="button" onclick="location.href='AddQuiz.jsp';" class="btn-general btn-add" value="+">
			</div>
		</div>
		<div class="quizlist-container">
			<%
			for (Exam e : le) {
				try
				{
					e.getName();
				}
				catch(Exception e1)
				{
					response.sendRedirect("index.jsp");
				}
				n = (int) request.getSession().getAttribute("numberQuiz");
				//n = new QuizzService().Get_Number_Of_Question(e.getID());
			%>
			Bạn hiện có <%=n %> quiz
			<div class="quiz-container">
				<div class="quiz-title">
					<div class="title-text">
						<b><%=e.getName()%></b>
					</div>
					<div class="btn-group">
						<form method="post" action="PlayGame?id=<%=e.getID()%>">
							<input type="submit" class="btn-general btn-important"
								value="Chơi">
						</form>
						<input type="submit" class="btn-general btn-other" value="Sửa">
					</div>
				</div>
				<div class="quiz-content">
					<p><%=e.getTopic()%></p>
					<p>... câu hỏi
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