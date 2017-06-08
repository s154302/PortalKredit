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
				<p style="color:green;"> ${deleteStatus}</p>
				<h1>${clientName}'s Accounts</h1>
				<div class="card-block">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Account Number</th>
								<th> Balance</th>
							</tr>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${accounts}">
								<tr data-toggle="collapse" data-target="#${ob.accountNumber}"
									class="clickable">
									<td><c:out value="${ob.accountNumber}" /></td>
									<td><c:out value="${ob.balance}" /></td>
								</tr>
								<tr>
									<td colspan="2">
										<div id="${ob.accountNumber}" class="collapse">
											<ul>
												<li>Date: ${ob.transactions[0].dateOfTransaction} Ammount: ${ob.transactions[0].amount}</li>
												<li>Date: ${ob.transactions[1].dateOfTransaction} Ammount: ${ob.transactions[1].amount}</li>
												<li>Date: ${ob.transactions[2].dateOfTransaction} Ammount: ${ob.transactions[2].amount}</li>
											</ul>
											<form action="" method="post">
												<input type="hidden" name="accountNumber" value="${ob.accountNumber}"/>
												<input type="hidden" name="regNo" value="${ob.regNo}"/>	
												<button class="btn btn-primary btn-block btn-action"
													type="submit" value="${ob.accountNumber}">View Account</button>
											</form>
										</div>
									</td>
								</tr>
							</c:forEach>
								<form class="btn btn-primary btn-block btn-action" action="CreateAccount" method="get">
      								<button class="btn btn-primary btn-block btn-action" type="submit">Create Account</button>
    							</form>
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