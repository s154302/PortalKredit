<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<head>
<title>View Accounts</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="../assets/css/custom.css">
</head>

<body>
	<jsp:include page="BankerNavbar.jsp"></jsp:include>
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary blue-outline">
				<h1 class="text-center">${account.accountName }</h1>
				<dl class="row">
					<dt class="col-sm-3 offset-3">Reg. No.:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.regNo }</dd>

					<dt class="col-sm-3 offset-3">Account:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.accountNumber }</dd>

					<dt class="col-sm-3 offset-3">Type:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.accountType }</dd>

					<dt class="col-sm-3 offset-3">Currency:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.currency }</dd>

					<dt class="col-sm-3 offset-3">Interest Rate:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.interestRate }</dd>

					<dt class="col-sm-3 offset-3">Balance:</dt>
					<dd class="col-sm-3" style="text-align: right">${account.balance }</dd>

				</dl>
				<form action="ViewClientAccount" method="post">
					<div class="container col-sm-8 offset-2">
						<button class="btn btn-primary btn-block btn-action" type="submit"
							name="EditAccount" value="EditAccount">Edit Account</button>
						<button class="btn btn-primary btn-block btn-action" type="submit"
							name="DeleteAccount" value="DeleteAccount">Delete
							Account</button>
					</div>
				</form>

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

							<c:forEach var="transaction" items="${transactions}">
								<tr data-toggle="collapse"
									data-target="#${transaction.transactionID}${transaction.regNo}${transaction.accountNumber}"
									class="clickable">
									<td><c:out value="${transaction.dateOfTransaction}" /></td>
									<td><c:out value="${transaction.note}" /></td>
									<td><c:out value="${transaction.amount}" /></td>
									<td><c:out value="${transaction.balance}" /></td>
								</tr>
								<tr>
									<td colspan="4">
										<div
											id="${transaction.transactionID}${transaction.regNo}${transaction.accountNumber}"
											class="collapse">
											<dl class="row">
												<dt class="col-sm-3 offset-3">ID:</dt>
												<dd class="col-sm-6">${transaction.transactionID}</dd>

												<dt class="col-sm-3 offset-3">Reg. No.:</dt>
												<dd class="col-sm-6">${transaction.recieveRegNo}</dd>

												<dt class="col-sm-3 offset-3">Account:</dt>
												<dd class="col-sm-6">${transaction.recieveAccount}</dd>

												<dt class="col-sm-3 offset-3">Currency:</dt>
												<dd class="col-sm-6">${transaction.currency}</dd>
											</dl>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<form action="" method="post">
						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="">Load old transactions</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
</body>