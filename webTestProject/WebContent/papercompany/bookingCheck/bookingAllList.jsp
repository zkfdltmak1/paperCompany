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
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js">
</script>
<link type="text/css" rel="stylesheet" href="/webTestProject/style/css/booking/bookingAllCheck.css"/>

<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<div id="main">
		<p>총 예약 정보 확인 </p>
		<table border="1" id="bookingAllcheck_table_id">
			<c:choose>
				<c:when test="${empty requestScope.baList}">
					
					<tr><td><p>예약 정보가 없습니다.</p></td></tr>
					
				</c:when>
				<c:otherwise>
					<tr>
						<th>예매번호</th>
						<th>고객번호</th>
						<th>예  매  날  짜</th>
						<th>출발지</th>
						<th>출발시간</th>
						<th>도착지</th>
						<th>도착시간</th>
						<th>운송수단종류</th>
						<th>연령구분</th>
						<th>좌석번호</th>
						<th>가   격</th>
					</tr>
				<c:forEach var="baList" items="${requestScope.baList}">
					<tr>
						<td>${baList.booking_dcode}</td>
						<td>${baList.booking_code }</td>
						<td>${baList.dp_date }</td>
						<td>${baList.start_city }</td>
						<td>${baList.time_code }</td>
						<td>${baList.arrival_city }</td>
						<td>${baList.arrival_time }</td>
						<td>${baList.vehicle_code }</td>
						<td>${baList.booking_age }</td>
						<td>${baList.seat_code }</td>
						<td>${baList.booking_price }</td>
					</tr>
				</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>