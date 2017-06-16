<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Delete Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="deleteAccountStatus()">
	<jsp:include page="BankerNavbar.jsp"></jsp:include>

	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 blue-outline">
				<div class="card-block">
					<form action="DeleteAccount" method="post">
						<div class="form-group">
							<p class="text-center">Confirm you want to delete</p>
							<p class="text-center" style="color: red;">
								${account.accountName} <br /> (${account.regNo}
								${account.accountNumber})
							</p>
							<dl class="row">
								<dt class="col-sm-3 offset-3">Balance:</dt>
								<dd class="col-sm-3" style="text-align: right">${account.balance }</dd>

								<dt class="col-sm-3 offset-3">Banker</dt>
								<dd class="col-sm-3" style="text-align: right">${userID}</dd>
							</dl>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="password"
								placeholder="Password">
						</div>
						<div class="form-control-feedback hidden-xs-up alert alert-danger"
							id="deleteAccountResult" role="alert">
							<input type="hidden" class="form-control" value="${deleteStatus}"
								id="deleteAccountBool">
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-block btn-action"
								type="submit" name="confirm" value="confirm">Confirm</button>
						</div>
						<div class="form-group">
							<button class="btn btn-primary btn-block btn-action"
								type="submit" name="cancel" value="cancel">Cancel</button>
						</div>
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