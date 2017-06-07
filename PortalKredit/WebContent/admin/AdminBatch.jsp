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
				<button class="btn btn-primary btn-block" type="submit"
					name="exchangeRates">Update Exchange Rates</button>

				<button class="btn btn-primary btn-block" type="submit"
					name="dInterestRates">Update Daily Interest Rates</button>
					
				<button class="btn btn-primary btn-block" type="submit"
					name="yInterestRates">Update Yearly Interest Rates</button>
			</form>
		</div>
	</div>

</body>
</html>