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

<body>
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Delete Admin!</h1>
				<div class="card-block">
					<table class="table table-sm table-bordered">
						<thead>

							<tr>
								<th>Login</th>
								<th>Delete</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${list}">
								<tr>
									<td><c:out value="${ob.username}" /></td>
									<td>
										<form action="" method="post">
											<button class="btn btn-sm btn-primary btn-block btn-action"
												type="submit" name="username" value="${ob.username}">Delete</button>
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

</html>