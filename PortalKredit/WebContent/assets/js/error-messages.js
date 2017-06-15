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

function createBranchStatus() {
	var status = $("#createBranchBool").val();

	if (status == 0) {
		$("#createBranchResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createBranchResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> Branch was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#createBranchResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review the input and try again.")
				.addClass("alert alert-danger");
	}
}

function deleteAdminStatus() {
	var status = $("#deleteAdminBool").val();
	var name = $("#adminName").val();

	if (status == 0) {
		$("#deleteAdminResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#deleteAdminResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> " + name + " was deleted succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#deleteAdminResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review any dependencies and try again.")
				.addClass("alert alert-danger");
	}
}

function deleteClientStatus() {
	var status = $("#deleteClientBool").val();
	var name = $("#clientName").val();

	if (status == 0) {
		$("#deleteClientResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#deleteClientResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> " + name + " was deleted succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#deleteClientResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review any dependencies and try again.")
				.addClass("alert alert-danger");
	}
}

function deleteBankerStatus() {
	var status = $("#deleteBankerBool").val();
	var name = $("#bankerName").val();

	if (status == 0) {
		$("#deleteBankerResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#deleteBankerResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> " + name + " was deleted succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#deleteBankerResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review any dependencies and try again.")
				.addClass("alert alert-danger");
	}
}

function deleteBranchStatus() {
	var status = $("#deleteBranchBool").val();
	var name = $("#branchName").val();

	if (status == 0) {
		$("#deleteBranchResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#deleteBranchResult").removeClass("hidden-xs-up").html(
				"<strong>Succes:</strong> " + name + " was deleted succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#deleteBranchResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review any dependencies and try again.")
				.addClass("alert alert-danger");
	}
}

function batch() {
	
}




















