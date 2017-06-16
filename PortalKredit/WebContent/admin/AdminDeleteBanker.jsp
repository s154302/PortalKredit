<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="deleteBankerStatus()">
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="card card-outline-primary text-center blue-outline">
			<h1>Delete Banker!</h1>
			<div class="card-block">
				<div class="form-control-feedback hidden-xs-up"
					id="deleteBankerResult" role="alert">
					<input type="hidden" value="${deleteBankerStatus}"
						id="deleteBankerBool"> <input type="hidden"
						value="${deleteBanker}" id="bankerName">
				</div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>bankerID</th>
							<th>First name</th>
							<th>Last name</th>
							<th>Reg. No.</th>
							<th>Phone No.</th>
							<th>E-mail</th>
							<th>Delete</th>
						</tr>
					</thead>

					<tbody>

						<c:forEach var="ob" items="${list}">
							<tr>
								<td><c:out value="${ob.bankerID}" /></td>
								<td><c:out value="${ob.firstName}" /></td>
								<td><c:out value="${ob.lastName}" /></td>
								<td><c:out value="${ob.regNo}" /></td>
								<td><c:out value="${ob.phoneNo}" /></td>
								<td><c:out value="${ob.email}" /></td>

								<td>
									<form action="DeleteBankerServlet" method="post">
										<button class="btn btn-primary btn-block btn-action"
											type="submit" name="username" value="${ob.bankerID}">Delete</button>
									</form>
							</tr>
						</c:forEach>

					</tbody>
				</table>
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