function loginError() {
	console.log("Hej");
	var correctPassword = $("#passBool").val();
	
	if (correctPassword == 1) {
		$("#loginError").addClass("hidden-xs-up");
	} else {
		$("#loginError").removeClass("hidden-xs-up").html("<strong>Error:</strong> Incorrect Password");
	}
}
