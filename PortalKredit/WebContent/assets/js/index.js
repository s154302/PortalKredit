function loginError() {
	var correctPassword = $("#passBool").val();
	
	if (correctPassword == 1) {
		$("#loginError").addClass("hidden-xs-up");
	} else {
		$("#loginError").removeClass("hidden-xs-up");
	}
}