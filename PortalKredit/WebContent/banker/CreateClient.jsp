<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
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
				<li class="nav-item active"><a class="nav-link" href="frontpage.jsp">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="ViewClients">View Clients</a></li>
				<li class="nav-item active"><a class="nav-link" href="CreateClient">Create Client</a></li>
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
				<h1>Create Client!</h1>
				<div class="card-block">
					<form action="CreateClient" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="clientFirstName"
								placeholder="*First Name">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientLastName"
								placeholder="*Last Name">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientPassword"
								placeholder="*Password">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientCPR"
								placeholder="*CPR">
						</div>

						<div class="form-group">
							<input type="email" class="form-control" name="clientEmail"
								placeholder="E-mail">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientTelephone"
								placeholder="Phone Number">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientBankerID"
								placeholder="*Associated Banker" value="${username}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientStreet"
								placeholder="Address">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientCity"
								placeholder="*City Postal Code">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientCountry"
								placeholder="*Country">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createClient">Create Client</button>
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