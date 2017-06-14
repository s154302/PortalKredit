function errorInput(identity) {
	var id = identity;
	var input = $("#" + id).val();
	var patt = /^[a-z A-Z]+(-[a-z A-Z]+)?$/;

	if (patt.test(input)) {
		$("#" + id + "Form").removeClass("has-danger");
		$("#" + id).removeClass("form-control-danger");
		$("#" + id + "Help").addClass("hidden-xs-up");

	} else if (input != '') {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#" + id + "Help")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> Please only use letters and a single hyphen.");
	} else {
		checkEmpty(id);
	}
}

function checkEmpty(identity) {
	var id = identity;
	var input = $("#" + id).val();

	if (input == '') {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#" + id + "Help").removeClass("hidden-xs-up").html(
				"<strong>Error:</strong> Cannot be empty.");
	} else {
		$("#" + id + "Form").removeClass("has-danger");
		$("#" + id).removeClass("form-control-danger");
		$("#" + id + "Help").addClass("hidden-xs-up")
	}
}

function numberError(identity) {
	var id = identity;
	var input = $("#" + id).val();
	var patt = /^[0-9]+$/;

	if (patt.test(input)) {
		$("#" + id + "Form").removeClass("has-danger");
		$("#" + id).removeClass("form-control-danger");
		$("#" + id + "Help").addClass("hidden-xs-up")
	} else if (input != '') {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#" + id + "Help").removeClass("hidden-xs-up").html(
				"<strong>Error:</strong> Please only input numbers.");
	} else {
		checkEmpty(id);
	}
}

function createClientStatus() {
	var status = $("#createClientBool").val();

	if (status == 0) {
		$("#createClientResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createClientResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> Client was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#createClientResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review input and try again.")
				.addClass("alert alert-danger");
	}
}

function createBankerStatus() {
	var status = $("#createBankerBool").val();

	if (status == 0) {
		$("#createBankerResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createBankerResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> Banker was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#createBankerResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review input and try again.")
				.addClass("alert alert-danger");
	}
}

function createAdminStatus() {
	var status = $("#createAdminBool").val();

	if (status == 0) {
		$("#createAdminResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createAdminResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> Admin was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#createAdminResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review input and try again.")
				.addClass("alert alert-danger");
	}
}