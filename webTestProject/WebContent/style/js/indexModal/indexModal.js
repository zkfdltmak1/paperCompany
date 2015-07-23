function fnJoin(){
	$("#loginModal").modal("hide");
	$("#joinModal").modal("show");		
}
function fnBack(){
	$("#joinModal").modal("hide");
	$("#selectPWModal").modal("hide");
	$("#selectIDModal").modal("hide");
	$("#loginModal").modal("show");
}

function fnSelectId(){
	$("#joinModal").modal("hide");				
	$("#selectIDModal").modal("show");
}

function fnSelectPW(){
	$("#joinModal").modal("hide");				
	$("#selectPWModal").modal("show");
}

