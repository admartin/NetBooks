/**
 * 
 */
function checkNotEmpty() {
	var fname = document.getElementById("fname");
	var fnameLabel = document.getElementById("fname_");
	if(fname == null) {
		fnameLabel.color = red;
		return requiredFieldsAlert();
	}
}

function requiredFieldsAlert() {
	alert("All fields are required.");
}