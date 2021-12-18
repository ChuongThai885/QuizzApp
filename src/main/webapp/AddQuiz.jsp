<%@page import="QuizzApp.Model.User_Infor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo quiz</title>
<link rel="stylesheet" href="css/AddQuiz.css">
</head>
<body>	
<%
	User_Infor u = (User_Infor) request.getSession().getAttribute("user");
	if (u!=null){
%>
	<!-- form tạo quiz -->
	<div class="add-ques-form" id="addquesform">
		<form name="addform" action="AddQuizController" method="post" class="form-inner">
            <h1>Tạo Quiz mới</h1>
            <div class="quiz-info">
                <input type="text" name="txtQuiz" class="form-input" placeholder="Tên Quiz" required="required">
            </div>
			<div class="quiz-info">
                <input type="text" name="txtTopic" class="form-input" placeholder="Chủ đề" required="required">
            </div>
            <div class="group-button">
                <div class="btn-container">
                    <input type="submit" name="btnSaveQuiz" class="btn-important" value="Lưu">
                </div>
                <div class="btn-container">
                    <input type="button" onclick="location.href='Login';" name="btnClose" class="btn-other" value="Huỷ">
                </div>
            </div>				
		</form>
	</div>
	<%} %>
</body>
</html>