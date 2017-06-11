
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
					<tbody colspan="6">
						<tr>Name: ${banker.fullName} <br></tr>
						<tr>Email: ${banker.email} <br></tr>
						<tr>Advisor Phone: ${banker.phoneNo} <br></tr>
						<tr>Bank Name: ${branch.bankName} <br></tr>
						<tr>Bank Address: ${branch.fullAddress} <br></tr>
						<tr>Bank Phone: ${branch.phone} <br></tr>
					</tbody>
				</table>
			</div>
		</div>
</div>
	

	<div class="form-group">

		<textarea class="form-control col-4 offset-4" id="exampleTextarea"
			rows="12"></textarea>
		<form action="" method="post">
			<button class="btn btn-primary btn-block col-4 offset-4"
				type="submit" name="contactAdvisor"> Send help pls
				</button>
		</form>
	</div>




	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>