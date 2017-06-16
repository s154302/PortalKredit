<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Edit Client</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>


<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>

	<div class="row pad-row">
		<div class="col-6 offset-3">
			<div class="card card-outline-primary blue-outline">
				<h1 class="pad-row text-center">${client.fullName}</h1>
				<dl class="row">
					<dt class="col-sm-3 offset-3">ClientID:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.clientID}</dd>

					<dt class="col-sm-3 offset-3">CPR:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.CPR}</dd>

					<dt class="col-sm-3 offset-3">BankerID:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.bankerID}</dd>

					<dt class="col-sm-3 offset-3">Email:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.email}</dd>

					<dt class="col-sm-3 offset-3">Mobile:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.phoneNo}</dd>

					<dt class="col-sm-3 offset-3">Street:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.street}</dd>

					<dt class="col-sm-3 offset-3">City:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.city}</dd>

					<dt class="col-sm-3 offset-3">Country:</dt>
					<dd class="col-sm-3" style="text-align: right">${client.country}</dd>
				</dl>
				<div class="card-block">
					<div class="form-control-feedback hidden-xs-up"
						id="editClientResult" role="alert">
						<input type="text" class="form-control hidden-xs-up"
							value="${editClientStatus}" id="editClientBool">
					</div>
					<form action="EditClient" method="post">
						<div class="form-group" id="clientFirstNameForm">
							<input type="text" class="form-control" name="clientFirstName"
								placeholder="*First Name" maxlength="45" id="clientFirstName"
								oninput="errorInput(this.id)" value="${client.firstName}">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientFirstNameHelp"></div>
						</div>

						<div class="form-group" id="clientLastNameForm">
							<input type="text" class="form-control" name="clientLastName"
								placeholder="*Last Name" maxlength="45" id="clientLastName"
								oninput="errorInput(this.id)" value="${client.lastName }">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientLastNameHelp"></div>
						</div>

						<div class="form-group" id="clientPasswordForm">
							<input type="password" class="form-control" name="clientPassword"
								id="clientPassword" placeholder="*Password"
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientPasswordHelp"></div>
						</div>

						<div class="form-group">
							<input type="email" class="form-control" name="clientEmail"
								placeholder="E-mail" maxlength="45" value="${client.email }">
						</div>

						<div class="form-group">
							<input type="text" class="form-control bfh-phone"
								data-country="createClientCountry" name="clientTelephone"
								placeholder="Phone Number" value="${client.phoneNo }">
						</div>

						<div class="form-group" id="clientBankerIDForm">
							<input type="text" class="form-control" name="clientBankerID"
								id="clientBankerID" placeholder="*Associated Banker"
								value="${client.bankerID}" maxlength="7"
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientBankerIDHelp"></div>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="street"
								placeholder="Address" value="${client.street}">
						</div>

						<div class="form-group" id="clientCityForm">
							<input type="text" class="form-control" name="clientCity"
								id="clientCity" placeholder="*City Postal Code" maxlength="45"
								oninput="numberError(this.id)" value="${client.postal}">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientCityHelp"></div>
						</div>

						<div class="form-group">
							<select class="form-control bfh-countries" data-country="Denmark"
								id="createClientCountry" name="clientCountry"></select>
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="EditClient">Save Client Changes</button>
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