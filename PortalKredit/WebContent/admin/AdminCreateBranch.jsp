<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Create Branch</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="AdminNavbar.jsp" />
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create branch</h1>
				<div class="card-block">
					<p style="color:red;">${status}</p>
					<form action="" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="regNo"
								placeholder="*Reg No" maxlength="4" required value="${regNo}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="bankName"
								placeholder="*Bank Name" maxlength="20" required value="${bankName}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="postal"
								placeholder="*Postal" required value="${postal}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="country"
								placeholder="*Country" maxlength="45" required value="${country}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="street"
								placeholder="*Street" required value="${street}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control bfh-phone"
								name="phone" placeholder="*Phone No" required value="${phone}">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createBranch">Create Branch</button>
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