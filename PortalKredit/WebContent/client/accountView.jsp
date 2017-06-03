<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Transactions</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<nav
		class="navbar navbar-toggleable-md navbar-inverse bg-primary custom-navbar">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarNavDropdown"
			aria-controls="navbarNavDropdown" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="#"><img
			src="../assets/images/logo.png" width="60" height="60" alt=""></a>
		<div class="collapse navbar-collapse"
			id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="frontpage">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="accounts">Accounts</a></li>
				<li class="nav-item active"><a class="nav-link" href="payments">Payments</a></li>
				<li class="nav-item active"><a class="nav-link" href="contact">Contact</a></li>
			</ul>
		</div>
	</nav>
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
			<div class="card card-outline-primary text-center blue-outline">
				<h1>Transactions</h1>

				
				<div class="card-block">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Date</th>
								<th>Note</th>
								<th>Amount</th>
								<th>Balance</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${transactions}">
								<tr data-toggle="collapse" data-target="#${ob.transactionID}${ob.regNo}${ob.accountNumber}"
									class="clickable">
									<td><c:out value="${ob.dateOfTransaction}" /></td>
									<td><c:out value="${ob.note}" /></td>
									<td><c:out value="${ob.amount}" /></td>
									<td><c:out value="${ob.balance}" /></td>
								</tr>
								<tr>
									<td colspan="4">
										<div id="${ob.transactionID}${ob.regNo}${ob.accountNumber}" class="collapse">
											<ul>
												<li>ID: ${ob.transactionID}</li>
												<li>RegNo: ${ob.recieveRegNo}</li>
												<li>AccountNumber: ${ob.recieveAccount}</li>
												<li>Currency: ${ob.currency}</li>
											</ul>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>