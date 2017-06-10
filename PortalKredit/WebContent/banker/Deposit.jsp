<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Payment</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>

	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<h1>Deposit</h1>
				<p style="color: red;">${status}</p>
				<div class="card-block">
					<form action="" method="post">
						<div class="form-group">
							<input type="text" class="form-control" name="reciAcc"
								placeholder="*Account Number" required value="${reciAcc}">
						</div>
						
						<div class="form-group">
							<input type="text" class="form-control" name="amount"
								placeholder="*Amount" required value="${amount}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="currency"
								placeholder="*Currency" required value="${currency}">
						</div>

						<div class="form-group">
							<input type="password" class="form-control" name="password"
								placeholder="*Enter password" required>
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="makeDeposit">Make Deposit</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>