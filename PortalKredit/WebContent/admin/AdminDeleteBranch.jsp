<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Delete branch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="card card-outline-primary text-center blue-outline">
			<h1>Delete branch</h1>
			<div class="card-block">
				<p style="color: red;">${status}</p>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>Reg. No</th>
							<th>Bank Name</th>
							<th>Postal</th>
							<th>City</th>
							<th>Country</th>
							<th>Street</th>
							<th>Phone</th>
							<th>Delete</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="ob" items="${branches}">
							<tr>
								<td><c:out value="${ob.regNo}" /></td>
								<td><c:out value="${ob.bankName}" /></td>
								<td><c:out value="${ob.postal}" /></td>
								<td><c:out value="${ob.city}" /></td>
								<td><c:out value="${ob.country}" /></td>
								<td><c:out value="${ob.street}" /></td>
								<td><c:out value="${ob.phone}" /></td>

								<td>
									<form action="" method="post">
										<button class="btn btn-primary btn-block btn-action"
											type="submit" name="regNo" value="${ob.regNo}">Delete</button>
									</form>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>

</html>