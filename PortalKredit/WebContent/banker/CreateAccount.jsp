<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Create Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create Account for Client</h1>
				<div class="card-block">
					<form action="CreateAccount" method="post">

						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountName"
								placeholder="Account Name">
						</div>



						<div class="form-group">
							<input type="text" pattern="[0-9]*" class="form-control"
								name="clientRegNo" placeholder="*Reg. No."
								title="Please only input numbers." value="${user.regNo}" readonly>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountType"
								placeholder="*Account Type">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientID"
								placeholder="*Client's ID" value="${clientID}" readonly>
						</div>

						<div class="form-group">
							<input type="text" pattern="[0-9]*" class="form-control"
								name="clientBalance" placeholder="*Balance"
								title="Please only input numbers.">
						</div>

						<div class="form-group">
							<select class="form-control bfh-currencies"
								id="createClientCountry" name="clientCurrency" data-currency="DKK"></select>
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAccount">Create Account</button>
					</form>
				</div>
				<p style="color: red;">${createAccountStatus}</p>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/bootstrap-formhelpers.js"></script>
</body>
</html>