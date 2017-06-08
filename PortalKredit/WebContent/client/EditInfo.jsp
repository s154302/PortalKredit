<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Transactions</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>


<body>
	<jsp:include page="ClientNavbar.jsp"></jsp:include>
	

	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<div class="card-block">
					<form action="" method="post">
						
						<div class="form-group">
							<input type="text" class="form-control" name="firstName"
								placeholder="clientID" value="${user.clientID}" readonly>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="firstName"
								placeholder="First Name" value="${user.firstName}" readonly>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="lastName"
								placeholder="Last Name" value="${user.lastName}" readonly>
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
							<input type="text" class="form-control" name="bankerID"
								placeholder="Associated Banker" value="${user.bankerID}" readonly>
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="street"
								placeholder="Address" value="${user.street}" >
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="postal"
								placeholder="City Postal Code" value="${user.postal}">
						</div>

						<div class="form-group">
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