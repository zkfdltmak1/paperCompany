<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">	
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/booking/bus/bus.css" />
<title>Bus Tiketing</title>

<!-- select 쓰기 위한 js -->
<script type="text/javascript" src="/webTestProject/style/js/bus/bus_category.js"></script>

<!-- 날짜 표현하기 위한 js -->
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<link type="text/css" rel="stylesheet" href="/webTestProject/style/css/booking/bus/bus.css"/>
<script type="text/javascript">
	
$(function(){
	$("#adult_people").change(function(){
		$("#children_people").find("option").each(function(){
			for(var i=0; i<=5; i++){
				if(this.value=i){
			        $(this).remove();
				}
			}
		});
	for(var j = 0; j<=5; j++){
		if($("#adult_people option:selected").text() == "어른 " + j + "명"){
			for(var i=0; i<=5-j; i++){
				$("#children_people").append("<option value="+i+">어린이 "+i+"명</option>");
			}
		}
	}
	if($("#adult_people option:selected").text() == "어른 0명"){
		$("#children_people option:first").remove();
	}
	});
});
</script>
</head>
<body>
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>

	<div id="main"> 
	<form id="bus_mainForm">
		<input type="hidden" name="command" value="bus_search_command" />
		<div id="bus_select_main">
			<p class="title">버스 예매</p>
			<div class="exec">
					<span id="category1">
						<select name="bus_type" id="category1-1">
							<option>----- 선택하세요 -----</option>
							<c:forEach var="vList" items="${sessionScope.vList}">
							<option value="${vList.vehicle_kinds }" data-target="#category2-1">${vList.vehicle_kinds }</option>
							</c:forEach>
						</select>
					</span> 
					<span id="category2">
						<select name="depart_station" id="category2-1">
							<option>----- 선택하세요 -----</option>
							<c:forEach var="cList" items="${sessionScope.cList}">
							<option value="${cList.city_city }" data-target="#category2-2">${cList.city_city }</option>
							</c:forEach>
						</select>
					</span> 
					<span id="category3">
						<select name="last_station" id="category2-2">
							<option>----- 선택하세요 -----</option>
							<c:forEach var="cList" items="${sessionScope.cList}">
							<option value="${cList.city_city }" data-target="#category2-2">${cList.city_city }</option>
							</c:forEach>
						</select>
					</span>
					<select name="adult_people" id="adult_people">
						<c:forEach var="i" begin="0" end="5" step="1">
							<option value="${i }">어른 ${i }명</option>
						</c:forEach>
					</select>
					<select name="children_people" id="children_people"></select>
						<input type="date" name="bus_date" min="${sessionScope.today}" max="${sessionScope.endday }" value="${sessionScope.today}"/>
						<input type="submit" value="검색" id="searchButton"/>
				</div>
		</div>
	</form>
	<form id="bus_viewForm">		
			<c:if test="${!empty depart_list}">
			<div class="console" id="select_id">
				<table id="bus_table_id" class="bus_table_class">
					<tr>
						<th>버스종류</th>
						<th>출발지</th>
						<th>도착지</th>
						<th>출발날짜</th>
						<th>출발시간</th>
						<th>도착시간</th>
						<th>우등</th>
						<th>일반</th>
						<th>운임요금</th>
					</tr>
						<c:forEach var="depart_list" items="${depart_list}">
						<tr>
							<td>${sessionScope.bus_type}</td>
							<td>${sessionScope.depart_station}</td>
							<td>${sessionScope.last_station}</td>
							<td>${sessionScope.bus_date}</td>
							<td>${depart_list.time_time}</td>
							<td>${depart_list.arrival_time}</td>
							<td>
								<a href="/webTestProject/bus/bus.do?command=bus_vip&bus_date=${depart_list.time_time}&arrival_time=${depart_list.arrival_time}" >예약하기</a>
							</td>
							<td>
								<a href="/webTestProject/bus/bus.do?command=bus_standard&bus_date=${depart_list.time_time}&arrival_time=${depart_list.arrival_time}" >예약하기</a>
							</td>
							<td>
								<a href="javascript:window.open('/webTestProject/papercompany/bus/bus_charge_list.jsp?charge_vip=${requestScope.charge_vip}&charge_vipChildren=${requestScope.charge_vipChildren}&charge_Standard=${requestScope.charge_Standard }&charge_StandardChildren=${requestScope.charge_StandardChildren}', 'charge', 'width=600 height=450 left=320 top=270')">운임조회</a>
							</td>
						</tr>					
						</c:forEach>
				</table>
			</div>
			</c:if>
		</form>
	</div>
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
</body>
</html>