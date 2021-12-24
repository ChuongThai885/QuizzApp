<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>QuizzGame management</title>
		<link rel="stylesheet" href="css/game_management.css">
		<link rel="stylesheet" href="css/Podium_M.css">
		<link rel="stylesheet" href="css/EntryGame.css">
		<link rel="stylesheet" type="text/css"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	</head>

	<body>
		<div class="start_box">
			<!-- <h1 class="ID-Room"></h1>
			<button class="button-start">Start</button>
			<button class="button-cancel">Cancel</button>
			<button class="button-lock">Lock</button>
			<ul class="users"></ul> -->
			<header>
				<div class="header-title">
					Chào mừng, ID room của bạn là:
				</div>
			</header>
			<section>
				<div class="entry-button">
					<div class="entry-button-left">
						<input type="button" id="button-cancel" class="btn-general btn-big" value="Thoát">
					</div>
					<div class="entry-button-right">
						<button type="button" id="button-lock" class="btn-general btn-small">
							<div class="lock-icon">
								<!-- add icon when button been clicked -->
							</div>
						</button>
						<input type="button" id="button-start" class="btn-general btn-big" value="Bắt đầu">
					</div>
				</div>

				<div class="entry-players">
					<!--Thêm code hiển thị các người chơi-->
				</div>
			</section>
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
				<div class="result">
					<!-- insert number of times the answer is selected from JS -->
				</div>
				<div class="result-icons">
				
				</div>
				<div class="option_list">
					<!--insert options from JS-->
				</div>
			</section>
		</div>

		<div class="end_box">
			<input type="button" id="button-out" class="btn-exit" value="Thoát">
			<div class="rankings">
				<!-- insert top 3 players with the highest score and player rank from JS -->
			</div>
		</div>
		
		<!-- cổng này để mở cho mọi người vô -->
		<!--<script src="http://ce6d-2405-4800-440f-84d-2816-1e62-e16c-b6a7.ngrok.io/socket.io/socket.io.js"></script>-->
		<script src="http://localhost:3000/socket.io/socket.io.js"></script>
		<!-- <script src="http://171.251.251.51:3000/socket.io/socket.io.js"></script> -->
		<script>const quizes = <%= request.getSession().getAttribute("quiz") %>;</script>
		<script src="js/game.js"></script>
	</body>

	</html>