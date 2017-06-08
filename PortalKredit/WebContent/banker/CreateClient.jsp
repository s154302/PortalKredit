<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Create Client</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/custom.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
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
				<p style="color:red;">
				${createClientStatus}
				</p>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>
</html>