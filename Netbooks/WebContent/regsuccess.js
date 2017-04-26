$(document).ready(assButt);

function assButt(){
	$("#reg").click(function(){
			$.ajax({type:"POST",url:"http://localhost:8080/Netbooks/Register", data: {"birth_date" : $("display_name").val()}, success:message})
	});
}

function message(report){
	alert("HELL");
	alert(report);
}