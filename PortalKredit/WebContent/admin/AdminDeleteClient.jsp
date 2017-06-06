<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="AdminNavbar.jsp"/>
	<div class="row pad-row">
		<div class="col-4 offset-4 text-center">
			<h1>Delete Client!</h1>
			<form action="" method="post">
				<div class="form-inline row offset-1">
					<input class="form-control mb-2 mr-sm-2 mb-sm-0" type="text"
						placeholder="Search Clients" name="search-term">

					<button class="btn btn-primary btn-action" type="submit"
						value="search-client" name="search-client">
						<img
							src="../assets/images/glyphicons_free/glyphicons/png/glyphicons-28-search-white.png"
							width="15" height="15" aria-hidden="true" />Search
					</button>
				</div>
			</form>

			
			<div class="card card-outline-primary blue-outline">
				<div class="card-block">
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>