<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<html>
<head>
<title>Portal Kredit</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
</head>

<body onload="loginError();">
	<div class="card" style="background-color: #505060;">
		<div class="row">
			<div class="col-md-12">
				<div class="text-center">
					<img src="assets/images/title.png" alt="Title" style="width: 60%" />
				</div>
			</div>
		</div>
	</div>
	&nbsp;
	<div class="row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">

				<div class="card-block">
					<form action="LoginServlet" method="post" id="loginForm">
						<div class="form-group">
							<input type="text" class="form-control" name="userID"
								placeholder="Enter User ID" required value="${userID}">
						</div>

						<div class="form-group" id="passForm">
							<input type="password" class="form-control" name="password"
								placeholder="Password" id="password" value="">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-block btn-action"
								type="submit" value="login">
								<img
									src="assets/images/glyphicons_free/glyphicons/png/glyphicons-387-log-in-white.png"
									width="17" height="15" alt=""> Log in
							</button>
						</div>
						<div class="alert alert-danger hidden-xs-up" role="alert"
							id="loginError">
							<input type="text" class="form-control hidden-xs-up"
								value="${loginStatus}" id="passBool">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/index.js"></script>
</body>
</html>
