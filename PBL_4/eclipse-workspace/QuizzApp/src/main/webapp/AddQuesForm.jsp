<%@page import="java.util.ArrayList"%>
<%@page import="QuizzApp.Model.User_Infor"%>
<%@page import="QuizzApp.Model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo quiz</title>
<link rel="stylesheet" href="css/AddQuestion.css">
</head>
<body>
<div class="ques-form">
		<form name="quizform" action="AddQuestionController" method="post" class="form-inner">
            <h1>Câu hỏi mới</h1>
			<div class="quiz-info">
				<input type="text" name="txtQues" class="form-input" placeholder="Câu hỏi">
			</div>
			<div class="quiz-info">
				<select name="selectTime" class="select-box">
					<option value="20">20 giây</option>
					<option value="30">30 giây</option>
					<option value="60">60 giây</option>
					<option value="90">90 giây</option>
				</select>
			</div>	
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="1">
				<input type="text" name="txtAns1" class="form-input" placeholder="Đáp án 1">
			</div>
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="2">
				<input type="text" name="txtAns2" class="form-input" placeholder="Đáp án 2">
			</div>
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="3">
				<input type="text" name="txtAns3" class="form-input" placeholder="Đáp án 3">
			</div>
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="4">
				<input type="text" name="txtAns4" class="form-input" placeholder="Đáp án 4">
			</div>
			<div class="group-button" style="justify-content:center">
				<input type="submit" name="btnAddQues" class="btn-general btn-important" value="Lưu">
				<input type="button" name="btnCloseQues" class="btn-general btn-other"
                    onclick="quesPopupClose();" value="Huỷ">             
            </div>		
		</form>
	</div>
</body>
</html>