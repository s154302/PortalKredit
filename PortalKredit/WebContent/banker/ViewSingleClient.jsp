<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>Accounts overview</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary text-center blue-outline">
				<h1>${clientName}'s Accounts</h1>
				<div class="card-block">
					<form action="CreateAccount" method="get">
						<button class="btn btn-primary btn-block btn-action" type="submit">Create
							Account</button>
					</form>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Account Number</th>
								<th>Account Name</th>
								<th>Balance</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="account" items="${accounts}">
								<tr data-toggle="collapse" data-target="#${account.accountNumber}"
									class="clickable">
									<td><c:out value="${account.accountNumber}" /></td>
									<td><c:out value="${account.accountName}" /></td>
									<td><c:out value="${account.balance}" /></td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="${account.accountNumber}" class="collapse">
											<ul>
												<li>Date: ${account.transactions[0].dateOfTransaction}
													Amount: ${account.transactions[0].amount}</li>
												<li>Date: ${account.transactions[1].dateOfTransaction}
													Amount: ${account.transactions[1].amount}</li>
												<li>Date: ${account.transactions[2].dateOfTransaction}
													Amount: ${account.transactions[2].amount}</li>
											</ul>
											<form action="" method="post">
												<input type="hidden" name="accountNumber"
													value="${account.accountNumber}" /> <input type="hidden"
													name="regNo" value="${account.regNo}" />
												<button class="btn btn-primary btn-block btn-action"
													type="submit" value="${account.accountNumber}">View
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
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	<script src="../assets/js/bootstrap-formhelpers.js"></script>
	<script src="../assets/js/form-input.js"></script>
	<script src="../assets/js/error-messages.js"></script>
</body>