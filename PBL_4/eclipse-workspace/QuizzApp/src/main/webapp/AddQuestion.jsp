<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo quiz</title>
<link rel="stylesheet" href="css/AddQuestion.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script>
	// Popup Open
	function popupOpen() {
		document.getElementById("editquizform").style.display = "block";
        document.getElementById("overlay").style.display = "block";
	}
	// Popup Close
	function popupClose() {
		document.getElementById("editquizform").style.display = "none";
        document.getElementById("overlay").style.display = "none";
	}
</script>
</head>
<body>
	<!-- header -->
	<header>		
		<div class="logo-container">
			<img
				src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg"
				height="55">
		</div>
		<div class="group-button">
			<div class="btn-container">
				<button name="btnSave" class="btn-important">Chơi</button>
			</div>
			<div class="btn-container">
				<button name="btnExit" class="btn-other">Thoát</button>
			</div>
		</div>
	</header>
	<!-- Quiz info -->
	<div class="quiz-main">		
		<div class="quiz-info">Tên Quiz</div>
		<div class="quiz-info">Chủ đề</div>
		<div class="quiz-info">
			<input type="button" name="btnAddQuiz" class="quiz-btn"
				onclick="popupOpen();" value="Sửa Quiz">		
		</div>
        <div class="quiz-info">
			<input type="button" name="btnAddQues" class="quiz-btn"
				value="Thêm câu hỏi">		
		</div>
	</div>
    <!--show ra câu hỏi-->
    <div class="question-show">
        <div class="question-header">
            <div class="question-header-text">
                Câu hỏi 1
            </div>
            <div class="group-button">
                <div class="btn-container">
                    <input type="submit" name="btnEditQues" class="btn-important" value="Sửa">
                </div>
                <div class="btn-container">
                    <input type="button" name="btnCloseQues" class="btn-other" value="Huỷ">
                </div>
            </div>
        </div>
        <div class="question-content">
            <div class="question-title">
                Một tháng có tối đa bao nhiêu ngày chủ nhật?
            </div>
            <div class="anwser">

            </div>
        </div>
    </div>
    <!--overlay-->
	<div class="overlay" id="overlay" style="display: none;"></div>
	<!-- form sửa thông tin quiz -->
	<div class="edit-form" id="editquizform" style="display: none;">
		<form name="quizform" class="form-inner">
            <h1>Thông tin Quiz</h1>
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
                    <input type="button" name="btnClose" class="btn-other"
                    onclick="popupClose();" value="Huỷ">
                </div>
            </div>				
		</form>
	</div>
</body>
</html>