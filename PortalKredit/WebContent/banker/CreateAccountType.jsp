<head>
<title>Account Type</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="createAccountType()">
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1 class="pad-row">Create New Account Type</h1>
				<div class="card-block">
					<form action="CreateAccountType" method="post">
						<div class="form-group" id="accountTypeForm">
							<input type="text" class="form-control" name="accountType"
								id="accountType" placeholder="*Account Type" maxlength="45"
								required oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="accountTypeHelp"></div>
						</div>

						<div class="form-group" id="interestRateForm">
							<input type="text" class="form-control" name="interestRate"
								id="interestRate" oninput="decimalError(this.id)"
								placeholder="*Interest Rate" required>
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="interestRateHelp"></div>
						</div>
						<div class="form-control-feedback hidden-xs-up"
							id="createAccountTypeResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${createAccountTypeStatus}" id="createAccountTypeBool">
						</div>
						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAccountType">Create Account Type</button>
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