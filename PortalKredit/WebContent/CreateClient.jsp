<div class="row pad-row">
	<div class="col-4 offset-4">
		<div class="card card-outline-primary mb-3 text-center blue-outline">
			<h1>Create Client!</h1>
			<div class="card-block">
				<form action="" method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="clientFirstName"
							placeholder="*First Name" maxlength="45">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientLastName"
							placeholder="*Last Name" maxlength="45">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" name="clientPassword"
							placeholder="*Password">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientCPR"
							placeholder="*CPR" maxlength="10">
					</div>

					<div class="form-group">
						<input type="email" class="form-control" name="clientEmail"
							placeholder="E-mail" maxlength="45">
					</div>

					<div class="form-group">
						<input type="text" class="form-control bfh-phone"
							data-country="createClientCountry" name="clientTelephone"
							placeholder="Phone Number">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientBankerID"
							placeholder="*Associated Banker" value="${username}" maxlength="7">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientStreet"
							placeholder="Address" maxlength="45">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientCity"
							placeholder="*City Postal Code" maxlength="45">
					</div>

					<div class="form-group">
						<select class="form-control bfh-countries" data-country="Denmark"
							id="createClientCountry" name="clientCountry"></select>
					</div>

					<button class="btn btn-primary btn-block btn-action" type="submit"
						value="createClient">Create Client</button>
					<p>
						${status}
					</p>

				</form>
			</div>
			<p style="color: red">${createClientStatus}</p>
		</div>
	</div>
</div>