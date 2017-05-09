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
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-sm-4 sidebar1">
				<div class="logo">
					<img src="../assets/images/logo.png"
						class="img-responsive center-block" alt="Logo" style="width: 110">
				</div>
				<br>
				<div class="left-navigation">
					<ul class="list">
						<h5 style="color: rgba(255, 255, 255, 0.5)">
							<strong>CREATE BANKER</strong>
						</h5>
						<li><a href="AdminCreateClient.jsp">Create Client</a></li>
						<li><a href="AdminCreateBanker.jsp">Create Banker</a></li>
						<li><a href="AdminCreateAdmin.jsp">Create Admin</a></li>
						<li><a href="AdminDeleteClient.jsp">Delete Client</a></li>
						<li><a href="AdminDeleteBanker.jsp">Delete Banker</a></li>
						<li><a href="AdminDeleteAdmin.jsp">Delete Admin</a></li>

					</ul>
				</div>
			</div>
			<div class="col-md-10 col-sm-8 main-content">
				<h1>Create Banker</h1>
				<div class="row">
					<div class="col-4 offset-4">
						<div
							class="card card-outline-primary mb-3 text-center blue-outline">
							<div class="card-block">
								<form action="CreateBankerServlet" method="post">
									<div class="form-group">
										<input type="text" class="form-control" name="bankerFirstName"
											placeholder="*First Name">
									</div>

									<div class="form-group">
										<input type="text" class="form-control" name="bankerLastName"
											placeholder="*Last Name">
									</div>

									<div class="form-group">
										<input type="text" class="form-control" name="bankerPassword"
											placeholder="*Password">
									</div>

									<div class="form-group">
										<input type="email" class="form-control" name="bankerEmail"
											placeholder="E-mail">
									</div>

									<div class="form-group">
										<input type="text" class="form-control" name="bankerTelephone"
											placeholder="Phone Number">
									</div>

									<div class="form-group">
										<input type="text" class="form-control" name="bankerReg"
											placeholder="*Registration Number">
									</div>

									<button class="btn btn-primary btn-block btn-action"
										type="submit" value="createClient">Create Banker</button>
								</form>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
</body>
</html>