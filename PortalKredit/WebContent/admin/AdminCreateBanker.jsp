<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Create Banker</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="createBankerStatus()">
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create Banker</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group" id="bankerFirstNameForm">
							<input type="text" class="form-control" name="bankerFirstName"
								placeholder="*First Name" maxlength="45" id="bankerFirstName"
								oninput="errorInput(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankerFirstNameHelp"></div>
						</div>

						<div class="form-group" id="bankerLastNameForm">
							<input type="text" class="form-control" name="bankerLastName"
								placeholder="*Last Name" maxlength="45" id="bankerLastName"
								oninput="errorInput(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankerLastNameHelp"></div>
						</div>

						<div class="form-group" id="bankerPasswordForm">
							<input type="password" class="form-control" name="bankerPassword"
								id="bankerPassword" placeholder="*Password"
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankerPasswordHelp"></div>
						</div>

						<div class="form-group">
							<input type="email" class="form-control" name="bankerEmail"
								placeholder="E-mail" maxlength="45">
						</div>

						<div class="form-group" id="bankerTelephoneForm">
							<input type="text" class="form-control" name="bankerTelephone"
								placeholder="Phone Number" id="bankerTelephone"
								oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankerTelephoneHelp"></div>
						</div>

						<div class="form-group id=bankerRegForm">
							<input type="text" class="form-control" name="bankerReg"
								id="bankerReg" oninput="numberError(this.id)"
								placeholder="*Registration Number">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankerRegHelp"></div>
						</div>

						<div class="form-control-feedback hidden-xs-up"
							id="createBankerResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${createBankerStatus}" id="createBankerBool">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createBanker">Create Banker</button>
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
</body>
</html>