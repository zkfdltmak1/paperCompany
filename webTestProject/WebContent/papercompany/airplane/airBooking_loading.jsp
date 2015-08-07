<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String airBooking_arrivalCity = (String)request.getParameter("airBooking_arrivalCity");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<title>Insert title here</title>
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/booking/airplane/airBooking_loading.css" />
<script type="text/javascript">
	var imagePath = "/webTestProject/style/image/airBooking/";
	var arrivalCity = '<%=airBooking_arrivalCity%>';
	
	$(function(){
		if(arrivalCity == "서울"){			
			$("body").css({
				'background-image' : 'url(/webTestProject/style/image/airBooking/서울2.jpg)'
			});	
		}else if(arrivalCity == "청주"){
			$("body").css({
				'background-image' : 'url(/webTestProject/style/image/airBooking/청주1.jpg)'
			});	
		}else if(arrivalCity == "여수"){
			$("body").css({
				'background-image' : 'url(/webTestProject/style/image/airBooking/여수2.jpg)'
			});	
		}else if(arrivalCity == "부산"){
			$("body").css({
				'background-image' : 'url(/webTestProject/style/image/airBooking/부산1.jpg)'
			});	
		}else if(arrivalCity == "제주도"){
			$("body").css({
				'background-image' : 'url(/webTestProject/style/image/airBooking/제주1.jpg)'
			});	
		}
		setTimeout(function(){
			$("#airBooking_detail").attr({
				method : "POST",
				action : "/webTestProject/airBooking.air?command=airBooking_detail"
			}).submit();
		}, 2800);
	});
</script>
</head>
<body>
	<div class="loading"></div>
	<div id="load">
	  <div>G</div>
	  <div>N</div>
	  <div>I</div>
	  <div>D</div>
	  <div>A</div>
	  <div>O</div>
	  <div>L</div>
	</div>
	<form id="airBooking_detail">
		
		
		<input type="hidden" name="airBooking_startCity" value="<%=request.getAttribute("airBooking_startCity")%>">
		<input type="hidden" name="airBooking_arrivalCity" value="<%=request.getAttribute("airBooking_arrivalCity")%>">
		<input type="hidden" name="airBooking_date" value="<%=request.getAttribute("airBooking_date")%>">
		<input type="hidden" name="airBooking_adults" value="<%=request.getAttribute("airBooking_adults")%>">
		<input type="hidden" name="airBooking_kids" value="<%=request.getAttribute("airBooking_kids")%>">
		
		
		<input type="hidden" name="command" value="airBooking_detail">
	</form>
</body>
</html>