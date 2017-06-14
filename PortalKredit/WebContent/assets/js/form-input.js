function errorInput(identity) {
	var id = identity;
	var input = $("#" + id).val();
	var patt = /^[a-z A-Z]+(-[a-z A-Z]+)?$/;

	if (patt.test(input)) {
		$("#" + id + "Form").removeClass("has-danger");
		$("#" + id).removeClass("form-control-danger");
		$("#submitCreateClient").removeClass("disabled");
		$("#" + id + "Help").addClass("hidden-xs-up");
	} else {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#submitCreateClient").addClass("disabled");
		$("#" + id + "Help")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> Please only use letters and a single hyphen.");
	}
}

function checkEmpty(identity) {
	var id = identity;
	var input = $("#" + id).val();
	console.log(input);
	if (input == '') {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#submitCreateClient").addClass("disabled");
		$("#" + id + "Help").removeClass("hidden-xs-up").html(
				"<strong>Error:</strong> Cannot be empty.");
	} else {
		$("#" + id + "Form").removeClass("has-danger");
		$("#" + id).removeClass("form-control-danger");
		$("#submitCreateClient").removeClass("disabled");
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
		$("#submitCreateClient").removeClass("disabled");
	} else {
		$("#" + id + "Form").addClass("has-danger");
		$("#" + id).addClass("form-control-danger");
		$("#submitCreateClient").addClass("disabled");
		$("#" + id + "Help").removeClass("hidden-xs-up").html(
				"<strong>Error:</strong> Please only numbers.");
	}
}

function createClientStatus() {
	var status = $("#createClientBool").val();
	console.log(status);
	if (status == 0) {
		$("#createClientResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createClientResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> Client was created succesfully.")
				.addClass("alert alert-success");

	} else if(status == -1) {
		$("#createClientResult").removeClass("hidden-xs-up").html(
		"<strong>Error:</strong> An error occurred. Please review input and try again.")
		.addClass("alert alert-danger");
	}
}
