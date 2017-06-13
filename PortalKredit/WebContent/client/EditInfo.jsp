<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Client Info</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>


<body>
	<jsp:include page="ClientNavbar.jsp"></jsp:include>
	

	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 blue-outline">
				<div class="card-block">
				<h1 class="text-center">Client Info</h1>
					<form action="" method="post">
						<div class="input-group form-group">
						<span class="input-group-addon" id="clientID">Client ID</span>
							<input type="text" class="form-control" name="firstName"
								placeholder="clientID" value="${user.clientID}" readonly>
						</div>
						<div class="input-group form-group">
						<span class="input-group-addon" id="fullName">Name</span>
							<input type="text" class="form-control" name="fullName"
								placeholder="Full Name" value="${user.firstName} ${user.lastName}" readonly>
						</div>
						
						<div class="form-group">
							<input type="text" class="form-control" name="password"
								placeholder="Password" >
						</div>
						<div class="form-group">
							<input type="email" class="form-control" name="email"
								placeholder="E-mail" value="${user.email}">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="mobolie"
								placeholder="Mobile" value="${user.phoneNo}" >
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="street"
								placeholder="Address" value="${user.street}" >
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="postal"
								placeholder="City Postal Code" value="${user.postal}">
						</div>

						<div class="form-group input-group">
						<span class="input-group-addon" id="country">Country</span>
							<input type="text" class="form-control" name="country"
								placeholder="Country" value="${user.country}" readonly>
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="EditClient">Save Changes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>