<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>QuizzGame management</title>
		<link rel="stylesheet" href="css/game_management.css">
		<link rel="stylesheet" type="text/css"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	</head>

	<body>
		<div class="start_box">
			<h1 class="ID-Room"></h1>
			<button class="button-start">Start</button>
			<button class="button-cancel">Cancel</button>
			<ul class="users"></ul>
		</div>

		<!-- Quiz Box -->
		<div class="quiz_box">
			<header>
				<div class="total_ques">
					<!-- Inserted Question Count Number from JS-->
				</div>
				<div class="title">Quizz Game</div>
				<div class="timer">
					<div class="time_left_txt">Time left</div>
					<div class="timer_sec"></div>
				</div>
			</header>
			
			<section>
				<button class="next_ques">Next</button>
				<button class="finish_countdown">Finish</button>
				<div class="que_text">
					<!--Insert question from JS-->
				</div>
				<div class="option_list">
					<!--insert options from JS-->
				</div>
			</section>
			
		</div>

		<!-- <script src="http://localhost:3000/socket.io/socket.io.js"></script> -->
		<script src="http://116.103.144.150:3000/socket.io/socket.io.js"></script>
		<script>const quizes = <%= request.getSession().getAttribute("quiz") %>;</script>
		<script src="js/game.js"></script>
	</body>

	</html>