<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>index</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/custom.css" rel="stylesheet">
</head>
<body>

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
					<form action="LoginServlet" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="userID"
								aria-describedby="textHelp" placeholder="Enter User ID">
						</div>
						<div class="form-group">
							<input type="password" class="form-control"
								name="password" placeholder="Password">
						</div>
						<button class="btn btn-primary btn-block btn-action" type="submit" value="login">Log
							in</button>

					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>