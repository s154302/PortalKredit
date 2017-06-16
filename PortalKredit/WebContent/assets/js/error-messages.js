function createClientStatus() {
	var status = $("#createClientBool").val();

	if (status == 0) {
		$("#createClientResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createClientResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Client was created successfully.")
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
				"<strong>Success:</strong> Banker was created succesfully.")
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
				"<strong>Success:</strong> Admin was created succesfully.")
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
				"<strong>Success:</strong> Branch was created succesfully.")
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
				"<strong>Success:</strong> " + name
						+ " was deleted succesfully.").addClass(
				"alert alert-success");

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
				"<strong>Success:</strong> " + name
						+ " was deleted succesfully.").addClass(
				"alert alert-success");

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
				"<strong>Success:</strong> " + name
						+ " was deleted succesfully.").addClass(
				"alert alert-success");

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
				"<strong>Success:</strong> " + name
						+ " was deleted succesfully.").addClass(
				"alert alert-success");

	} else if (status == -1) {
		$("#deleteBranchResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review any dependencies and try again.")
				.addClass("alert alert-danger");
	}
}

function batch() {
	var status = $("#status").val();
	var message = $("#batchResult");
	message.removeClass("hidden-xs-up");
	console.log(status.substr(status.length - 1));
	switch (status.substr(status.length - 1)) {
	case "S":
		message
				.html(
						"<strong>Success:</strong> Operation was performed succesfully.")
				.addClass("alert alert-success")
		break;

	case "F":
		message.html("<strong>Error:</strong> An error has occurred.")
				.addClass("alert alert-danger");
		break;

	default:

	}

}

function deposit() {
	var status = $("#status").val();
	var message = $("#depositResult");
	
	if (status == 0) {
		$("#depositResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#depositResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Deposit complete.").addClass(
				"alert alert-success");
	} else if (status == -1) {
		$("#depositResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred.")
				.addClass("alert alert-danger");
	} else if(status === -2) {
		$("#depositResult")
		.removeClass("hidden-xs-up")
		.html(
				"<strong>Error:</strong> Incorrect password.")
		.addClass("alert alert-danger");
	}
}

function transaction() {
	var status = $("#status").val();
	var message = $("#transactionResult");
	
	if (status == 0) {
		$("#transactionResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#transactionResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Payment complete.").addClass(
				"alert alert-success");
	} else if (status == -1) {
		$("#transactionResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred.")
				.addClass("alert alert-danger");
	} else if(status === -2) {
		$("#transactionResult")
		.removeClass("hidden-xs-up")
		.html(
				"<strong>Error:</strong> Incorrect password.")
		.addClass("alert alert-danger");
	}
}

function createAccountType() {
	var status = $("#createAccountTypeBool").val();

	if (status == 0) {
		$("#createAccountTypeResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#createAccountTypeResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Account type was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#createAccountTypeResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review the input and try again.")
				.addClass("alert alert-danger");
	}
}

function deleteAccountType() {
	var status = $("#deleteAccountTypeBool").val();

	if (status == 0) {
		$("#deleteAccountTypeResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#deleteAccountTypeResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Account type was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#deleteAccountTypeResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred.")
				.addClass("alert alert-danger");
	}
}

function editClientStatus() {
	var status = $("#editClientBool").val();

	if (status == 0) {
		$("#editClientResult").addClass("hidden-xs-up");
	} else if (status == 1) {
		$("#editClientResult").removeClass("hidden-xs-up").html(
				"<strong>Success:</strong> Account type was created succesfully.")
				.addClass("alert alert-success");

	} else if (status == -1) {
		$("#editClientResult")
				.removeClass("hidden-xs-up")
				.html(
						"<strong>Error:</strong> An error occurred. Please review the input and try again.")
				.addClass("alert alert-danger");
	}
}




















