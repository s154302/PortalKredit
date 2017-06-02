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
				<h1>Create Account for Client</h1>
				<div class="card-block">
					<form action="CreateAccount" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountNumber"
								placeholder="*Account Number">
						</div>

						<div class="form-group">
							<input type="number" class="form-control" name="clientRegNo"
								placeholder="*Reg. No.">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountType"
								placeholder="*Account Type">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientID"
								placeholder="*Client's ID">
						</div>

						<div class="form-group">
							<input type="number" class="form-control" name="clientBalance"
								placeholder="*Balance">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientCurrency"
								placeholder="Client's prefered Currency">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAccount">Create Account</button>
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