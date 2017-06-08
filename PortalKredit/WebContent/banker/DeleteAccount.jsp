<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>

<head>
<title>Delete Account</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				
				<div class="card-block">
					<form action="DeleteAccount" method="post">
						<div class="form-group">
							<p>Confirm you want to delete</p>
							<p style="color:red;">  ${account.accountName} <br /> (${account.regNo} ${account.accountNumber}) </p>
							<p>Balance: ${account.balance} </p>
							<p>Banker: ${userID} </p>
						</div>
						<div class="form-group">
							<input type="password" class="form-control"
								name="password" placeholder="Password" required >
						</div>
						<button class="btn btn-primary btn-block btn-action" type="submit" name = "confirm" value="confirm">Confirm</button>
					</form>
					<form action="DeleteAccount" method="post">
						<button class="btn btn-primary btn-block btn-action" type="submit" name = "cancel"value="cancel">Cancel</button>
					</form>
				</div>
				<p style="color:red;">
				${deleteStatus}
				</p>
			</div>
		</div>
	</div>

	<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>