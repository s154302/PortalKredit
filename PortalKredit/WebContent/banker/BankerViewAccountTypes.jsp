<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Account Types</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"/>
	<div class="row pad-row">
			<div class="card card-outline-primary text-center blue-outline">
				<h1>Account Types</h1>
				<div class="card-block">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Account Type</th>
								<th>Interest Rate</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${accountTypes}">
								<tr>
									<td><c:out value="${ob.name}" /></td>
									<td><c:out value="${ob.interestRate}" /></td>

									<td>
										<form action="" method="post">
											<button class="btn btn-primary btn-block btn-action"
												type="submit" name="username" value="${ob.name}">Delete</button>
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