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
			src="../assets/images/logo.png" width="60" height="60" alt="logo"></a>
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
						<a class="dropdown-item" href="AdminDeleteClient">Delete Client</a> 
						<a class="dropdown-item" href="AdminDeleteBanker">Delete Banker</a> 
						<a class="dropdown-item" href="AdminDeleteAdmin">Delete Admin</a>
					</div>
				</li>
			</ul>
		</div>
				<div>
		<form class="form-inline my-2 my-lg-0" action="LogOutServlet" method="post">
      
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Log Out</button>
    </form>
		</div>
	</nav>
	<div class="row pad-row">
		<div class="col-4 offset-4 text-center">
			<h1>Delete Client!</h1>
			<form action="" method="post">
				<div class="form-inline row offset-1">
					<input class="form-control mb-2 mr-sm-2 mb-sm-3" type="text"
						placeholder="Search Clients" name="search-term">
				</div>
				<button class="btn btn-primary btn-action" type="submit"
					value="search-client" name="search-client">
					<img
						src="../assets/images/glyphicons_free/glyphicons/png/glyphicons-28-search-white.png"
						width="15" height="15" aria-hidden="true" />Search
				</button>
			</form>

			&nbsp;
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