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
			<div class="card card-outline-primary text-center blue-outline">
				<h1>Delete Banker!</h1>
				<div class="card-block">
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
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>