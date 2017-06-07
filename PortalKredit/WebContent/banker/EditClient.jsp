<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Transactions</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>


<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	
		<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary text-center blue-outline">
				<table class="table table-hover">
					<tbody colspan="4">
						<tr>Name: ${client.fullName} <br></tr>
						<tr>ClientID: ${client.clientID} <br></tr>
						<tr>CPR: ${client.CPR} <br></tr>
						<tr>BankerID: ${client.bankerID} <br></tr>
						<tr>Email: ${client.email} <br></tr>
						<tr>Mobile: ${client.phoneNo} <br></tr>
						<tr>Street: ${client.street} <br></tr>
						<tr>City: ${client.city} <br></tr>
						<tr>Country: ${client.country}</tr>
					</tbody>
				</table>
			</div>
		</div>
		</div>
	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<div class="card-block">
					<form action="EditClient" method="post">
						
						<div class="form-group">
							<input type="text" class="form-control" name="clientID"
								placeholder="Client ID" value="${client.clientID}">
						</div>
						
						<div class="form-group">
							<input type="text" class="form-control" name="firstName"
								placeholder="First Name" value="${client.firstName}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="lastName"
								placeholder="Last Name" value="${client.lastName}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="password"
								placeholder="Password">
						</div>

						<div class="form-group">
							<input type="email" class="form-control" name="email"
								placeholder="E-mail" value="${client.email}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="mobolie"
								placeholder="Mobile" value="${client.phoneNo}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="bankerID"
								placeholder="Associated Banker" value="${client.bankerID}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="street"
								placeholder="Address" value="${client.street}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="postal"
								placeholder="City Postal Code" value="${client.postal}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="country"
								placeholder="Country" value="${client.country}">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="EditClient">Save Client Changes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>