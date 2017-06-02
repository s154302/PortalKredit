<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
<title>Welcome</title>
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
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link" href="frontpage.jsp">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="ViewClients">View Clients</a></li>
				<li class="nav-item active"><a class="nav-link" href="CreateClient">Create Client</a></li>
			</ul>
		</div>
						<div>
		<form class="form-inline my-2 my-lg-0" action="LogOutServlet" method="post">
      
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Log Out</button>
    </form>
		</div>			
	</nav>
	
	<div class="row pad-row">
		<div class="col-4 offset-4">
			<div class="card card-outline-primary text-center blue-outline">
				<h1>View Clients</h1>
				<div class="card-block">
					<table class="table table-hover">
						<thead>
							<th></th>
							<th></th>
							<th></th>
						</thead>

						<tbody>

							<c:forEach var="ob" items="${list}">
								<tr data-toggle="collapse" data-target="#${ob.clientID}"
									class="clickable">
									<td><c:out value="${ob.clientID}" /></td>
									<td><c:out value="${ob.firstName}" /></td>
									<td><c:out value="${ob.lastName}" /></td>
								</tr>
								<tr>
									<td colspan="3">
										<div id="${ob.clientID}" class="collapse">
											<ul>
												<li>CPR: ${ob.CPR}</li>
												<li>Email: ${ob.email}</li>
												<li>Mobile: ${ob.phoneNo}</li>
												<li>Street: ${ob.street}</li>
												<li>City: ${ob.postal}</li>
												<li>Country: ${ob.country}</li>
											</ul>
											<form action="" method="post">
												<button class="btn btn-primary btn-block btn-action"
													type="submit" name="username" value="${ob.clientID}">View</button>
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