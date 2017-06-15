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
	var input = $("#" + id).val().trim();

	if (input === '') {
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








