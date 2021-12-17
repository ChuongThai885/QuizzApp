<%@page import="QuizzApp.Model.Exam"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa quiz</title>
<link rel="stylesheet" href="css/AddQuiz.css">
</head>
<body>	
<%
	Exam e = (Exam) request.getSession().getAttribute("quiz");
	if (e!=null){
%>
	<!-- form tạo quiz -->
	<div class="add-ques-form" id="addquesform">
		<form name="addform" action="UpdateQuizController?id=<%=e.getID()%>" method="post" class="form-inner">
            <h1>Sửa thông tin Quiz</h1>
            <div class="quiz-info" style="display:none">
                
                <!-- <input type="text" name="edit" value="1">  -->
            </div>
            <div class="quiz-info">
                <input type="text" name="txtQuiz" class="form-input" value="<%=e.getName()%>" required="required">
            </div>
			<div class="quiz-info">
                <input type="text" name="txtTopic" class="form-input" value="<%=e.getTopic()%>" required="required">
            </div>
            <div class="group-button">
                <div class="btn-container">
                    <input type="submit" name="btnSaveQuiz" class="btn-important" value="Lưu">
                </div>
                <div class="btn-container">
                    <input type="button" name="btnClose" class="btn-other" value="Huỷ">
                </div>
            </div>				
		</form>
	</div>
	<%} %>
</body>
</html>