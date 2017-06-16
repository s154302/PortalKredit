<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Edit Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>


<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>

	<div class="row pad-row">
		<div class="col-6 offset-3">
			<div class="card card-outline-primary blue-outline">
				<h1 class="text-center">Edit ${account.accountName}</h1>
				<dl class="row">
					<dt class="col-sm-3 offset-3">Reg. No.:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.regNo}</dd>

					<dt class="col-sm-3 offset-3">Account:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.accountNumber}</dd>

					<dt class="col-sm-3 offset-3">Type:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.accountType}</dd>

					<dt class="col-sm-3 offset-3">Currency:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.currency}</dd>

					<dt class="col-sm-3 offset-3">Interest rate:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.interestRate}</dd>

					<dt class="col-sm-3 offset-3">Balance:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.balance}</dd>
				</dl>
				<div class="card-block">
					<form action="EditAccount" method="post">

						<div class="form-group" id="clientAccountNameForm">
							<input type="text" class="form-control" name="clientAccountName"
								id="clientAccountName" placeholder="Account Name"
								value="${account.accountName}" oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientAccountNameHelp"></div>
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
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="clientIDHelp"></div>
						</div>

						<div class="form-group">
							<select class="form-control bfh-currencies"
								id="createClientCountry" name="clientCurrency"
								data-currency="${account.currency}"></select>
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="editAccount">Save Account Changes</button>
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