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
			<li class="nav-item active"><a class="nav-link"
				href="ViewClients">View Clients</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="CreateClient">Create Client</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="Deposit">Deposit</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="Transaction">Transaction</a></li>
			<li class="nav-item active"></li>	
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">Account Type</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="CreateAccountType">Create Account Type</a>
					<a class="dropdown-item" href="BankerViewAccountTypes">View Account Types</a>
				</div></li>
		</ul>
	</div>
	<p class="col-2" style="color: white">Logged in as: <br>${user.fullName}</p>
	<div>
		<form class="form-inline my-2 my-lg-0" action="LogOutServlet"
			method="post">

			<button class="btn btn-primary my-2 my-sm-0" type="submit">Log
				Out</button>
		</form>
	</div>
</nav>