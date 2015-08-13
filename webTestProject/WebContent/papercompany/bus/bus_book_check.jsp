<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js">
</script>
<link type="text/css" rel="stylesheet" href="/webTestProject/style/css/booking/bus/bus_check.css"/>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<title>Insert title here</title>
<script type="text/javascript">
	 $(function(){
		 
		$("#payCheck").bind("click",function(){
			
			if(confirm("결제를 하시겠습니까?")){
				
				$("#bus_detail_form").attr( "action", "../bus/bus.do" );
				$("#bus_detail_form").submit();
			}
			 
		});	
	}); 
	

</script>

</head>
<body>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>	
	<div id="main" >
		<div>
		<table class="bus_table_id">
			<tr>
				<th>출발 날짜</th>
				<th>버스 종류</th>
				<th>출발지</th>
				<th>출발시간</th>
				<th>도착지</th>
				<th>도착시간</th>
				<th>예약매수</th>
				<th>총결제금액</th>
			</tr>
			<tr>
				<td>${requestScope.bus_date }</td>
				<td>${requestScope.bus_type }</td>
				<td>${requestScope.depart_station }</td>
				<td>${requestScope.depart_date }</td>
				<td>${requestScope.last_station }</td>
				<td>${requestScope.arrival_time }</td>
				<td>${requestScope.result_people }</td>
				<td>${requestScope.resultCharge}</td>
			</tr>
		</table>
		</div>
		<form id="bus_detail_form" name="bus_detail_form">
			<input type="hidden" name="command" value="payCheck_command"/>
			<div>
				<table class="bus_table_id">
					<tr>
						<th>객실등급</th>
						<th>좌석정보</th>
						<th>승객유형</th>
						<th>운임요금</th>
					</tr>
					<c:forEach var="bList" items="${requestScope.bList}">
						<tr>
							<td>${bList.seat_seat}</td>
							<td>${bList.seat_number}</td>
							<td>${bList.people}</td>
							<td>${bList.charge}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="4"><input type="button" id="payCheck"  value="결제하기" /></td>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>