<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<nav
		class="navbar navbar-toggleable-md navbar-inverse bg-primary custom-navbar">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarNavDropdown"
			aria-controls="navbarNavDropdown" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="#"><img
			src="../assets/images/logo.png" width="60" height="60" alt=""></a>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="AdminControl.jsp">Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Create</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="AdminCreateClient.jsp">Create
							Client</a> <a class="dropdown-item" href="AdminCreateBanker.jsp">Create
							Banker</a> <a class="dropdown-item" href="AdminCreateAdmin.jsp">Create
							Admin</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Delete</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="AdminDeleteClient">Delete
							Client</a> <a class="dropdown-item" href="AdminDeleteBanker">Delete
							Banker</a> <a class="dropdown-item" href="AdminDeleteAdmin">Delete
							Admin</a>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="row pad-row">
		<div class="col-6 offset-3">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Delete Banker!</h1>
				<div class="card-block">
					<table class="table table-bordered">
						<thead>



							<tr>
								<th>bankerID</th>
								<th>First name</th>
								<th>Last name</th>
								<th>regNo</th>
								<th>phoneNo</th>
								<th>email</th>
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
										<form action="" method="post">
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