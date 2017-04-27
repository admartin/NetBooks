$(document).ready(auto);

function auto(){
		$.ajax({url:"Search", success:addArr, dataType:'json'});
		//retrieves array from Search servlet
}

function addArr(list){
		//input is id of the search bar
		$("input[name='search']").autocomplete({source:list});	
}