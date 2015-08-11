<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%
   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
   String today = formatter.format(new java.util.Date());
   Date date = new java.util.Date();
   date.setMonth(date.getMonth()+1);
   String endday = formatter.format(date);
   
   String session_email = (String)session.getAttribute("s_member_email");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, 
					maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<script type="text/javascript" src="/webTestProject/style/js/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/airBooking/airBooking.js"></script>
<script type="text/javascript" src="/webTestProject/style/js/bootstrap/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/bootstrap/bootstrap.min.css" />
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/extras/modernizr-custom.js"></script>
<script src="http://cdn.jsdelivr.net/webshim/1.12.4/polyfiller.js"></script>
<link rel="stylesheet" type="text/css" href="/webTestProject/style/css/booking/airplane/airBooking.css" />
<script>
  webshims.setOptions('waitReady', false);
  webshims.setOptions('forms-ext', {types: 'date'});
  webshims.polyfill('forms forms-ext');
</script>
</head>
<body>
	<%if(session_email == null){%>
		<script type="text/javascript">
			$(function(){
				alert("정상적인 접근이 아닙니다.\n로그인을 하여 주세요.");
				location.href="/webTestProject/index.jsp";
			});
		</script>
	<%}else{ %>
	<!-- 헤더 영역 -->
	<jsp:include page="../mainBar/mainTop.jsp"></jsp:include>
	<!-- 헤더 영역 -->
	
	<!-- 본문 영역 -->
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
					<fieldset>
						<!-- Form Name -->
						<legend><h3>비행기 예매</h3></legend>
						
						<form class="form-horizontal airBooking_form" role="form" method="post" action="/webTestProject/airBooking.air">
						<!-- 출발지, 도착지 -->
						<div class="form-group">
							<label class="col-sm-3 control-label">장소</label>
							<div class="col-sm-4">
								<select class="form-control selectpicker" id="airBooking_startCity" name="airBooking_startCity">
									<option>출발지</option>
									<option>서울</option>
									<option>부산</option>
									<option>제주도</option>
									<option>여수</option>
									<option>청주</option>
								</select>
							</div>
						 	
							<!-- <label class="col-sm-2 control-label" for="textinput"></label> -->
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_arrivalCity" name="airBooking_arrivalCity">
									<option>도착지</option>
									<option>서울</option>
									<option>부산</option>
									<option>제주도</option>
									<option>여수</option>
									<option>청주</option>
								</select>
							</div>
						</div>
						
						<!-- 출발지와 도착지가 같으면 검색결과 없음 -->
						
						<!-- 날짜 -->
						<div class="form-group">
							<label class="col-sm-3 control-label" for="textinput">날짜</label>
							<div class="col-sm-8">
								<input class="form-control" type="date" value="<%= today %>"  min="<%=today%>" max="<%=endday%>" id="airBooking_date" name="airBooking_date"/>
							</div>
						</div>
						
						<!-- 인원수 -->
						<div class="form-group">
							<label class="col-sm-3 control-label" for="sel1">인원</label>
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_adults" name="airBooking_adults">
									<option>대인</option>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
								</select>
							</div>
							<div class="col-sm-4">
								<select class="form-control" id="airBooking_kids" name="airBooking_kids">
									<option>소인</option>
								</select>
							</div>							
						</div>
						
						<!-- 버튼 -->
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-6">
								<div class="pull-right">
									<button type="submit" class="btn btn-primary" id="btn-primary">예매</button>
									<button type="reset" class="btn btn-default">취소</button>
								</div>
							</div>
						</div>
						<input type="hidden" name="command" value="airBooking_loading">
						</form>
						
					</fieldset>
			</div><!-- /.col-lg-12 -->
		</div><!-- /.row -->
	</div>
	<!-- 본문 영역 -->
	
	<!-- 푸터 영역 -->
	<jsp:include page="../mainBar/mainFooter.jsp"></jsp:include>
	<!-- 푸터 영역 -->
	<%}%>
</body>
</html>