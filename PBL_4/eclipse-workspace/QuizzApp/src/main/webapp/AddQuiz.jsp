<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo quiz</title>
<link rel="stylesheet" href="css/AddQuiz.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
</head>
<body>	
	<!-- form tạo quiz -->
	<div class="add-ques-form" id="addquesform" style="display: block;">
		<form name="addform" action="AddQuizController" method="post" class="form-inner">
            <h1>Tạo Quiz mới</h1>
            <div class="quiz-info">
                <input type="text" name="txtQuiz" class="form-input" placeholder="Tên Quiz">
            </div>
			<div class="quiz-info">
                <input type="text" name="txtTopic" class="form-input" placeholder="Chủ đề">
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
</body>
</html>