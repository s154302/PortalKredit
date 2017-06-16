<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
<title>Create Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
</head>

<body onload="createAccountStatus()">
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-6 offset-3">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1 class="pad-row">Create Account:</h1>
				<div class="card-block">
					<form action="CreateAccount" method="post">

						<div class="form-group" id="clientAccountNameForm">
							<input type="text" class="form-control" name="clientAccountName"
								id="clientAccountName" placeholder="Account Name">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientAccountNameHelp"></div>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientRegNo"
								placeholder="*Reg. No." value="${user.regNo}" readonly>
						</div>

						<div class="form-group">
							<select class="form-control" name="clientAccountType" required>
								<c:forEach var="acc" items="${accountTypes}">
									<option value="${acc.name}">${acc.name}</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group" id="clientIDForm">
							<input type="text" class="form-control" name="clientID"
								id="clientID" placeholder="*Client's ID" value="${clientID}"
								readonly>
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientIDHelp"></div>
						</div>

						<div class="form-group" id="clientBalanceForm">
							<input type="text" class="form-control" name="clientBalance"
								id="clientBalance" placeholder="*Balance"
								oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientBalanceHelp"></div>
						</div>

						<div class="form-group">
							<select class="form-control bfh-currencies"
								id="createClientCountry" name="clientCurrency"
								data-currency="DKK"></select>
						</div>

						<div class="form-control-feedback hidden-xs-up"
							id="createAccountResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${createAccountStatus}" id="createAccountBool">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAccount">Create Account</button>
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