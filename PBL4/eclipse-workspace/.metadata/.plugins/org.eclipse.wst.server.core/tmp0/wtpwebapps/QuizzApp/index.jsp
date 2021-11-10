<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resource/formLogin.css">
<title>Login</title>
</head>

<body>
	<div>
		<div id="heading">
			<h1>LOGIN</h1>
		</div>
		<div class="splitdiv">
			<div class="leftsplit">
				<h1>WELCOME TO QUIZGAME!</h1>
			</div>
			<div class="rightsplit">
				<form action="Login" method="post" class="form-login">
					<div class="form-group">
						<input type="text" class="form-input" name="user"
							placeholder="Username">
					</div>
					<div class="form-group">
						<input type="password" class="form-input" name="pass"
							placeholder="Password">
					</div>
					<div>
						<input type="submit" class="form-submit" name="btn_login"
							value="LOGIN">
					</div>
					<hr>
					<div>
						Do not have account yet? <input type="submit" class="form-submit"
							name="btn_signup" value="SIGN UP">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>

</html>