<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>View Client</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body onload="deleteClientStatus()">
	<jsp:include page="AdminNavbar.jsp" />

	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>View Clients</h1>
				<div class="card-block">
					<form action="" method="post">
						<div class="input-group col-8 offset-2">
							<input class="form-control" type="text"
								placeholder="Search Clients" name="search-term"> <span
								class="input-group-btn">
								<button class="btn btn-primary btn-action" type="submit"
									value="search-client" name="search-client">
									<img
										src="../assets/images/glyphicons_free/glyphicons/png/glyphicons-28-search-white.png"
										width="15" height="15" aria-hidden="true" />
								</button>
							</span>
						</div>
					</form>
					<div class="pad-row">
					<div class="form-control-feedback hidden-xs-up"
						id="deleteClientResult" role="alert">
						<input type="hidden" value="${deleteClientStatus}"
							id="deleteClientBool"> <input type="hidden"
							value="${deleteClient}" id="clientName">
					</div>
						<table class="table table-hover">
							<thead>
								<th></th>
								<th></th>
								<th></th>
							</thead>
							<tbody>
								<c:forEach var="ob" items="${list}">
									<tr data-toggle="collapse" data-target="#${ob.clientID}"
										class="clickable">
										<td><c:out value="${ob.clientID}" /></td>
										<td><c:out value="${ob.firstName}" /></td>
										<td><c:out value="${ob.lastName}" /></td>
									</tr>
									<tr>
										<td colspan="3">
											<div id="${ob.clientID}" class="collapse">
												<ul>
													<li>CPR: ${ob.CPR}</li>
													<li>Email: ${ob.email}</li>
													<li>Mobile: ${ob.phoneNo}</li>
													<li>Street: ${ob.street}</li>
													<li>City: ${ob.postal}</li>
													<li>Country: ${ob.country}</li>
												</ul>
												<form action="" method="post">
													<button class="btn btn-primary btn-block btn-action"
														type="submit" name="username" value="${ob.clientID}">Delete</button>
												</form>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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