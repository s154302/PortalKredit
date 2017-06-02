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
	<nav
		class="navbar navbar-toggleable-md navbar-inverse bg-primary custom-navbar">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarNavDropdown"
			aria-controls="navbarNavDropdown" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="AdminControl.jsp"><img
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
				<div>
		<form class="form-inline my-2 my-lg-0" action="LogOutServlet" method="post">
      
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Log Out</button>
    </form>
		</div>
	</nav>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create Admin</h1>
				<div class="card-block">
					<form action="CreateAdminServlet" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="adminID"
								placeholder="Admin username">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="adminPassword"
								placeholder="Password">
						</div>
						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAdmin">Create Admin</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>
</html>