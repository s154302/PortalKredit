<head>
<title>Account Type</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Create New Account Type</h1>
				<div class="card-block">
				<form action="CreateAccountType" method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="accountType"
							placeholder="*Account Type" maxlength="45" required>
					</div>
					
					<div class="form-group">
							<input type="text" pattern="[0-9]+([.][0-9]+)?" class="form-control"
								name="interestRate" placeholder="*Interest Rate"
								title="Please only input Numbers. E.g. 0.4" required>
					</div>
					
					<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createAccountType">Create Account Type</button>
				</form>
			</div>
			<p style="color: red;">${createAccountTypeStatus}</p>
		</div>
	</div>
</div>
		
		
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>