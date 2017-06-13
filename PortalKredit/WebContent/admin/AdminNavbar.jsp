<nav
	class="navbar navbar-default navbar-toggleable-md navbar-inverse bg-primary custom-navbar">
	<div class="container">
		<button class="navbar-toggler navbar-toggler-left" type="button"
			data-toggle="collapse" data-target="#navbarNavDropdown"
			aria-controls="navbarNavDropdown" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<a class="navbar-brand" href="AdminControl"><img
			src="../assets/images/logo.png" width="50" height="50" alt=""></a>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="AdminControl">Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Create</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="AdminCreateClient">Create
							Client</a> <a class="dropdown-item" href="AdminCreateBanker">Create
							Banker</a> <a class="dropdown-item" href="AdminCreateAdmin">Create
							Admin</a> <a class="dropdown-item" href="AdminCreateBranch">Create
							Branch</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">View</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="AdminDeleteClient">Clients</a> <a
							class="dropdown-item" href="AdminDeleteBanker">Bankers</a> <a
							class="dropdown-item" href="AdminDeleteAdmin">Admins</a> <a
							class="dropdown-item" href="AdminDeleteBranch">Branches</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="AdminBatch">Batch</a></li>
			</ul>
		</div>
		<p class="col-2 navbar-texpad" style="color: white">Logged in as:
			${user.username}</p>
		<div>
			<form action="LogOutServlet"
				method="post">
				<button class="btn btn-primary" type="submit">
					<img
						src="../assets/images/glyphicons_free/glyphicons/png/glyphicons-388-log-out-white.png"
						width="15" height="15" alt=""> Log Out
				</button>
			</form>
		</div>
	</div>
</nav>