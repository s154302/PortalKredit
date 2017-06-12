<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Batch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="AdminNavbar.jsp"></jsp:include>

	<div class="center-text col-4 offset-4 batch">
		<div class="btn-group-vertical">
			<form class="form-inline" action="AdminBatchServlet" method="post">
				<p style="color: red">${status}</p>
				<button class="btn btn-primary btn-block" type="submit"
					name="exchangeRate">Update Exchange Rates</button>

				<button class="btn btn-primary btn-block" type="submit"
					name="insertExchangeRate">Insert Exchange Rates</button>

				<button class="btn btn-primary btn-block" type="submit"
					name="dInterestRate">Update Daily Interest Rates</button>

				<button class="btn btn-primary btn-block" type="submit"
					name="yInterestRate">Update Yearly Interest Rates</button>
				<button class="btn btn-primary btn-block" type="submit"
					name="backupTrsansactions">Backup transactions</button>
			</form>
		</div>
	</div>

</body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</html>