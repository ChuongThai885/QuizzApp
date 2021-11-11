<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Chat cùng Chương Thái</title>
</head>

<body>
	<h1>Chat demo</h1>
	<ul id="messages"></ul>

	<form id="chat-form">
		<input type="text" id="chat-message">
		<button id="send-chat">Gửi</button>
	</form>

</body>
<script src="http://localhost:3000/socket.io/socket.io.js"></script>
<script>
		const socket = io("http://localhost:3000");

		const name = prompt('Nhập tên m!');
		const chatForm = document.querySelector('#chat-form');
		const chatMess = document.querySelector('#chat-message')
		chatForm.addEventListener('submit',(e) => {
			e.preventDefault();

			const message = chatMess.value;
			socket.emit('on-chat',{
				name,
				message: message
			});
			chatMess.value = '';
		})
		socket.emit('create-room', room => {
			console.log(room);
		} )
		const messages = document.querySelector('#messages')
		socket.on('user-chat',(message) =>{
			const chatItem = document.createElement('li');
			chatItem.textContent = '${message.name}: ${message.message}';
			chatItem.textContent = message.name +": " + message.message;
			messages.appendChild(chatItem);
		})
	</script>

</html>
<%
//page for client joining
%>