<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Welcome</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet" >
</head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-sm-4 sidebar1">
				<div class="logo">
					<img src="../assets/images/logo.png"
						class="img-responsive center-block" alt="Logo" style="width: 70%">
				</div>
				<br>
				<div class="left-navigation">
					<ul class="list">
						<h5 style="color: rgba(255, 255, 255, 0.5)">
							<strong>CREATE CLIENT</strong>
						</h5>
						<li><a href="AdminCreateClient.jsp">Create
								Client</a></li>
						<li><a href="AdminCreateBanker.jsp">Create
								Banker</a></li>
						<li><a href="AdminCreateAdmin.jsp">Create
								Admin</a></li>
						<li><a href="AdminDeleteClient.jsp">Delete
								Client</a></li>
						<li><a href="AdminDeleteBanker.jsp">Delete
								Banker</a></li>
						<li><a href="AdminDeleteAdmin">Delete
								Admin</a></li>

					</ul>
				</div>
			</div>
			<div class="col-md-10 col-sm-8 main-content">
				<h1>Create Client!</h1>
				<div class="row">
					<div class="col-4 offset-4">
						<div class="card card-outline-primary mb-3 text-center blue-outline">
							<div class="card-block">
								<form action="CreateClientServlet" method="post">
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
											placeholder="*Associated Banker">
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
			</div>
		</div>
	</div>
	<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>