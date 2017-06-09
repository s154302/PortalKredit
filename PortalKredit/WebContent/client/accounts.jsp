<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Accounts overview</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="ClientNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary text-center blue-outline">
				<h1>Accounts</h1>
				<div class="card-block">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Number</th>
								<th>Name</th>
								<th>Balance</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${accounts}">
								<tr data-toggle="collapse"
									data-target="#${ob.regNo}${ob.accountNumber}" class="clickable">
									<td><c:out value="${ob.regNo} ${ob.accountNumber}" /></td>
									<td><c:out value="${ob.accountName}" /></td>
									<td><c:out value="${ob.balance}" /></td>
								</tr>
								<tr>
									<td colspan="4">
										<div id="${ob.regNo}${ob.accountNumber}" class="collapse">
											<ul>
												<li>${ob.transactions[0].dateOfTransaction}
													${ob.transactions[0].note} Amount:
													${ob.transactions[0].amount} Balance:
													${ob.transactions[0].balance}
													${ob.currency}</li>
												<li>${ob.transactions[1].dateOfTransaction}
													${ob.transactions[1].note} Amount:
													${ob.transactions[1].amount} Balance:
													${ob.transactions[1].balance}
													${ob.currency}</li>
												<li>${ob.transactions[2].dateOfTransaction}
													${ob.transactions[2].note} Amount:
													${ob.transactions[2].amount} Balance:
													${ob.transactions[2].balance}
													${ob.currency}</li>
											</ul>
											<form action="" method="post">
												<input type="hidden" name="accountNumber"
													value="${ob.accountNumber}" /> <input type="hidden"
													name="regNo" value="${ob.regNo}" />
												<button class="btn btn-primary btn-block btn-action"
													type="submit" value="${ob.accountNumber}">View
													Account</button>
											</form>
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