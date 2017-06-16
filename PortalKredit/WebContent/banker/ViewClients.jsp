<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>View Clients</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-6 offset-3">
			<div class="card card-outline-primary text-center blue-outline">
				<h1 class="pad-row">View Clients</h1>
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
				<div class="card-block">
					<table class="table table-hover">
						<thead>
							<th></th>
							<th></th>
							<th></th>
						</thead>
						<tbody>
							<c:forEach var="client" items="${list}">
								<tr class="text-center" data-toggle="collapse" data-target="#${client.clientID}"
									class="clickable">
									<td><c:out value="${client.clientID}" /></td>
									<td><c:out value="${client.firstName}" /></td>
									<td><c:out value="${client.lastName}" /></td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="${client.clientID}" class="collapse">
											<dl class="row">
												<dt class="col-sm-3 offset-3">CPR:</dt>
												<dd class="col-sm-6">${client.CPR}</dd>

												<dt class="col-sm-3 offset-3">Email:</dt>
												<dd class="col-sm-6">${client.email}</dd>

												<dt class="col-sm-3 offset-3">Mobile:</dt>
												<dd class="col-sm-6">${client.phoneNo}</dd>

												<dt class="col-sm-3 offset-3">Street:</dt>
												<dd class="col-sm-6">${client.street}</dd>

												<dt class="col-sm-3 offset-3">City:</dt>
												<dd class="col-sm-6">${client.postal}</dd>

												<dt class="col-sm-3 offset-3">Country:</dt>
												<dd class="col-sm-6">${client.country}</dd>
											</dl>
											<form action="" method="post">
												<button class="btn btn-primary btn-block btn-action col-sm-8 offset-2"
													type="submit" name="ViewUsername" value="${client.clientID}">View
													Accounts</button>
												<button class="btn btn-primary btn-block btn-action col-sm-8 offset-2"
													type="submit" name="EditUsername" value="${client.clientID}">Edit
													Client</button>
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/bootstrap-formhelpers.js"></script>
	<script src="../assets/js/form-input.js"></script>
	<script src="../assets/js/error-messages.js"></
</body>