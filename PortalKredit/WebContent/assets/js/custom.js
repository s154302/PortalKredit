function checkInput(value) {
	console.log("Test")
	if (value == "cheese") {
		$("#form1").removeClass("has-danger").removeClass("alert-danger")
				.addClass("has-success");
		$("#input1").removeClass("form-control-warning").removeClass(
				"form-control-danger").addClass("form-control-success");
		$("#helpBlock2").removeClass("alert-warning").removeClass(
				"alert-danger").addClass('alert-success');
		$("#helpBlock2").html("Shit's right");

	} else {
		$("#form1").removeClass("has-warning").removeClass("has-success")
				.addClass("has-danger");
		$("#input1").removeClass("form-control-warning").removeClass(
				"form-control-success").addClass("form-control-danger");
		$("#helpBlock2").removeClass("alert-warning").removeClass(
				"alert-success").addClass('alert-danger');
		$("#helpBlock2").html("Yo shit ain't right");
	}
}