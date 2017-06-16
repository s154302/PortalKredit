<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Create Branch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="createBranchStatus()">
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create branch</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group" id="branchRegnoForm">
							<input type="text" class="form-control" name="branchRegno"
								id="branchRegno" placeholder="*Reg No" maxlength="4" required
								oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="branchRegnoHelp"></div>
						</div>

						<div class="form-group" id="bankNameForm">
							<input type="text" class="form-control" name="bankName"
								id="bankName" placeholder="*Bank Name" maxlength="20" required
								value="${bankName}" oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="bankNameHelp"></div>
						</div>

						<div class="form-group" id="branchPostalForm">
							<input type="text" class="form-control" name="postal"
								id="branchPostal" placeholder="*Postal" required
								value="${postal}" oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="branchPostalHelp"></div>
						</div>

						<div class="form-group">
							<select class="form-control bfh-countries" data-country="Denmark"
								name="country"></select>
						</div>

						<div class="form-group" id="branchStreetForm">
							<input type="text" class="form-control" name="street" id="branchStreet"
								placeholder="*Street" required value="${street}" oninput="checkEmpty(this.id)">
								<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="branchStreetHelp"></div>
						</div>

						<div class="form-group" id="branchTelephoneForm">
							<input type="text" class="form-control" name="branchTelephone"
								placeholder="Phone Number" id="branchTelephone"
								oninput="numberError(this.id)" maxlength="20">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="branchTelephoneHelp"></div>
						</div>
						
						<div class="form-control-feedback hidden-xs-up"
							id="createBranchResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${createBranchStatus}" id="createBranchBool">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createBranch">Create Branch</button>
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