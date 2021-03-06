<%@page import="Model.Bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo quiz</title>
<link rel="stylesheet" href="css/AddQuestion.css">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

</head>
<body>
<%
	User_Infor u = (User_Infor) session.getAttribute("user");
	Exam e = (Exam) session.getAttribute("quiz");
	ArrayList<QuestionForm> ql = (ArrayList<QuestionForm>) session.getAttribute("queslist");
	if (u!=null){
%>
	<!-- header -->
	<header>		
		<div class="logo-container">
			<img
				src="https://www.dng24.co.uk/wp-content/uploads/2016/12/quiz.jpg"
				height="55">
		</div>
		<div class="group-button">
		<form method="post" action="PlayGame?id=<%=e.getID()%>">
			<button type="submit" name="btnSave" class="btn-general btn-important">Chơi</button>
		</form>						
			<button name="btnExit" class="btn-general btn-other" 
			onclick="location.href='/QuizzApp/';">Thoát</button>
		</div>
	</header>
	<!-- Tên quiz, thêm câu hỏi -->
	<div class="quiz-main">		
		<div class="quiz-info"><h2><%=e.getName() %></h2></div>
		<div class="quiz-info"><i class="fas fa-clipboard-list"></i> <%=e.getTopic() %></div>
		<div class="quiz-info">
			<input type="button" name="btnAddQuiz" class="quiz-btn"
				onclick="popupOpen();" value="Sửa Quiz">		
		</div>
        <div class="quiz-info">
			<input type="button" name="btnAddQues" class="quiz-btn"
			onclick="quesPopupOpen();" value="Thêm câu hỏi">		
		</div>
	</div>
    <!--show ra câu hỏi-->
    <div>
    <% if (ql!=null){
    	int dem = 1;
    	int countdown[] = {20, 30, 60, 90};%>
    <div class="question-list-show">	
    <% for (QuestionForm q : ql) {%>
    <div class="question-show">
        <div class="question-header">
            <div class="question-header-text">
                Câu hỏi <%=dem %>
            </div>
            <div class="group-button">
                    <input type="button" name="btnEditQues" onclick="editQuesOpen(<%=dem %>);" class="btn-general btn-important" value="Sửa">
                    <button type="button" class="btn-general btn-other" 
                    onclick="location.href='DeleteQuestion?id=<%=q.getQues().getID() %>&quiz=<%=q.getQues().getID_Ex()%>';">
                    <i class="fas fa-trash-alt"></i></button>
            </div>
        </div>
        <div class="question-content">
            <div class="question-title">
                <%=q.getQues().getQues() %>
            </div>
            <div class="answer-list">
            <%for (int i=0; i < q.getAns().size();i++){ %>
            <div class="answer">
            <%if (q.getAns().get(i).isSelected()){ %>
				<i class="fas fa-check-circle" style="color: #159E15;"></i>
				<%} else{ %>
				<i class="far fa-circle" style="color: #EB5931;"></i>
				<%} %>
				<%=q.getAns().get(i).getAns() %>
            </div>
            <%} %>
            </div>
        </div>
    </div>
    
    <!--overlay-->
	<div class="overlay" id="overlay" style="display: none;"></div>
	<!-- form sửa câu hỏi -->
	<div class="ques-form" id="editquesform<%=dem %>" style="display: none;">
		<form name="quizform" action="UpdateQuestion?id=<%=q.getQues().getID() %>" method="post" class="form-inner">
            <h1>Sửa câu hỏi</h1>
            <input type="text" name="idQuiz" value="<%=q.getQues().getID_Ex()%>" style="display:none">
			<div class="quiz-info">
				<input type="text" name="txtQues" class="form-input" required="required" value="<%=q.getQues().getQues() %>">
			</div>
			<div class="quiz-info">
			<!-- lấy thời gian đếm ngược trong dữ liệu -->
				<select name="selectTime" class="select-box">
				<%for(int i=0; i<4; i++){ 
				if (countdown[i]==q.getQues().getCountdown_Time()){%>
					<option value="<%=countdown[i]%>" selected="selected"><%=countdown[i]%> giây</option>
					<%}else {%>
					<option value="<%=countdown[i]%>"><%=countdown[i]%> giây</option>
					<%} %>					
				<%} %>
				</select>
			</div>
			<!-- lấy đáp án trong dữ liệu -->	
			<%for (int i=0; i < q.getAns().size();i++){ %>
			<div class="quiz-info">
			<%if (q.getAns().get(i).isSelected()){ %>
				<input type="radio" name="trueAns" value="<%=i+1 %>" checked="checked">
				<%} else{%>
				<input type="radio" name="trueAns" value="<%=i+1 %>">
				<%} %>
				<input type="text" name="txtIDAns<%=i+1 %>" value="<%=q.getAns().get(i).getID() %>" style="display:none">
			<%if (i < 2){ %>
				<input type="text" name="txtAns<%=i+1 %>" class="form-input" 
				required="required" value="<%=q.getAns().get(i).getAns()%>">
			<%} else { %>
			<input type="text" name="txtAns<%=i+1 %>" class="form-input" 
				value="<%=q.getAns().get(i).getAns()%>">
			<%} %>
			</div>
			<%} %>
			<!-- hiển thị thêm các textbox rỗng nếu số đáp án < 4 -->
			<%for (int i=0; i< (4-q.getAns().size());i++){%>
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="<%=i+1+q.getAns().size() %>">
				<input type="text" name="txtIDAns<%=i+1+q.getAns().size() %>" value="" style="display:none">
				<input type="text" name="txtAns<%=i+1+q.getAns().size() %>" 
				class="form-input" placeholder="Đáp án <%=i+1+q.getAns().size() %>">
			</div>
			<%} %>			
			<div class="group-button" style="justify-content:center">
				<input type="submit" name="btnEditQues" class="btn-general btn-important" value="Lưu">
				<input type="button" name="btnCloseEdit" class="btn-general btn-other"
                    onclick="editQuesClose(<%=dem %>);" value="Huỷ">             
            </div>		
		</form>
	</div>
    <%dem+=1;%> 
    <% }} %>
    </div>
    <!--overlay-->
	<div class="overlay" id="overlay" style="display: none;"></div>
	<!-- form sửa thông tin quiz -->
	<div class="edit-form" id="editquizform" style="display: none;">
		<form method="post" name="quizform" class="form-inner" action="UpdateQuiz?idexam=<%=e.getID()%>"> 
            <h1>Thông tin Quiz</h1>         
            <div class="quiz-info">
                <input type="text" name="txtQuiz" class="form-input" value="<%=e.getName()%>" required="required">
            </div>
			<div class="quiz-info">
                <input type="text" name="txtTopic" class="form-input" value="<%=e.getTopic()%>" required="required">
            </div>
            <div class="group-button" style="justify-content:center">
                <input type="submit" name="btnSaveQuiz" class="btn-general btn-important" value="Lưu">
                <input type="button" name="btnClose" class="btn-general btn-other"
                    onclick="popupClose();" value="Huỷ">
            </div>				
		</form>
	</div>
	</div>
	<!--overlay-->
	<div class="overlay" id="overlay" style="display: none;"></div>
	<!-- form thêm câu hỏi -->
	<div class="ques-form" id="addquesform" style="display: none;">
		<form name="quizform" action="AddQuestion?idquiz=<%=e.getID() %>" method="post" class="form-inner">
            <h1>Câu hỏi mới</h1>
            
			<div class="quiz-info">
				<input type="text" name="txtQues" class="form-input" placeholder="Câu hỏi" required="required">
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
				<input type="radio" name="trueAns" value="1" checked="checked">
				<input type="text" name="txtAns1" class="form-input" placeholder="Đáp án 1" required="required">
			</div>
			<div class="quiz-info">
				<input type="radio" name="trueAns" value="2">
				<input type="text" name="txtAns2" class="form-input" placeholder="Đáp án 2" required="required">
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
		
	<%} %>
	<script src="js/addQues.js"></script>
</body>
</html>