<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create Banker</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="bankerFirstName"
								placeholder="*First Name" maxlength="45">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="bankerLastName"
								placeholder="*Last Name" maxlength="45">
						</div>

						<div class="form-group">
							<input type="password" class="form-control" name="bankerPassword"
								placeholder="*Password">
						</div>

						<div class="form-group">
							<input type="email" class="form-control" name="bankerEmail"
								placeholder="E-mail" maxlength="45">
						</div>

						<div class="form-group">
							<input type="text" class="form-control bfh-phone"
								name="bankerTelephone" placeholder="Phone Number">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="bankerReg"
								placeholder="*Registration Number">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createClient">Create Banker</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>
</html>