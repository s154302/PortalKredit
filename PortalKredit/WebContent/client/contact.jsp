
<head>
<title>Contact</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="ClientNavbar.jsp"></jsp:include>
	<h1 class="text-center">Contact your advisor</h1>

	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary text-center blue-outline">
				<table class="table table-hover">
					<tbody>
						<tr>
							Name: ${banker.fullName}
							<br>
						</tr>
						<tr>
							Email: ${banker.email}
							<br>
						</tr>
						<tr>
							Advisor Phone: ${banker.phoneNo}
							<br>
						</tr>
						<tr>
							Bank Name: ${branch.bankName}
							<br>
						</tr>
						<tr>
							Bank Address: ${branch.fullAddress}
							<br>
						</tr>
						<tr>
							Bank Phone: ${branch.phone}
							<br>
						</tr>
					</tbody>
				</table>

				<div class="form-group">
					<div class="container">
						<textarea class="form-control noresize" id="textarea" rows="12"></textarea>
					</div>
				</div>

				<div class="form-group">
					<form action="" method="post">
						<button class="btn btn-primary btn-block col-4 offset-4"
							type="submit" name="contactAdvisor">Send</button>
					</form>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>