$(function(){
	

	$("#btn-primary").click(function(){
		var startCity= $("#airBooking_startCity").val();
		var arrivalCity= $("#airBooking_arrivalCity").val();
		var adults= $("#airBooking_adults").val();
		var kids= $("#airBooking_kids").val();
		var date= $("#airBooking_date").val();
//출발지
		if(startCity == "출발지"){
			$("#airBooking_startCity").css({
				border : "2px solid red"	
			});
			return false;
		}else{
			$("#airBooking_startCity").css({
				border : "2px solid green"	
			});
		}
					
//도착지
		if(arrivalCity == "도착지"){
			$("#airBooking_arrivalCity").css({
				border : "2px solid red"	
			});
			return false;
		}else{
			$("#airBooking_arrivalCity").css({
				border : "2px solid green"	
			});
			
		}
		
//대인
		if(adults == "대인"){
			$("#airBooking_adults").css({
				border : "2px solid red"	
			});
			return false;
		}else{
			$("#airBooking_adults").css({
				border : "2px solid green"	
			});
		}
		
//소인
		if(kids == "소인"){
			$("#airBooking_kids").css({
				border : "2px solid red"	
			});
			return false;
		}else{
			$("#airBooking_kids").css({
				border : "2px solid green"	
			});	
		}
//날짜
		if(date == ""){
			$("#airBooking_date").css({
				border : "2px solid red"	
			});
			return false;
		}else{
			$("#airBooking_date").css({
				border : "2px solid green"	
			});	
		}
		
		
	});
	
	$("#airBooking_adults").change(function(){
		if($("#airBooking_adults option:selected").text() == "1"){
			$("#airBooking_kids option").remove();
			$("#airBooking_kids").append("<option>소인</option");
			$("#airBooking_kids").append("<option>0</option");
			$("#airBooking_kids").append("<option>1</option");
			$("#airBooking_kids").append("<option>2</option");
			$("#airBooking_kids").append("<option>3</option");
		}
		else if($("#airBooking_adults option:selected").text() == "2"){
			$("#airBooking_kids option").remove();
			$("#airBooking_kids").append("<option>소인</option");
			$("#airBooking_kids").append("<option>0</option");
			$("#airBooking_kids").append("<option>1</option");
			$("#airBooking_kids").append("<option>2</option");
		}
		else if($("#airBooking_adults option:selected").text() == "3"){
			$("#airBooking_kids option").remove();
			$("#airBooking_kids").append("<option>소인</option");
			$("#airBooking_kids").append("<option>0</option");
			$("#airBooking_kids").append("<option>1</option");
		}
		else if($("#airBooking_adults option:selected").text() == "4"){
			$("#airBooking_kids option").remove();
			$("#airBooking_kids").append("<option>소인</option");
			$("#airBooking_kids").append("<option>0</option");
		}		
	});
	
	$("#airBooking_arrivalCity").change(function(){
		var startCity = $("#airBooking_startCity option:selected").text();
		var arrivalCity = $("#airBooking_arrivalCity option:selected").text();
		if(startCity == arrivalCity){
			alert("같은 곳으로 갈려고?");
			return false;
		}
	});
	return false;
});