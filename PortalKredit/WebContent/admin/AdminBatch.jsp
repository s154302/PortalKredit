<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Batch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
</head>
<body onload="batch()">
	<jsp:include page="AdminNavbar.jsp"></jsp:include>

	<div class="center-text col-4 offset-4 batch">
		<div class="form-control-feedback hidden-xs-up text-center" id="batchResult"
			role="alert">
			<input type="hidden" value="${status}" id="status">
		</div>
		<div class="btn-group-vertical">
			<form class="form-inline" action="AdminBatchServlet" method="post">
				<button class="btn btn-warning btn-block" type="submit"
					name="exchangeRate">Update Exchange Rates</button>

				<button class="btn btn-warning btn-block" type="submit"
					name="insertExchangeRate">Insert Exchange Rates</button>

				<button class="btn btn-warning btn-block" type="submit"
					name="dInterestRate">Update Daily Interest Rates</button>

				<button class="btn btn-warning btn-block" type="submit"
					name="yInterestRate">Update Yearly Interest Rates</button>
				<button class="btn btn-warning btn-block" type="submit"
					name="backupTransactions">Backup transactions</button>
			</form>
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