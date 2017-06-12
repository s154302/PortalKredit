<nav
	class="navbar navbar-toggleable-md navbar-inverse bg-primary custom-navbar">
	<button class="navbar-toggler navbar-toggler-right" type="button"
		data-toggle="collapse" data-target="#navbarNavDropdown"
		aria-controls="navbarNavDropdown" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<a class="navbar-brand" href="AdminControl"><img
		src="../assets/images/logo.png" width="60" height="60" alt=""></a>
	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link"
				href="AdminControl">Home</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">Create</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="AdminCreateClient">Create Client</a>
					<a class="dropdown-item" href="AdminCreateBanker">Create Banker</a>
					<a class="dropdown-item" href="AdminCreateAdmin">Create Admin</a>
					<a class="dropdown-item" href="AdminCreateBranch">Create Branch</a>
				</div></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">Delete</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="AdminDeleteClient">Delete Client</a>
					<a class="dropdown-item" href="AdminDeleteBanker">Delete Banker</a>
					<a class="dropdown-item" href="AdminDeleteAdmin">Delete Admin</a>
					<a class="dropdown-item" href="AdminDeleteBranch">Delete Branch</a>
				</div></li>
			<li class="nav-item"><a class="nav-link"
				href="AdminBatch">Batch</a></li>
		</ul>
	</div>
	<p class="col-2" style="color: white">Logged in as: <br>${user.username}</p>
	<div>
		<form class="form-inline my-2 my-lg-0" action="LogOutServlet"
			method="post">
			<button class="btn btn-primary my-2 my-sm-0" type="submit">Log
				Out</button>
		</form>
	</div>
</nav>