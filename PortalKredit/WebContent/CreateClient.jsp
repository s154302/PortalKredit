<div class="row pad-row">
	<div class="col-4 offset-4">
		<div class="card card-outline-primary mb-3 text-center blue-outline">
			<h1>Create Client!</h1>
			<div class="card-block">
				<form action="" method="post">
					<div class="form-group" id="clientFirstNameForm">
						<input type="text" class="form-control" name="clientFirstName"
							placeholder="*First Name" maxlength="45" id="clientFirstName"
							oninput="errorInput(this.id); checkEmpty(this.id)">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientFirstNameHelp"></div>
					</div>

					<div class="form-group" id="clientLastNameForm">
						<input type="text" class="form-control" name="clientLastName"
							placeholder="*Last Name" maxlength="45" id="clientLastName"
							oninput="errorInput(this.id); checkEmpty(this.id)">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientLastNameHelp"></div>
					</div>

					<div class="form-group" id="clientPasswordForm">
						<input type="password" class="form-control" name="clientPassword"
							id="clientPassword" placeholder="*Password"
							oninput="checkEmpty(this.id)">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientPasswordHelp"></div>
					</div>

					<div class="form-group" id="clientCPRForm">
						<input type="text" class="form-control" name="clientCPR"
							placeholder="*CPR" maxlength="10"
							oninput="numberError(this.id); checkEmpty(this.id)"
							id="clientCPR">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientCPRHelp"></div>
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

					<div class="form-group" id="clientBankerIDForm">
						<input type="text" class="form-control" name="clientBankerID"
							id="clientBankerID" placeholder="*Associated Banker"
							value="${username}" maxlength="7" oninput="checkEmpty(this.id)">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientBankerIDHelp"></div>
					</div>

					<div class="form-group">
						<input type="text" class="form-control" name="clientStreet"
							placeholder="Address" maxlength="45">
					</div>

					<div class="form-group" id="clientCityForm">
						<input type="text" class="form-control" name="clientCity"
							id="clientCity" placeholder="*City Postal Code" maxlength="45"
							oninput="numberError(this.id); checkEmpty(this.id)">
						<div class="form-control-feedback alert alert-danger hidden-xs-up"
							id="clientCityHelp"></div>
					</div>

					<div class="form-group">
						<select class="form-control bfh-countries" data-country="Denmark"
							id="createClientCountry" name="clientCountry"></select>
					</div>
					
					<div class="form-control-feedback hidden-xs-up"
						id="createClientResult" role="alert">
						<input type="text" class="form-control hidden-xs-up"
							value="${createClientStatus}" id="createClientBool">
					</div>
					
					<div class="form-group">
						<button class="btn btn-primary btn-block btn-action" type="submit"
							value="createClient" id="submitCreateClient">Create
							Client</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</div>