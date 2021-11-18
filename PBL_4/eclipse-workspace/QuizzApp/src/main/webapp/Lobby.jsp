<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Chat cùng Chương Thái</title>
	</head>

	<body>
		<h1 id="ID-Room"></h1>
		<button id="button-start">Start</button>
		<ul id="users"></ul>

	</body>
	<script src="http://localhost:3000/socket.io/socket.io.js"></script>
	<script>
		let number_of_users = 0;
		document.getElementById("button-start").setAttribute("hidden", "hidden"); // if users in room = 0 then disable butt
		let questions = [];
		const socket = io("http://localhost:3000");

		socket.on('connect', () => {
			console.log(socket.id + '');
		})
		socket.emit('create-room', room => {
			document.getElementById('ID-Room').textContent = `Your room ID is : ${room}`;
		})
		const users = document.querySelector('#users')
		socket.on('joined-user', (message) => {
			if (number_of_users == 0) {
				document.getElementById("button-start").removeAttribute("hidden"); 
			}
			number_of_users++;
			const Item = document.createElement('li');
			Item.textContent = message;
			users.appendChild(Item);
		})

		const button_start = document.querySelector('#button-start');
		button_start.addEventListener('click', (e) => {
			questions =  <%= request.getSession().getAttribute("quiz") %>;
			console.log(questions[0]);
			socket.emit('start-game', questions);
			socket.on('get-question', (index, maxquestion, question) => {
				//
			})
		})
	</script>

	</html>
	<% //page for client joining %>