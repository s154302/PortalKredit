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
						<tr>Name: ${account.accountName} <br></tr>
						<tr>Reg: ${account.regNo} <br></tr>
						<tr>Account: ${account.accountNumber} <br></tr>
						<tr>Type: ${account.accountType} <br></tr>
						<tr>Currency: ${account.currency} <br></tr>
						<tr>Interest rate: ${account.interestRate} <br></tr>
						<tr>Balance: ${account.balance}</tr>
					</tbody>
				</table>
			</div>
		</div>
		</div>
	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary mb-3 text-center blue-outline">
				<div class="card-block">
					<form action="EditAccount" method="post">
						
						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountName"
								placeholder="Account Name" value="${account.accountName}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientAccountType"
								placeholder="*Account Type" value="${account.accountType}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientID"
								placeholder="*Client's ID" value="${clientID}">
						</div>

						<div class="form-group">
							<input type="text" class="form-control" name="clientCurrency"
								placeholder="*Client's prefered Currency" value="${account.currency}">
						</div>

						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="editAccount">Save Account Changes</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>