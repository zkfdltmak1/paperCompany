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
<script type="text/javascript" src="../../style/js/jquery/jquery-1.11.3.min.js"></script>
<link type="text/css" rel="stylesheet" href="/webTestProject/style/css/booking/bus/bus_charge_list.css"/>
<title>Charge List</title>
</head>
<body>
	<div id="main">
	<table id="bus_table_id" >
		<tr>
			<th rowspan="2">구분</th>
			<th colspan="2">우등</th>
			<th colspan="2">일반</th>
		</tr>
		<tr id="first_td">
			<td>어른</td>
			<td>어린이</td>
			<td>어른</td>
			<td>어린이</td>
		</tr>
		<tr id="second_td">
			<td>금액</td>
			<td>${param.charge_vip}</td>
			<td>${param.charge_vipChildren}</td>
			<td>${param.charge_Standard}</td>
			<td>${param.charge_StandardChildren}</td>
		</tr>
		<tr>
			<td colspan="5"><input type="button" value="창 닫기" onclick="window.close()"/></td>
		</tr>
	</table>
	</div>
</body>
</html>