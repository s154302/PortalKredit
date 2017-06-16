<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="createAdminStatus()">
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create Admin</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="adminID"
								placeholder="*Admin username">
						</div>

						<div class="form-group">
							<input type="password" class="form-control" name="adminPassword"
								placeholder="*Password">
						</div>
						
						<div class="form-control-feedback hidden-xs-up"
							id="createAdminResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${createAdminStatus}" id="createAdminBool">
						</div>
						
						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAdmin">Create Admin</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/bootstrap-formhelpers.js"></script>
	<script src="../assets/js/form-input.js"></script>
	<script src="../assets/js/error-messages.js"></script>
</body>
</html>