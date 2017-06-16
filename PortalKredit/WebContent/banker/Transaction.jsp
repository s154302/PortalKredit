<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Payment</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/bootstrap-formhelpers.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="transaction()">
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1 class="pad-row">New Transaction</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group" id="fromAccountForm">
							<input type="text" class="form-control" name="fromAccount"
								id="fromAccount" placeholder="*From Account" required
								value="${fromAccount}" oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="fromAccountHelp"></div>
						</div>

						<div class="form-group" id="amountForm">
							<input type="text" class="form-control" name="amount" id="amount"
								placeholder="*Amount" required value="${amount}"
								oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="amountHelp"></div>
						</div>

						<div class="form-group">
							<select class="form-control bfh-currencies" name="currency"
								data-currency="DKK"></select>
						</div>

						<div class="form-group" id="messageForm">
							<input type="text" class="form-control" name="message"
								id="message" placeholder="*Message" required value="${message}"
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="messageHelp"></div>
						</div>

						<div class="form-group" id="reciRegForm">
							<input type="text" class="form-control" name="reciReg"
								id="reciReg" placeholder="*Reg no" required value="${reciReg}"
								oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="reciRegHelp"></div>
						</div>

						<div class="form-group" id="reciAccForm">
							<input type="text" class="form-control" name="reciAcc"
								id="reciAcc" placeholder="*Account Number" required
								value="${reciAcc}" oninput="numberError(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="reciAccHelp"></div>
						</div>

						<div class="form-group" id="reciMessageForm">
							<input type="text" class="form-control" name="reciMessage"
								id="reciMessage" placeholder="*Message to reciever" required
								value="${reciMessage}" oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="reciMessageHelp"></div>
						</div>

						<div class="form-group" id="passwordForm">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="*Enter password" required
								oninput="checkEmpty(this.id)">
							<div
								class="form-control-feedback alert alert-danger hidden-xs-up"
								id="passwordHelp"></div>
						</div>

						<div class="form-control-feedback hidden-xs-up"
							id="transactionResult" role="alert">
							<input type="text" class="form-control hidden-xs-up"
								value="${status}" id="status">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="submitPayment">Submit payment</button>
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