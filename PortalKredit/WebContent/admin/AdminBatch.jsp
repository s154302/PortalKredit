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
	<form class="form-inline my-2 my-lg-0" action="LogOutServlet"
		method="post">
		<button class="btn btn-primary my-2 my-sm-0" type="submit">Update Exchange Rates</button>
	</form>
	<form class="form-inline my-2 my-lg-0" action="LogOutServlet"
		method="post">
		<button class="btn btn-primary my-2 my-sm-0" type="submit">Update Account Interest Rates</button>
	</form>
</body>
</html>